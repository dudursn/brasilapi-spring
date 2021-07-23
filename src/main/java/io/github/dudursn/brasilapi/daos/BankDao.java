package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Bank;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankDao extends CrudRepository<Bank, Long> {


}
