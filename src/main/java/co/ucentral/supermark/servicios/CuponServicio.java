package co.ucentral.supermark.servicios;

import co.ucentral.supermark.persistencia.entidades.Cupon;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.repositorios.CuponRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CuponServicio {

    @Autowired
    private ServicioProducto servicioProducto;

    @Autowired
    private CuponRepositorio cuponRepositorio;


    public List<Producto> obtenerProductosConDescuento(int limiteStock, int diasVencimiento) {
        List<Producto> productos = servicioProducto.listarProductos();
        List<Producto> productosConDescuento = new ArrayList<>();

        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = hoy.plusDays(diasVencimiento);

        for (Producto producto : productos) {
            if (producto.getCantidad() < limiteStock ||
                    (producto.getVenc() != null && producto.getVenc().isBefore(fechaLimite))) {
                productosConDescuento.add(producto);
            }
        }
        return productosConDescuento;
    }

    public double calcularDescuento(Producto producto, double porcentajeDescuento) {
        return producto.getPrecio() * (porcentajeDescuento / 100);
    }

    public List<Cupon> obtenerCupones() {
        List<Producto> productos = servicioProducto.listarProductos();
        List<Cupon> cupones = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        LocalDate fechaLimite = hoy.plusDays(7);

        for (Producto producto : productos) {
            if (producto.getCantidad() < 10 ||
                    (producto.getVenc() != null && producto.getVenc().isBefore(fechaLimite))) {
                Cupon cupon = new Cupon();
                cupon.setProducto(producto);
                cupon.setDescuento(producto.getPrecio() * 0.2); // 20% de descuento
                cupon.setPrecioFinal(producto.getPrecio() - cupon.getDescuento());
                cupones.add(cupon);
            }
        }
        return cupones;
    }

    public Optional<Cupon> obtenerCuponPorCodigo(String codigo) {
        return cuponRepositorio.findByCodigo(codigo);
    }
}
