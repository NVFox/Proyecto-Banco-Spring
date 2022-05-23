package com.banco.appbanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.banco.appbanco.entities.Cuenta;
import com.banco.appbanco.entities.Tabla;
import com.banco.appbanco.services.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerCuenta implements ControllerInterface {

    @Autowired
    CuentaService servicio;

    private static final String REDIRECT_URL = "redirect:/cuentas";

    @GetMapping("cuentas")
    @Override
    public String listarDatos(Model modelo) {
        modelo.addAttribute("objeto", new Cuenta());
        modelo.addAttribute("objetos", servicio.listarCuentas());
        return "formulario";
    }

    @PostMapping("cuentas/registro")
    public String insertarDato(@ModelAttribute("objeto") Cuenta tabla) {
        servicio.guardarCuenta(tabla);
        return REDIRECT_URL;
    }

    @PostMapping("cuentas/actualizar")
    public String actualizarDato(@ModelAttribute("objeto") Cuenta tabla) {
        servicio.actualizarCuenta(tabla);
        return REDIRECT_URL;
    }

    @GetMapping("cuentas/eliminar/{id}")
    public String eliminarDato(@PathVariable String id) {
        servicio.eliminarCuenta(id);
        return REDIRECT_URL;
    }

    @GetMapping("cuentas/tabla")
    @ResponseBody
    @Override
    public List<Tabla> listarDatosJSON() {
        List<Tabla> lista = new ArrayList<>();
        lista.addAll(servicio.listarCuentas());
        return lista;
    }
}