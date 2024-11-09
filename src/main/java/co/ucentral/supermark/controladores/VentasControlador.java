package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Ventas;
import co.ucentral.supermark.servicios.VentasProducto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registroVentas")
@AllArgsConstructor

public class VentasControlador {

    private final VentasProducto ventasProducto;
}
