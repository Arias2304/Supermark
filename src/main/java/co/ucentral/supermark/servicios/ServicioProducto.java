package co.ucentral.supermark.servicios;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.repositorios.ProductoRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioProducto {

    private static final Logger log = LogManager.getLogger(ServicioProducto.class);
    private final ProductoRepositorio productoRepositorio;

    public List<Producto> obtenerTodos() {
        log.info("Obteniendo todos los productos");
        return (List<Producto>) productoRepositorio.findAll();
    }

    public void guardar(Producto producto) {
        log.info("Guardando producto con código: {}", producto.getCodigo());
        productoRepositorio.save(producto);
    }

    public Producto buscarPorCodigo(String codigo) {
        log.info("Buscando producto con código: {}", codigo);
        Optional<Producto> producto = productoRepositorio.findById(codigo);
        return producto.orElse(null);
    }

    public boolean borrar(Producto producto) {
        try {
            log.info("Eliminando producto con código: {}", producto.getCodigo());
            productoRepositorio.delete(producto);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar el producto: {}", e.getMessage());
            return false;
        }
    }

    public boolean borrarPorCodigo(String codigo) {
        try {
            log.info("Eliminando producto con código: {}", codigo);
            productoRepositorio.deleteById(codigo);
            return true;
        } catch (Exception e) {
            log.error("Error al eliminar el producto con código {}: {}", codigo, e.getMessage());
            return false;
        }
    }

    public boolean reducirCantidad(String codigo, int cantidadVendida) {
        Producto producto = buscarPorCodigo(codigo);
        if (producto != null && producto.getCantidad() >= cantidadVendida) {
            producto.setCantidad(producto.getCantidad() - cantidadVendida);
            guardar(producto);
            log.info("Reduciendo cantidad del producto con código: {}. Nueva cantidad: {}", codigo, producto.getCantidad());
            return true;
        } else {
            log.warn("Cantidad insuficiente para el producto con código: {}", codigo);
            return false;
        }
    }

    public boolean validarDisponibilidad(String codigo, int cantidadRequerida) {
        Producto producto = buscarPorCodigo(codigo);
        return producto != null && producto.getCantidad() >= cantidadRequerida;
    }
}
