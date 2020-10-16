package com.simplife.skip.repositories;

import com.simplife.skip.models.Solicitud;
import com.simplife.skip.models.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Modifying
    @Query("UPDATE Solicitud s SET s.estadoPasajero = ?1 where s.id = ?2 and s.pasajero.id = ?3")
    int actualizarEstadoPasajero(String estado, Long solicitudId, Long clienteId);

    @Query("SELECT s FROM Solicitud s WHERE s.pasajero.id = ?1")
    List<Solicitud> listarSolicitudesPorUsuario(Long usuarioId);

    @Query("SELECT s FROM Solicitud s JOIN Viaje v ON s.viaje.id = v.id JOIN Usuario u ON v.conductor.id = u.id Where v.conductor.id = ?1")
    List<Solicitud> listarSolicitudesPorConductor(Long conductorId);

    @Query("SELECT s FROM Solicitud s JOIN Viaje v ON s.viaje.id = v.id JOIN Usuario u ON s.pasajero.id = u.id WHERE u.id = ?1 AND v.id = ?2")
    Solicitud listarSolicitudPorPasajeroYViaje(Long pasajeroId, Long viajeId);
}
