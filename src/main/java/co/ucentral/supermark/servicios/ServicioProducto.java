package co.ucentral.supermark.servicios;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.repositorios.ProductoRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Optional<Producto> producto = productoRepositorio.findById(Integer.valueOf(codigo));
        return producto.orElse(null);
    }


    public void borrarPorCodigo(int codigo) {
        try {
            log.info("Eliminando producto con código: {}", codigo);
            productoRepositorio.deleteById(Integer.valueOf(String.valueOf(codigo)));
        } catch (Exception e) {
            log.error("Error al eliminar el producto con código {}: {}", codigo, e.getMessage());
        }
    }

    public List<Producto> obtenerTodosConAlertas() {
        List<Producto> productos = (List<Producto>) productoRepositorio.findAll();
        LocalDate hoy = LocalDate.now();

        productos.forEach(producto -> {
            if (producto.getCantidad() <= 5 || producto.getVenc().isBefore(hoy.plusDays(5))) {
                producto.setAlerta(true);
            }
        });

        return productos;
    }
    @Autowired
    private ProductoRepositorio productoRepository;



    public List<Producto> listarProductos() {
        return (List<Producto>) productoRepository.findAll();
    }


}
