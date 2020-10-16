package com.simplife.skip.repositories;

import com.simplife.skip.models.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT p FROM Parada p WHERE p.latitud = ?1 AND p.longitud = ?2")
    Parada buscarPorLatYLong(double latitud, double longitud);
}
