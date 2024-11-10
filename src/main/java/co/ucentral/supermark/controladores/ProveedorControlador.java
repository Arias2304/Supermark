package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Proveedor;
import co.ucentral.supermark.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
@AllArgsConstructor
public class ProveedorControlador {

    private final ProveedorServicio proveedorServicio;

    @GetMapping
    public String listarProveedores(Model model) {
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos());
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorServicio.guardar(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{nit}")
    public String editarProveedor(@PathVariable int nit, Model model) {
        Proveedor proveedor = proveedorServicio.buscarPorNit(nit);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos());
        return "proveedores";
    }

    @GetMapping("/eliminar/{nit}")
    public String eliminarProveedor(@PathVariable int nit) {
        proveedorServicio.borrarPorNit(nit);
        return "redirect:/proveedores";
    }
}
