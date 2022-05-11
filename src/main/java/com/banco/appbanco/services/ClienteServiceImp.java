package com.banco.appbanco.services;

import com.banco.appbanco.repositories.ClienteRepository;
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
}