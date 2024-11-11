package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Proveedor;
import co.ucentral.supermark.persistencia.repositorios.ProveedorRepositorio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProveedorServicio {

    private static final Logger log = LogManager.getLogger(ProveedorServicio.class);

    private final ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> obtenerTodos() {
        return (List<Proveedor>) proveedorRepositorio.findAll();
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    public Proveedor buscarPorNit(int nit) {
        return proveedorRepositorio.findById(nit).orElse(null);
    }

    public boolean borrar(Proveedor proveedor) {
        try {
            proveedorRepositorio.delete(proveedor);
        } catch (Exception e) {
            log.error("Error al borrar el proveedor: {}", e.getMessage());
            return false;
        }
        return true;
    }

    public void borrarPorNit(int nit) {
        try {
            proveedorRepositorio.deleteById(nit);
        } catch (Exception e) {
            log.error("Error al borrar el proveedor por NIT: {}", e.getMessage());
        }
    }
}
