package co.ucentral.supermark.servicios;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.repositorios.ProductoRepositorio;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    @AllArgsConstructor
    public class ServicioProducto {
        private static final Logger log = LogManager.getLogger(ServicioProducto.class);
        private final ProductoRepositorio productoRepositorio;

        public List<Producto> obtenerTodos() {
            return (List<Producto>) productoRepositorio.findAll();
        }

        public boolean borrar(Producto producto) {
            try {
                productoRepositorio.delete(producto);
            } catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }
            return true;
        }

    }

