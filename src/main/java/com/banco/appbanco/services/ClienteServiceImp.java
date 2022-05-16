package com.banco.appbanco.services;

import com.banco.appbanco.repositories.ClienteRepository;
import com.banco.appbanco.utilities.Accessors;

import java.util.*;

import com.banco.appbanco.entities.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Optional<Cliente> encontrarClientePorDocumento(long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        Optional<Cliente> data = encontrarClientePorDocumento(cliente.getDocumento());
        if (data.isPresent()) {
            Cliente prevData = data.get();
            Accessors accessorUtility = new Accessors();
            accessorUtility.actualizarObjeto(prevData, cliente);
            return repository.save(prevData);
        }
        return null;
    }

    @Override
    public void eliminarCliente(long id) {
        repository.deleteById(id);
    }
     
}