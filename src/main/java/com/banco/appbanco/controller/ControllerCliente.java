package com.banco.appbanco.controller;

import com.banco.appbanco.entities.Cliente;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.repositories.ClienteRepository;
import com.banco.appbanco.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerCliente implements ControllerInterface {
    
    @Autowired
    ClienteService servicio;

    @Autowired
    ClienteRepository repository;

    @GetMapping("clientes")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("clientes", servicio.listarClientes());
        return "clientes";
    }

    @GetMapping("clientes/nuevo")
    @Override
    public String renderRegistro(Model modelo) {
        modelo.addAttribute("clienteInsert", new Cliente());
        return "insertarCliente";
    }

    @PostMapping("clientes/nuevo")
    @Override
    public String insertarDato(Tabla tabla) {
        Cliente cliente = (Cliente) tabla;
        repository.saveAndFlush(cliente);
        return "redirect:/clientes";
    }
}
