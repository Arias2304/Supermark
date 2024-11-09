package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Ventas;
import co.ucentral.supermark.persistencia.repositorios.VentasRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VentasProducto {

    private static final Logger log = LogManager.getLogger(VentasProducto.class);
    private final VentasRepositorio ventasRepositorio;

    public List<Ventas> obtenerTodas() {
        log.info("Obteniendo todas las ventas de productos");
        return (List<Ventas>) ventasRepositorio.findAll();
    }

    public void registrar(Ventas venta) {
        log.info("Registrando venta de producto con ID: {}", venta.getId());
        ventasRepositorio.save(venta);
    }

    public Ventas buscarPorId(int id) {
        log.info("Buscando venta de producto con ID: {}", id);
        Optional<Ventas> venta = ventasRepositorio.findById(id);
        return venta.orElse(null);
    }

    public boolean borrar(Ventas venta) {
        try {
            log.info("Eliminando venta de producto con ID: {}", venta.getId());
            ventasRepositorio.delete(venta);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar la venta de producto: {}", e.getMessage());
            return false;
        }
    }

    public boolean borrarPorId(int id) {
        try {
            log.info("Eliminando venta de producto con ID: {}", id);
            ventasRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar la venta de producto con ID {}: {}", id, e.getMessage());
            return false;
        }
    }
}
