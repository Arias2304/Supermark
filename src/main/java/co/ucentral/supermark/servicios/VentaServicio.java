package co.ucentral.supermark.servicios;
import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.persistencia.repositorios.VentaRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VentaServicio {

    private static final Logger log = LogManager.getLogger(VentaServicio.class);
    private final VentaRepositorio ventaRepositorio;

    public List<Venta> obtenerTodas() {
        log.info("Obteniendo todas las ventas");
        return (List<Venta>) ventaRepositorio.findAll();
    }


    public void guardar(Venta venta) {
       log.info("Guardando venta por codigo: {}", venta.getId());
       ventaRepositorio.save(venta);
    }


    public Venta buscarPorId(int id) {
        log.info("Buscando venta con ID: {}", id);
        Optional<Venta> venta = ventaRepositorio.findById(id);
        return venta.orElse(null);
    }
    public void eliminar(int id) {
        try {
            log.info("Eliminando venta con ID: {}", id);
            ventaRepositorio.deleteById(id);
        } catch (Exception e) {
            log.error("Error al eliminar la venta con ID {}: {}", id, e.getMessage());
        }
    }

    public List<Venta> obtenerVentasPorCliente() {
        return (List<Venta>) ventaRepositorio.findAll();
    }
}
