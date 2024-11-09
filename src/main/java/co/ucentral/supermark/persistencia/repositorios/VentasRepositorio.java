package co.ucentral.supermark.persistencia.repositorios;

import co.ucentral.supermark.persistencia.entidades.Ventas;
import org.springframework.data.repository.CrudRepository;

public interface VentasRepositorio extends CrudRepository<Ventas, Integer> {
}
