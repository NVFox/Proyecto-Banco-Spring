package com.banco.appbanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.banco.appbanco.entities.Credito;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.services.CreditoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerCredito implements ControllerInterface {
    @Autowired
    CreditoService servicio;

    private static final String REDIRECT_URL = "redirect:/creditos";

    @GetMapping("creditos")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("objeto", new Credito());
        modelo.addAttribute("objetos", servicio.listarCreditos());
        return "formulario";
    }

    @PostMapping("creditos/registro")
    public String insertarDato(@ModelAttribute("objeto") Credito tabla) {
        servicio.guardarCredito(tabla);
        return REDIRECT_URL;
    }

    @PostMapping("creditos/actualizar")
    public String actualizarDato(@ModelAttribute("objeto") Credito tabla) {
        servicio.actualizarCredito(tabla);
        return REDIRECT_URL;
    }

    @GetMapping("creditos/eliminar/{id}")
    public String eliminarDato(@PathVariable String id) {
        servicio.eliminarCredito(id);
        return REDIRECT_URL;
    }

    @GetMapping("creditos/tabla")
    @ResponseBody
    public List<Tabla> listarDatosJSON() {
        List<Tabla> lista = new ArrayList<>();
        lista.addAll(servicio.listarCreditos());
        return lista;
    }
}
