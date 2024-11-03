package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Proveedor;
import co.ucentral.supermark.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
@AllArgsConstructor
public class ProveedorControlador {

    private static final Logger log = LogManager.getLogger(ProveedorControlador.class);

    private final ProveedorServicio proveedorServicio;

    @GetMapping
    public String listarProveedores(Model model) {
        log.info("Listando todos los proveedores.");
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos());
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        log.info("Guardando proveedor con NIT: {}", proveedor.getNit());
        proveedorServicio.guardar(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{nit}")
    public String editarProveedor(@PathVariable String nit, Model model) {
        log.info("Editando proveedor con NIT: {}", nit);
        Proveedor proveedor = proveedorServicio.buscarPorNit(nit);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("proveedores", proveedorServicio.obtenerTodos());
        return "proveedores";
    }

    @GetMapping("/eliminar/{nit}")
    public String eliminarProveedor(@PathVariable String nit) {
        log.info("Eliminando proveedor con NIT: {}", nit);
        proveedorServicio.borrarPorNit(nit);
        return "redirect:/proveedores";
    }
}
