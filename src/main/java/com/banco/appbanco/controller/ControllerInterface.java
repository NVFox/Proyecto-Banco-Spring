package com.banco.appbanco.controller;

import com.banco.appbanco.entities.Tabla;

import org.springframework.ui.Model;

public interface ControllerInterface {
    public String listarDatos(Model modelo);

    public String renderRegistro(Model modelo);

    public String insertarDato(Tabla tabla);
}
