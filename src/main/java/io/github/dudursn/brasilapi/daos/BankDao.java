package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankDao extends CrudRepository<Bank, Long> {


}
