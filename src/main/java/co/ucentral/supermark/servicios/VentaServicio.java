package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.persistencia.repositorios.ClienteRepositorio;
import co.ucentral.supermark.persistencia.repositorios.ProductoRepositorio;
import co.ucentral.supermark.persistencia.repositorios.VentaRepositorio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class VentaServicio {

    private static final Logger log = LogManager.getLogger(VentaServicio.class);

    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public List<Venta> obtenerTodas() {
        log.info("Obteniendo todas las ventas");
        return ventaRepositorio.findAll();
    }
    public List<Producto> obtenerProductosPorIds(List<String> productIds) {
        return (List<Producto>) productoRepositorio.findAllById(productIds);
    }


    public void guardarVenta(Venta venta) {
        log.info("Guardando nueva venta");
        venta.setFechaVenta(LocalDateTime.now());
        venta.setTotal(calcularTotal(venta.getProductos()));
        ventaRepositorio.save(venta);
    }

    public Venta buscarPorId(Long id) {
        log.info("Buscando venta con ID: {}", id);
        return ventaRepositorio.findById(id).orElse(null);
    }

    public Cliente obtenerClientePorId(int clienteId) {
        return clienteRepositorio.findById(clienteId).orElse(null);
    }

    public List<Producto> obtenerTodosProductos() {
        return (List<Producto>) productoRepositorio.findAll();
    }

    private Double calcularTotal(List<Producto> productos) {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}
