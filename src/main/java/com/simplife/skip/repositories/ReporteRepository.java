package com.simplife.skip.repositories;

import com.simplife.skip.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    @Query("SELECT r FROM Reporte r WHERE r.viaje.id = ?1")
    List<Reporte> listarReportesPorViaje(Long viajeId);

}
