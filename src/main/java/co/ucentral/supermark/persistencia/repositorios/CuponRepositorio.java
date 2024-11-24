package co.ucentral.supermark.persistencia.repositorios;

import co.ucentral.supermark.persistencia.entidades.Cupon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CuponRepositorio extends CrudRepository<Cupon, Long> {
    Optional<Cupon> findByCodigo(String codigo);
}
