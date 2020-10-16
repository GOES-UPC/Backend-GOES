package com.simplife.skip.repositories;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.models.Parada;
import com.simplife.skip.payload.requests.ViajeInicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v INNER JOIN Solictud s ON v.id = s.viaje.id INNER JOIN Usuario u ON u.id = s.pasajero.id" +
            " WHERE u.id = ?1 ORDER BY v.fechaViaje DESC")
    List<Viaje> listarPorPasajero(Long usuarioPasajeroId);


    @Query("SELECT v FROM Viaje v INNER JOIN Usuario u ON v.conductor.id = u.id" +
            " WHERE u.id = ?1 ORDER BY v.fechaViaje DESC")
    List<Viaje> listarPorConductor(Long usuarioConductorId);


    @Modifying
    @Query("UPDATE Viaje v SET v.fechaViaje = ?1 WHERE v.id = ?2")
    int actualizarFechaViaje(LocalDate fecha, Long viajeId);

    @Modifying
    @Query("UPDATE Viaje v SET v.estadoViaje = ?1 WHERE v.id = ?2")
    int actualizarEstadoViaje(String estado, Long viajeId);

    @Query("SELECT u FROM Usuario u JOIN Solicitud s ON s.pasajero.id = u.id " +
            "JOIN Viaje v ON v.id = s.viaje.id WHERE v.id = ?1")
    List<Usuario> listarPasajerosRegistradosDelViaje(Long viajeId);

    @Query("UPDATE Viaje v SET v.numeroPasajeros = v.numeroPasajeros + 1 WHERE v.id = ?1")
    int actualizarNumeroPasajeros(Long viajeId);

   /* @Query("SELECT v FROM Viaje v JOIN Ruta r ON r.id = v.ruta.id "
    + "JOIN Itinerario i ON i.ruta.id = r.id")*/
    @Query("SELECT v FROM Usuario u JOIN Viaje v ON u.id = v.conductor.id\n" +
            "JOIN Ruta r ON r.id = v.ruta.id order by v.id desc")
    List<Viaje> listarViajesInicio() throws Exception;

    @Query("SELECT p FROM Parada p JOIN Itinerario i ON i.parada.id = p.id " +
            "JOIN Ruta r ON i.ruta.id = r.id JOIN Viaje v ON v.ruta.id = r.id WHERE v.id = ?1" +
            " ORDER BY p.id asc")
    List<Parada> listarParadasPorViajeId(Long viajeId) throws Exception;


}
