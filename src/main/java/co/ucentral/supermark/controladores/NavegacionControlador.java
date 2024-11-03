package co.ucentral.supermark.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacionControlador {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }


}

