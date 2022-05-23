package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Credito;

public interface CreditoService {
    public List<Credito> listarCreditos();

    public Credito guardarCredito(Credito credito);

    public Optional<Credito> encontrarCreditoPorCodigo(String id);

    public Credito actualizarCredito(Credito credito);

    public void eliminarCredito(String codigo);
}
