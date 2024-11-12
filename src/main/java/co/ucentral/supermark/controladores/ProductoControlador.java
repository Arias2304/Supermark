package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.entidades.Proveedor;
import co.ucentral.supermark.servicios.ServicioProducto;
import co.ucentral.supermark.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoControlador {

    private final ServicioProducto productoServicio;
    private final ProveedorServicio proveedorServicio;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("misproductos", productoServicio.obtenerTodos());
        model.addAttribute("producto", new Producto());
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos());
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoServicio.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{codigo}")
    public String editarProducto(@PathVariable int codigo, Model model) {
        Producto producto = productoServicio.buscarPorCodigo(String.valueOf(codigo));
        model.addAttribute("producto", producto);
        model.addAttribute("misproductos", productoServicio.obtenerTodos());
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos()); // Añadir lista de proveedores
        return "productos";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarProducto(@PathVariable int codigo) {
        productoServicio.borrarPorCodigo(String.valueOf(codigo));
        return "redirect:/productos";
    }
}
