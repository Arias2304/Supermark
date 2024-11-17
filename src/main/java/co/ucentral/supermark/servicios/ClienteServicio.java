package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.persistencia.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteServicio {

    private static final Logger log = LogManager.getLogger(ClienteServicio.class);

    private final ClienteRepositorio clienteRepositorio;

    public List<Cliente> obtenerTodos() {
        return (List<Cliente>) clienteRepositorio.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public Cliente buscarPorId(Integer ID) {
        return clienteRepositorio.findById(ID).orElse(null);
    }

    public boolean borrar(Cliente cliente) {
        try {
            clienteRepositorio.delete(cliente);
        } catch (Exception e) {
            log.error("Error al borrar el cliente: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean borrarPorId(int ID) {
        try {
            clienteRepositorio.deleteById(ID);
            return true;
        } catch (Exception e) {
            log.error("Error al borrar el cliente por ID: {}", e.getMessage());
            return false;
        }
    }
    @Autowired
    private ClienteRepositorio clienteRepository;

    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}
