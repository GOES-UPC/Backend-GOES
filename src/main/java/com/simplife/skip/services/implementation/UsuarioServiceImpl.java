package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.UsuarioResponse;
import com.simplife.skip.repositories.UsuarioRepository;
import com.simplife.skip.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception{
        List<Usuario> listaUsuarios;
        try{
            listaUsuarios = this.usuarioRepository.findAll();
        }catch(Exception e){
            throw e;
        }
        return listaUsuarios;
    }

    @Override
    public Usuario insertarUsuario(Usuario usuario) throws Exception {
        Usuario nuevoUsuario;
        try{
            nuevoUsuario = this.usuarioRepository.save(usuario);
        }catch(Exception e){
            throw e;
        }
        return nuevoUsuario;
    }

    @Override
    public UsuarioResponse verPerfil(Long id) throws Exception{
        Usuario aux = this.usuarioRepository.findById(id).get();
        String facebook = this.usuarioRepository.obtenerFbPorCuenta(aux.getId());
        UsuarioResponse usuarioReponse = new UsuarioResponse(aux.getDni(),aux.getNombres(),aux.getApellidos(), aux.getSede(), aux.getCuenta(), facebook,aux.getImagen());
        //String dni, String nombres, String apellidos, String sede, Cuenta cuenta, String facebook, String imagen
        return usuarioReponse;
    }


}
