
package com.khoza.atm.service;

import com.khoza.atm.model.Denomination;
import com.khoza.atm.repository.DenominationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenominationService
{
  @Autowired
  private DenominationRepository  denominationRepository;


  public List<Denomination> findAll()
  {
    return (List<Denomination>) denominationRepository.findAll();
  }
}
