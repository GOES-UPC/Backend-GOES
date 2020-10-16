package com.simplife.skip.controllers;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.UsuarioResponse;
import com.simplife.skip.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/skip/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ApiOperation(value = "Find all users", notes = "Return all users" )
    public List<Usuario> visualizarUsuarios() throws Exception{
        return this.usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse verPerfil(final @PathVariable Long id) throws Exception{
        return this.usuarioService.verPerfil(id);
    }


    /*@PostMapping("/registro")
    public Usuario registrarUsuarioPrueba(@RequestBody Usuario usuario) throws Exception{
        return this.usuarioService.insertarUsuario(usuario);
    }*/

}