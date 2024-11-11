package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Producto;
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
        log.info("Registrando una nueva venta con ID: {}", venta.getId());
        ventasRepositorio.save(venta);
    }

    public void agregarProductosAVenta(Ventas venta, List<Producto> productos) {
        log.info("Agregando productos a la venta con ID: {}", venta.getId());
        venta.getProductos().addAll(productos); // Agrega productos a la lista en la venta
        ventasRepositorio.save(venta); // Guarda la venta con la lista actualizada de productos
    }

    public Ventas buscarPorId(int id) {
        log.info("Buscando venta con ID: {}", id);
        Optional<Ventas> venta = ventasRepositorio.findById(id);
        return venta.orElse(null);
    }

    public boolean borrar(Ventas venta) {
        try {
            log.info("Eliminando venta con ID: {}", venta.getId());
            ventasRepositorio.delete(venta);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar la venta: {}", e.getMessage());
            return false;
        }
    }

    public boolean borrarPorId(int id) {
        try {
            log.info("Eliminando venta con ID: {}", id);
            ventasRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar la venta con ID {}: {}", id, e.getMessage());
            return false;
        }
    }
}
