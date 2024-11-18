package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Cupon;
import co.ucentral.supermark.persistencia.entidades.Producto;
import co.ucentral.supermark.servicios.CuponServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cupones")
@AllArgsConstructor
public class CuponControlador {
    private final CuponServicio cuponServicio;

    @GetMapping
    public String mostrarCupones(Model model) {
        int limiteStock = 10; // Configurable
        int diasVencimiento = 7; // Configurable
        double porcentajeDescuento = 20.0; // Configurable

        List<Producto> productosConDescuento = cuponServicio.obtenerProductosConDescuento(limiteStock, diasVencimiento);

        // Calcular descuentos y agregar al modelo
        List<Map<String, Object>> cupones = new ArrayList<>();
        for (Producto producto : productosConDescuento) {
            Map<String, Object> cupon = new HashMap<>();
            cupon.put("producto", producto);
            cupon.put("descuento", cuponServicio.calcularDescuento(producto, porcentajeDescuento));
            cupon.put("precioFinal", producto.getPrecio() - cuponServicio.calcularDescuento(producto, porcentajeDescuento));
            cupones.add(cupon);
        }

        model.addAttribute("cupones", cupones);
        model.addAttribute("porcentajeDescuento", porcentajeDescuento);
        return "cupones";
    }
    @GetMapping("/cupones")
    public String listarCupones(Model model) {
        List<Cupon> cupones = cuponServicio.obtenerCupones();
        model.addAttribute("cupones", cupones);
        return "cupones";
    }

}

