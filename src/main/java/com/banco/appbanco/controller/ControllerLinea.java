package com.banco.appbanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.banco.appbanco.entities.Linea;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.services.LineaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerLinea implements ControllerInterface {
    
    @Autowired
    LineaService servicio;

    private static final String REDIRECT_URL = "redirect:/lineas";

    @GetMapping("lineas")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("objeto", new Linea());
        modelo.addAttribute("objetos", servicio.listarLineas());
        return "formulario";
    }

    @PostMapping("lineas/registro")
    public String insertarDato(@ModelAttribute("objeto") Linea tabla) {
        servicio.guardarLinea(tabla);
        return REDIRECT_URL;
    }

    @PostMapping("lineas/actualizar")
    public String actualizarDato(@ModelAttribute("objeto") Linea tabla) {
        servicio.actualizarLinea(tabla);
        return REDIRECT_URL;
    }

    @GetMapping("lineas/eliminar/{id}")
    public String eliminarDato(@PathVariable String id) {
        servicio.eliminarLinea(id);
        return REDIRECT_URL;
    }

    @GetMapping("lineas/tabla")
    @ResponseBody
    @Override
    public List<Tabla> listarDatosJSON() {
        List<Tabla> lista = new ArrayList<>();
        lista.addAll(servicio.listarLineas());
        return lista;
    }
}
