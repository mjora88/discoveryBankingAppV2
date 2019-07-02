package com.khoza.atm.repository;

import com.khoza.atm.model.ATM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends CrudRepository<ATM, Integer>
{

}
