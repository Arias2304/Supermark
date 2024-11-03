package co.ucentral.supermark.persistencia.repositorios;
import co.ucentral.supermark.persistencia.entidades.Producto;
import org.springframework.data.repository.CrudRepository;


public interface ProductoRepositorio extends CrudRepository<Producto, String> {

}