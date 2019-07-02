package com.khoza.atm.repository;

import com.khoza.atm.model.ATMAllocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMAllocationRepository extends CrudRepository<ATMAllocation, Integer>
{

}
