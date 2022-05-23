package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Linea;
import com.banco.appbanco.repositories.LineaRepository;
import com.banco.appbanco.utilities.Accessors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineaServiceImp implements LineaService {

    @Autowired
    LineaRepository repository;

    @Override
    public List<Linea> listarLineas() {
        return repository.findAll();
    }

    @Override
    public Linea guardarLinea(Linea linea) {
        return repository.save(linea);
    }

    @Override
    public Optional<Linea> encontrarLineaPorCodigo(String id) {
        return repository.findById(id);
    }

    @Override
    public Linea actualizarLinea(Linea linea) {
        Optional<Linea> data = encontrarLineaPorCodigo(linea.getCodigo());
        if (data.isPresent()) {
            Linea prevData = data.get();
            Accessors accessorUtility = new Accessors();
            accessorUtility.actualizarObjeto(prevData, linea);
            return repository.save(prevData);
        }
        return null;
    }

    @Override
    public void eliminarLinea(String codigo) {
        repository.deleteById(codigo);
    }
    
}
