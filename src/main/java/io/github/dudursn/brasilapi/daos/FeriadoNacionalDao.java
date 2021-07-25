package io.github.dudursn.brasilapi.daos;

import io.github.dudursn.brasilapi.models.FeriadoNacional;
import org.springframework.data.repository.CrudRepository;

public interface FeriadoNacionalDao extends CrudRepository<FeriadoNacional, Long> {
}