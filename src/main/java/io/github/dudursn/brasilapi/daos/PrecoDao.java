package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Preco;
import org.springframework.data.repository.CrudRepository;

public interface PrecoDao extends CrudRepository<Preco, Long> {
}
