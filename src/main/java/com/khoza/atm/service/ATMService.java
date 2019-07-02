package com.khoza.atm.service;

import com.khoza.atm.model.ATM;
import com.khoza.atm.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMService {

    @Autowired
    private AtmRepository atmRepository;

    public List<ATM> getAllAtms()
    {
        return (List<ATM>) atmRepository.findAll();
    }


  public ATM findOne(int atmId)
  {
    return atmRepository.findOne(atmId);
  }
}
