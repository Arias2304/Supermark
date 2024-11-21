package co.ucentral.supermark.controladores;
import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.servicios.ClienteServicio;
import co.ucentral.supermark.servicios.ServicioProducto;
import co.ucentral.supermark.servicios.VentaServicio;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaControlador {

    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final ServicioProducto servicioProducto;


    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaServicio.obtenerTodas());
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("productos", servicioProducto.obtenerTodos());
        return "ventas";
    }


    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta){
        ventaServicio.guardar(venta);
        return "redirect:/ventas";

    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable int id, Model model) {
        Venta venta = ventaServicio.buscarPorId(id);
        model.addAttribute("venta", venta);
        model.addAttribute("ventas", ventaServicio.obtenerTodas());
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("productos", servicioProducto.obtenerTodos());
        return "ventas";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarventa(@PathVariable int id) {
        ventaServicio.eliminar(id);
        return "redirect:/ventas";
    }

}
