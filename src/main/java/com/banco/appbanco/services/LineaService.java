package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Linea;

public interface LineaService {
    public List<Linea> listarLineas();

    public Linea guardarLinea(Linea linea);

    public Optional<Linea> encontrarLineaPorCodigo(String id);

    public Linea actualizarLinea(Linea linea);

    public void eliminarLinea(String codigo);
}
