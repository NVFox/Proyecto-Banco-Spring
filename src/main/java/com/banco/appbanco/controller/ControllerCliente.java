package com.banco.appbanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.banco.appbanco.entities.Cliente;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerCliente implements ControllerInterface {
    
    @Autowired
    ClienteService servicio;

    private static final String REDIRECT_URL = "redirect:/clientes";

    @GetMapping("clientes")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("objeto", new Cliente());
        modelo.addAttribute("objetos", servicio.listarClientes());
        return "formulario";
    }

    @PostMapping("clientes/registro")
    public String insertarDato(@ModelAttribute("objeto") Cliente tabla) {
        servicio.guardarCliente(tabla);
        return REDIRECT_URL;
    }

    @PostMapping("clientes/actualizar")
    public String actualizarDato(@ModelAttribute("objeto") Cliente tabla) {
        servicio.actualizarCliente(tabla);
        return REDIRECT_URL;
    }

    @GetMapping("clientes/eliminar/{id}")
    public String eliminarDato(@PathVariable Long id) {
        servicio.eliminarCliente(id);
        return REDIRECT_URL;
    }

    @GetMapping("clientes/tabla")
    @ResponseBody
    public List<Tabla> listarDatosJSON() {
        List<Tabla> lista = new ArrayList<>();
        lista.addAll(servicio.listarClientes());
        return lista;
    }
}
