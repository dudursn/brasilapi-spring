package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Cnpj;
import org.springframework.data.repository.CrudRepository;

public interface CnpjDao extends CrudRepository<Cnpj, Long> {
}
