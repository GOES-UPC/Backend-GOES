package com.simplife.skip.repositories;

import com.simplife.skip.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findByCodigoUpc(String codigo);
    Boolean existsByCorreoUPC(String email);
    Boolean existsByCodigoUpc(String codigo);

}
