package co.ucentral.supermark.controladores;
import co.ucentral.supermark.SupermarkApplication;
import co.ucentral.supermark.servicios.ServicioProducto;
import co.ucentral.supermark.persistencia.entidades.Producto;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class ProductoControlador {


    private static final Logger log = LogManager.getLogger(SupermarkApplication.class);
    ServicioProducto servicioProducto;

    @GetMapping({"/PRODUCTOS"})
    public String obtenerTodos(Model model){
        List<Producto> listado =  servicioProducto.obtenerTodos();
        model.addAttribute("misproductos",listado);
        log.debug(" listado consultado "+listado.size());
        return "pageproductos";
    }
    public List<Producto> obtenerTodos1(){
        return servicioProducto.obtenerTodos();

    }


}