package com.khoza.atm.service;

import com.khoza.atm.model.Client;
import com.khoza.atm.model.ClientAccount;
import com.khoza.atm.model.CreditCardLimit;
import com.khoza.atm.model.Denomination;
import com.khoza.atm.model.FinancialPositionReport;
import com.khoza.atm.repository.AccountRepository;
import com.khoza.atm.repository.ClientRepository;
import com.khoza.atm.repository.CreditCardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankingService
{
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private CreditCardLimitRepository creditCardLimitRepository;

  @Autowired
  private DenominationService denominationService;

  private static List<Double> nodesInDescendingOrder = new ArrayList<>();

  private void loadDenominations()
  {
    nodesInDescendingOrder.addAll(denominationService.findAll().stream()
        .filter(denomination -> denomination.getDenominationTypeCode().getDenominationTypeCode().equals("N"))
        .sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue())).mapToDouble(Denomination::getValue).boxed().collect(Collectors.toList()));
  }

  @Transactional
  public List<String> withdraw(ClientAccount selectedAccount, long amountLeftToWithdraw, double maxToWithdraw)
  {
    loadDenominations();

    List<String> listOfNotesToReturn = new ArrayList<>();
    double totalWithdrawn = 0;

    if (amountLeftToWithdraw <= maxToWithdraw)
    {
      for (Double note : nodesInDescendingOrder)
      {
        int numberOfNodes = 0;
        while (amountLeftToWithdraw >= note)
        {
          totalWithdrawn += note;
          amountLeftToWithdraw -= note;
          numberOfNodes++;
        }
        if (numberOfNodes > 0)
        {
          listOfNotesToReturn.add(numberOfNodes + " X R" + note);
        }
      }

      if (selectedAccount.getAccountTypeCode().getAccountTypeCode().equals("CCRD"))
      {
        double mainAccBalance = selectedAccount.getBalance();
        if (totalWithdrawn > mainAccBalance)
        {
          double amountTakenFromOverdraft = totalWithdrawn - mainAccBalance;
          selectedAccount.setBalance(0);// we have taken all the money available, and took some from overdraft. So main balance is now updated to R0

          //now updating overdraft limit
          CreditCardLimit creditCardLimit = creditCardLimitRepository.findOne(selectedAccount.getAccountNumber());
          creditCardLimit.setAccountLimit(creditCardLimit.getAccountLimit() - amountTakenFromOverdraft);
          creditCardLimitRepository.save(creditCardLimit);
        }
      }
      else
      {
        selectedAccount.setBalance(selectedAccount.getBalance() - totalWithdrawn);
      }

      accountRepository.save(selectedAccount);

      listOfNotesToReturn.add("Total Amount Withdrawn:\t R" + String.format("%.2f", totalWithdrawn));
      listOfNotesToReturn.add("Remaining Balance:\t R" + String.format("%.2f", selectedAccount.getBalance()));
    }

    return listOfNotesToReturn;
  }

  public List<FinancialPositionReport> findAllAgregateFinancialPositions()
  {
    List<FinancialPositionReport> financialPositionReports = new ArrayList<>();

    for (Client client : clientRepository.findAll())
    {
      FinancialPositionReport report = new FinancialPositionReport();
      report.setCustmerId(client.getId());
      report.setFormalName(client.getFormalName());

      List<ClientAccount> listOfClientAccounts = accountRepository.findAllByClient(client);

      if (!listOfClientAccounts.isEmpty())
      {
        report.setLoanBalance(listOfClientAccounts
            .stream()
            .filter(account -> !account.getAccountTypeCode().getTransactional())
            .mapToDouble(ClientAccount::getBalance).sum()
        );
        report.setTransactionalBalance(listOfClientAccounts
            .stream()
            .filter(account -> account.getAccountTypeCode().getTransactional())
            .mapToDouble(ClientAccount::getBalance).sum()
        );

        report.setNetPosition(listOfClientAccounts
            .stream()
            .mapToDouble(ClientAccount::getBalance).sum()
        );

        financialPositionReports.add(report);
      }
    }

    return financialPositionReports;
  }

}
