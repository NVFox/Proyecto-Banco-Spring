package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Cuenta;
import com.banco.appbanco.repositories.CuentaRepository;
import com.banco.appbanco.utilities.Accessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    CuentaRepository repository;

    @Override
    public List<Cuenta> listarCuentas() {
        return repository.findAll();
    }

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
       return repository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> encontrarCuentaPorCodigo(String id) {
        return repository.findById(id);
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        Optional<Cuenta> data = encontrarCuentaPorCodigo(cuenta.getCodigo());
        if (data.isPresent()) {
            Cuenta prevData = data.get();
            Accessors accessorUtility = new Accessors();
            accessorUtility.actualizarObjeto(prevData, cuenta);
            return repository.save(prevData);
        }
        return null;
    }

    @Override
    public void eliminarCuenta(String codigo) {
        repository.deleteById(codigo);
    }
    
}
