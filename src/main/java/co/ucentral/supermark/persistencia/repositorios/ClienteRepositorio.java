package co.ucentral.supermark.persistencia.repositorios;
import co.ucentral.supermark.persistencia.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;


public interface ClienteRepositorio extends CrudRepository<Cliente, Integer>{
}
