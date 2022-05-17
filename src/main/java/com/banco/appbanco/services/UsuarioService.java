package com.banco.appbanco.services;

import com.banco.appbanco.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario> listarUsuarios();

    public Usuario guardarUsuario(Usuario usuario);

    public Optional<Usuario> encontrarUsuarioPorDocumento(long id);

    public Usuario actualizarUsuario(Usuario usuario);

    public void eliminarUsuario(long id);

}
