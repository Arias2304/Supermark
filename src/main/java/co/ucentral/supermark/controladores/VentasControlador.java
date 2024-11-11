package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Ventas;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.servicios.VentasProducto;
import co.ucentral.supermark.servicios.ServicioProducto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pageventas")
@AllArgsConstructor
public class VentasControlador {

    private final VentasProducto ventasProducto;
    private final ServicioProducto servicioProducto;

    // Método para mostrar la página de registro de ventas
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        List<Producto> productos = servicioProducto.obtenerTodos();
        model.addAttribute("productos", productos);
        model.addAttribute("venta", new Ventas());
        return "registroVentas";
    }


    @PostMapping("/guardar")
    public String registrarVenta(@ModelAttribute Ventas venta, @RequestParam List<String> productosIds, Model model) {
        double totalVenta = ventasProducto.calcularTotalVenta(productosIds); // Método en VentasProducto para calcular el total
        venta.setPago(totalVenta);
        ventasProducto.registrarVentaConProductos(venta, productosIds);

        return "redirect:/pageventas/consulta";
    }

    // Método para mostrar la página de consulta de ventas
    @GetMapping("/consulta")
    public String mostrarVentas(Model model) {
        List<Ventas> ventas = ventasProducto.obtenerTodas();
        model.addAttribute("misventas", ventas);
        return "consultaVentas";
    }
}

