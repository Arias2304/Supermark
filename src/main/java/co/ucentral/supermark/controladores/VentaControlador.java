package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.persistencia.entidades.Venta;
import co.ucentral.supermark.servicios.ClienteServicio;
import co.ucentral.supermark.servicios.ServicioProducto;
import co.ucentral.supermark.servicios.VentaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaControlador {

    private final VentaServicio ventaServicio;
    private final ClienteServicio clienteServicio;
    private final ServicioProducto productoServicio;


    @GetMapping("/nueva")
    public String mostrarFormularioVenta(Model model) {
        List<Cliente> clientes = clienteServicio.obtenerTodos();
        List<Producto> productos = productoServicio.obtenerTodos();

        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        model.addAttribute("venta", new Venta());

        return "registroVentas";
    }


    @PostMapping("/registrar")
    public String registrarVenta(@ModelAttribute Venta venta,
                                 @RequestParam List<Integer> productosIds,
                                 @RequestParam Integer clienteId,
                                 Model model) {
        Cliente cliente = clienteServicio.buscarPorId(clienteId);
        if (cliente == null) {
            model.addAttribute("error", "Cliente no encontrado");
            return "registroVentas";
        }

        List<Producto> productosSeleccionados = productosIds.stream()
                .map(productoServicio::buscarPorCodigo)
                .toList();

        double totalVenta = productosSeleccionados.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        venta.setFecha(new Date());
        venta.setProductosVendidos(productosSeleccionados);
        venta.setTotalVenta(totalVenta);

        ventaServicio.guardar(venta);


        productosSeleccionados.forEach(producto -> {
            producto.setCantidad(producto.getCantidad() - 1);
            productoServicio.guardar(producto);
        });

        return "redirect:/ventas";
    }


    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaServicio.obtenerTodas();
        model.addAttribute("ventas", ventas);
        return "consultaVentas";
    }
    @GetMapping("/ventas")
    public String mostrarVentas(Model model) {
        List<Venta> ventas = ventaServicio.obtenerTodas();
        model.addAttribute("ventas", ventas);
        return "ventas"; // Nombre del HTML
    }
    @PostMapping("/ventas/guardar")
    public String guardarVenta(@ModelAttribute Venta venta, @RequestParam Integer clienteId,
                               @RequestParam List<Integer> productosIds, Model model) {
        Cliente cliente = clienteServicio.buscarPorId(clienteId);
        List<Producto> productos = productosIds.stream()
                .map(productoServicio::buscarPorCodigo)
                .collect(Collectors.toList());
        venta.setCliente(cliente);
        venta.setProductosVendidos(productos);
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
        venta.setTotalVenta(total);
        ventaServicio.guardar(venta);
        return "redirect:/ventas";
    }


}

