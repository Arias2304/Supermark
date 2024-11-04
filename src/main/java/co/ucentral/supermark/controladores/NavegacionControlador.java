package co.ucentral.supermark.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacionControlador {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "productos";
    }

    @GetMapping("/proveedores")
    public String proveedores() {
        return "proveedores";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}

