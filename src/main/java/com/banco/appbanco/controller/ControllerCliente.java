package com.banco.appbanco.controller;

import com.banco.appbanco.services.ClienteService;
import com.banco.appbanco.entities.Cliente;
import com.banco.appbanco.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerCliente {
    
    @Autowired
    ClienteService servicio;

    @Autowired
    ClienteRepository repository;

    @GetMapping("clientes")
    public String listarClientes(Model modelo) {
        modelo.addAttribute("clientes", servicio.listarClientes());
        return "clientes";
    }

    @GetMapping("cliente/nuevo")
    public String renderRegistro(Model modelo) {
        modelo.addAttribute("clienteInsert", new Cliente());
        return "insertarCliente";
    }

    @PostMapping("cliente/nuevo")
    public String insertarCliente(Cliente cliente) {
        repository.saveAndFlush(cliente);
        return "redirect:/clientes";
    }
}
