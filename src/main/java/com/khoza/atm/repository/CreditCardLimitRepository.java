package com.khoza.atm.repository;

import com.khoza.atm.model.CreditCardLimit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardLimitRepository extends CrudRepository<CreditCardLimit, String>
{
}
