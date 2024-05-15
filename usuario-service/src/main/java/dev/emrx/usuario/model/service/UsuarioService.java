package dev.emrx.usuario.model.service;

import dev.emrx.usuario.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Usuario getUsuario(String idUsuario);

}
