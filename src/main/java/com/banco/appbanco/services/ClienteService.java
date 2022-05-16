package com.banco.appbanco.services;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.entities.Cliente;

public interface ClienteService {
    
    public List<Cliente> listarClientes();

    public Cliente guardarCliente(Cliente cliente);

    public Optional<Cliente> encontrarClientePorDocumento(long id);

    public Cliente actualizarCliente(Cliente cliente);

    public void eliminarCliente(long id);

}
