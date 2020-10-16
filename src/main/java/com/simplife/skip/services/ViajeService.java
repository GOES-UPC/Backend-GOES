package com.simplife.skip.services;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.PasajeroEnLista;
import com.simplife.skip.payload.requests.ViajeInicio;
import com.simplife.skip.payload.requests.ViajeRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ViajeService {

    //Métodos CRUD
    Viaje insertarViaje(ViajeRequest viaje) throws Exception;

    List<Viaje> obtenerViajes() throws Exception;
        //usuarioConductorId es id de usuario de algún conductor
    List<Viaje> listarViajesPorConductor(Long usuarioConductorId) throws Exception;

    //Actualizar la fecha de viaje
    @Transactional
    int actualizarFechaViaje(String fechaViaje, Long viajeId) throws Exception;

    @Transactional
    int actualizarEstadoViaje(String estado, Long viajeId) throws Exception;

    List<Usuario> listarPasajerosPorViaje(Long viajeId) throws Exception;

    Viaje listarViajePorId(Long viajeId) throws Exception;

    List<ViajeInicio> listarViajesInicio() throws Exception;

<<<<<<< HEAD
    List<PasajeroEnLista> listarPasajerosPorViajeId(Long viajeId) throws Exception;
=======
    List<Viaje> listarPorPasajero(Long usuarioPasajeroId) throws Exception;
>>>>>>> d29ce9f5d01540f0eed780015d56424188a54333
}
