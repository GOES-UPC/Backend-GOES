package com.simplife.skip.repositories;

import com.simplife.skip.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    @Query("SELECT u.id FROM Usuario u JOIN Cuenta c ON u.cuenta.Id = c.Id WHERE c.Id = ?1")
    Long obtenerIdPorCuenta(Long cuentaId);

    @Query("SELECT ic.facebook FROM Usuario u JOIN Cuenta c ON u.cuenta.Id = c.Id JOIN InformacionConductor ic ON u.id = ic.usuario.id WHERE c.Id = ?1")
    String obtenerFbPorCuenta(Long usuarioId);
}

