package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.servicios.ClienteServicio;
import co.ucentral.supermark.servicios.VentaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaControlador {

    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;

    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaServicio.obtenerTodas());
        return "ventas";
    }

    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("productos", ventaServicio.obtenerTodosProductos());
        return "nueva_venta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta,
                               @RequestParam("clienteId") Integer clienteId,
                               @RequestParam("productos") List<String> productIds,
                               RedirectAttributes redirectAttributes) {
        Cliente cliente = clienteServicio.buscarPorId(clienteId);
        if (cliente != null) {
            venta.setCliente(cliente);
        }
        List<Producto> productosSeleccionados = ventaServicio.obtenerProductosPorIds(productIds);
        venta.setProductos(productosSeleccionados);
        ventaServicio.guardarVenta(venta);
        redirectAttributes.addFlashAttribute("mensaje", "Venta registrada exitosamente");
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable Long id, Model model) {
        Venta venta = ventaServicio.buscarPorId(id);
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("productos", ventaServicio.obtenerTodosProductos());
        return "nueva_venta";
    }
}
