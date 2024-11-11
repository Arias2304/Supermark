package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.entidades.Ventas;
import co.ucentral.supermark.persistencia.repositorios.ProductoRepositorio;
import co.ucentral.supermark.persistencia.repositorios.VentasRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor


public class VentasProducto {

    private static final Logger log = LogManager.getLogger(VentasProducto.class);
    private final VentasRepositorio ventasRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public List<Ventas> obtenerTodas() {
        log.info("Obteniendo todas las ventas de productos");
        return (List<Ventas>) ventasRepositorio.findAll();
    }

    public void registrarVentaConProductos(Ventas venta, List<String> productosIds) {
        List<Producto> productos = new ArrayList<>();
        for (String id : productosIds) {
            Producto producto = productoRepositorio.findById(id).orElse(null);
            if (producto != null) {
                producto.setCantidad(producto.getCantidad() - 1); // Disminuye cantidad, o usa la cantidad deseada
                productoRepositorio.save(producto); // Actualiza el producto en la base de datos
                productos.add(producto);
            }
        }
        venta.setProductos(productos);
        ventasRepositorio.save(venta); // Guarda la venta con los productos asociados
    }


    public void agregarProductosAVenta(Ventas venta, List<Producto> productos) {
        log.info("Agregando productos a la venta con ID: {}", venta.getId());
        venta.getProductos().addAll(productos); 

        
        double total = venta.getProductos().stream()
                .mapToDouble(Producto::getPrecio) 
                .sum();

        venta.setPago(total); 
        ventasRepositorio.save(venta); 

        log.info("Venta actualizada con nuevo total: {}", total);
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

    public double calcularTotalVenta(List<String> productosIds) {
        double total = 0.0;
        for (String id : productosIds) {
            Producto producto = productoRepositorio.findById(id).orElse(null);
            if (producto != null) {
                total += producto.getPrecio();
            }
        }
        return total;
    }

}

