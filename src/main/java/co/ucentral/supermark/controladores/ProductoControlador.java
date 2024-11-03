package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.servicios.ServicioProducto;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoControlador {

    private static final Logger log = LogManager.getLogger(ProductoControlador.class);

    private final ServicioProducto productoServicio;

    @GetMapping
    public String listarProductos(Model model) {
        log.info("Listando todos los productos.");
        model.addAttribute("productos", productoServicio.obtenerTodos());
        model.addAttribute("producto", new Producto());
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        log.info("Guardando producto con código: {}", producto.getCodigo());
        productoServicio.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{codigo}")
    public String editarProducto(@PathVariable String codigo, Model model) {
        log.info("Editando producto con código: {}", codigo);
        Producto producto = productoServicio.buscarPorCodigo(codigo);
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoServicio.obtenerTodos());
        return "productos";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarProducto(@PathVariable String codigo) {
        log.info("Eliminando producto con código: {}", codigo);
        productoServicio.borrarPorCodigo(codigo);
        return "redirect:/productos";
    }
}
