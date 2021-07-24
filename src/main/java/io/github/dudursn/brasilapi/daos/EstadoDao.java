package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.Estado;
import org.springframework.data.repository.CrudRepository;

public interface EstadoDao extends CrudRepository<Estado, Long> {
}
