package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Credito;
import com.banco.appbanco.repositories.CreditoRepository;
import com.banco.appbanco.utilities.Accessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditoServiceImp implements CreditoService {

    @Autowired
    CreditoRepository repository;

    @Override
    public List<Credito> listarCreditos() {
        return repository.findAll();
    }

    @Override
    public Credito guardarCredito(Credito credito) {
        return repository.save(credito);
    }

    @Override
    public Optional<Credito> encontrarCreditoPorCodigo(String id) {
        return repository.findById(id);
    }

    @Override
    public Credito actualizarCredito(Credito credito) {
        Optional<Credito> data = encontrarCreditoPorCodigo(credito.getCodigo());
        if (data.isPresent()) {
            Credito prevData = data.get();
            Accessors accessorUtility = new Accessors();
            accessorUtility.actualizarObjeto(prevData, credito);
            return repository.save(prevData);
        }
        return null;
    }

    @Override
    public void eliminarCredito(String codigo) {
        repository.deleteById(codigo);
    }
    
}
