package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Cuenta;

public interface CuentaService {
    public List<Cuenta> listarCuentas();

    public Cuenta guardarCuenta(Cuenta cuenta);

    public Optional<Cuenta> encontrarCuentaPorCodigo(String id);

    public Cuenta actualizarCuenta(Cuenta cuenta);

    public void eliminarCuenta(String codigo);
}
