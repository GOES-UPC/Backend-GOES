package com.simplife.skip.services;


import com.simplife.skip.models.Solicitud;
import com.simplife.skip.payload.requests.SolicitudRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SolicitudService {

    public Solicitud procesarSolicitud(SolicitudRequest solicitud) throws Exception;

    @Transactional
    public int actualizarEstadoPasajero(Long solicitudId, Long pasajeroId, String estado) throws Exception;

    public List<Solicitud> listarSolicitudesPorUsuario(Long usuarioId) throws Exception;


    public List<Solicitud> listarSolicitudesPorConductor(Long conductorId) throws Exception;

}
