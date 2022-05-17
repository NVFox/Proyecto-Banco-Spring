package com.banco.appbanco.services;

import com.banco.appbanco.entities.Usuario;

import java.util.List;
import java.util.Optional;

import com.banco.appbanco.repositories.UsuarioRepository;
import com.banco.appbanco.utilities.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    UsuarioRepository repository;

    @Override
    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> encontrarUsuarioPorDocumento(long id) {
        return repository.findById(id);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Optional<Usuario> data = encontrarUsuarioPorDocumento(usuario.getDocumento());
        if (data.isPresent()) {
            Usuario prevData = data.get();
            Accessors accessorUtility = new Accessors();
            accessorUtility.actualizarObjeto(prevData, usuario);
            return repository.save(prevData);
        }
        return null;
    }

    @Override
    public void eliminarUsuario(long id) {
        repository.deleteById(id);
    }
}
