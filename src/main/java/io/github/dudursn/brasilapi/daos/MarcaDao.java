package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Marca;
import org.springframework.data.repository.CrudRepository;

public interface MarcaDao extends CrudRepository<Marca, Long> {
}
