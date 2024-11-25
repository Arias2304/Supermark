package co.ucentral.supermark.persistencia.repositorios;

import co.ucentral.supermark.persistencia.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepositorio extends JpaRepository<Venta, Integer> {

}