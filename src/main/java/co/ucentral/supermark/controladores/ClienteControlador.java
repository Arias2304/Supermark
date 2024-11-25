package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteServicio.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{ID}")
    public String editarCliente(@PathVariable int ID, Model model) {
        Cliente cliente = clienteServicio.buscarPorId(ID);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        return "clientes";
    }

    @GetMapping("/eliminar/{ID}")
    public String eliminarCliente(@PathVariable int ID) {
        clienteServicio.borrarPorId(ID);
        return "redirect:/clientes";
    }
}
