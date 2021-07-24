package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Cep;
import org.springframework.data.repository.CrudRepository;

public interface CepDao extends CrudRepository<Cep, Long> {
}
