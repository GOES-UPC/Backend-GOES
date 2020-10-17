package com.simplife.skip.controllers;


import com.simplife.skip.models.Solicitud;
import com.simplife.skip.models.Usuario;
import com.simplife.skip.payload.requests.SolicitudRequest;
import com.simplife.skip.services.SolicitudService;
import com.simplife.skip.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/auth/skip/solicitudes")
public class SolicitudController {

    private SolicitudService solicitudService;

    private UsuarioService usuarioService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService){
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public Solicitud procesarSolicitud(@RequestBody SolicitudRequest solicitud) throws Exception{
        return this.solicitudService.procesarSolicitud(solicitud);
    }

    @PutMapping
    public int actualizarEstadoPasajero(@RequestParam("viajeId") Long viajeId,
                                        @RequestParam("pasajeroId") Long pasajeroId,
                                        @RequestParam("estado") String estado) throws Exception{
        return this.solicitudService.actualizarEstadoPasajero(viajeId,pasajeroId,estado);
    }

    @PutMapping("pasajeros/endestino")
    public int actualizarPasajerosEnDestino(@RequestParam("viajeId") Long viajeId) throws Exception{
        return this.solicitudService.actualizarPasajerosEnDestino(viajeId);
    }

    @GetMapping("/usuario/{id}")
    public List<Solicitud> mostrarSolicitudesPorUsuario(@PathVariable("id") Long usuarioId) throws Exception{
        return this.solicitudService.listarSolicitudesPorUsuario(usuarioId);
    }

    @GetMapping("/conductor/{id}")
    public List<Solicitud> mostrarSolicitudesPorConductor(@PathVariable("id") Long conductorId) throws Exception{
        return this.solicitudService.listarSolicitudesPorConductor(conductorId);
    }

}
