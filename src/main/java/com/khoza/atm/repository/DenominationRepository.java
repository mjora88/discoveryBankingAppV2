package com.khoza.atm.repository;

import com.khoza.atm.model.Denomination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominationRepository extends CrudRepository<Denomination, String>
{
}
