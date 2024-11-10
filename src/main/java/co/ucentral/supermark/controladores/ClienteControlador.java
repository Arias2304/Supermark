package co.ucentral.supermark.controladores;

import co.ucentral.supermark.persistencia.entidades.Cliente;
import co.ucentral.supermark.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable int id, Model model) {
        Cliente cliente = clienteServicio.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteServicio.obtenerTodos());
        return "clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        clienteServicio.borrarPorId(id);
        return "redirect:/clientes";
    }
}
