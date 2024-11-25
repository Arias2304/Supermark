package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.persistencia.repositorios.VentaRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info("Guardando venta: {}", venta);
        ventaRepositorio.save(venta);
    }

    public Venta buscarPorId(int id) {
        log.info("Buscando venta con ID: {}", id);
        return ventaRepositorio.findById((int) id).orElse(null);
    }

    public void eliminar(int id) {
        log.info("Eliminando venta con ID: {}", id);
        ventaRepositorio.deleteById((int) id);
        ventaRepositorio.deleteById(id);

    }
}
