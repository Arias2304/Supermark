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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VentaServicio {

    private static final Logger log = LogManager.getLogger(VentaServicio.class);

    private final VentaRepositorio ventaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final ClienteRepositorio clienteRepositorio;


    public Venta registrarVenta(Integer clienteId, List<Producto> productos) {
        double totalVenta = 0.0;


        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("Cliente no encontrado con ID: " + clienteId)
        );


        for (Producto producto : productos) {
            Optional<Producto> productoExistente = productoRepositorio.findById(Integer.valueOf(producto.getCodigo()));



        if (productoExistente.isPresent()) {
                Producto productoInventario = productoExistente.get();


                if (productoInventario.getCantidad() < producto.getCantidad()) {
                    throw new IllegalArgumentException("Inventario insuficiente para el producto: " + producto.getNombre());
                }


                productoInventario.setCantidad(productoInventario.getCantidad() - producto.getCantidad());
                productoRepositorio.save(productoInventario);


                totalVenta += producto.getCantidad() * productoInventario.getPrecio();
            } else {
                throw new IllegalArgumentException("Producto no encontrado con código: " + producto.getCodigo());
            }
        }


        Venta venta = new Venta();
        venta.setFecha(new Date());
        venta.setProductosVendidos(productos);
        venta.setTotalVenta(Double.valueOf(totalVenta));

        Venta ventaRegistrada = ventaRepositorio.save(venta);
        log.info("Venta registrada exitosamente con ID: {}", Optional.of(ventaRegistrada.getId()));

        return ventaRegistrada;
    }
    public Venta guardar(Venta venta) {
        log.info("Guardando venta con ID: {}", Optional.of(venta.getId()));
        return ventaRepositorio.save(venta);
    }

    public List<Venta> obtenerTodas() {
        log.info("Obteniendo todas las ventas");
        return (List<Venta>) ventaRepositorio.findAll();
    }
    

}
