package com.banco.appbanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.banco.appbanco.entities.Usuario;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerUsuario implements ControllerInterface {

    @Autowired
    UsuarioService servicio;

    private static final String REDIRECT_URL = "redirect:/usuarios";

    @GetMapping("usuarios")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("objeto", new Usuario());
        modelo.addAttribute("objetos", servicio.listarUsuarios());
        return "formulario";
    }

    @PostMapping("usuarios/registro")
    public String insertarDato(@ModelAttribute("objeto") Usuario tabla) {
        servicio.guardarUsuario(tabla);
        return REDIRECT_URL;
    }

    @PostMapping("usuarios/actualizar")
    public String actualizarDato(@ModelAttribute("objeto") Usuario tabla) {
        servicio.actualizarUsuario(tabla);
        return REDIRECT_URL;
    }

    @GetMapping("usuarios/eliminar/{id}")
    @Override
    public String eliminarDato(@PathVariable Long id) {
        servicio.eliminarUsuario(id);
        return REDIRECT_URL;
    }

    @GetMapping("usuarios/tabla")
    @ResponseBody
    @Override
    public List<Tabla> listarDatosJSON() {
        List<Tabla> lista = new ArrayList<>();
        lista.addAll(servicio.listarUsuarios());
        return lista;
    }
}
