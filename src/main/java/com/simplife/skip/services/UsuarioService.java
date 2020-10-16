package com.simplife.skip.services;


import com.simplife.skip.models.Usuario;
import com.simplife.skip.payload.requests.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    //Métodos CRUD
    Usuario insertarUsuario(Usuario usuario) throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

    UsuarioResponse verPerfil(Long id) throws Exception;


}
