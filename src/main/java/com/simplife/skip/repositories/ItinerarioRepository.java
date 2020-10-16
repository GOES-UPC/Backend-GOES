package com.simplife.skip.repositories;

import com.simplife.skip.models.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

   @Query("UPDATE Itinerario i SET i.numOrden = ?1 WHERE i.id = ?2")
    int actualizarNumeroDeOrden(int numOrden, Long itinerarioId);

   @Query("SELECT i FROM Itinerario i JOIN Ruta r ON r.id = i.ruta.id JOIN Viaje v ON v.ruta.id = r.id WHERE v.id = ?1")
    List<Itinerario> listarItinerariosPorViaje(Long viajeId);
}
