package com.banco.appbanco.controller;

import java.util.List;

import com.banco.appbanco.entities.Tabla;

import org.springframework.ui.Model;

public interface ControllerInterface {
    public String listarDatos(Model modelo);

    public List<Tabla> listarDatosJSON();
}
