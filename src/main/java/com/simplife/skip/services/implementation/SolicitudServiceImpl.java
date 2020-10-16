package com.simplife.skip.services.implementation;

import com.simplife.skip.models.*;
import com.simplife.skip.payload.requests.PasajeroEnLista;
import com.simplife.skip.repositories.*;
import com.simplife.skip.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simplife.skip.payload.requests.SolicitudRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
class SolicitudServiceImpl implements SolicitudService {

    private SolicitudRepository solicitudRepository;
    private ParadaRepository paradaRepository;
    private UsuarioRepository usuarioRepository;
    private ViajeRepository viajeRepository;
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                ParadaRepository paradaRepository,
                                UsuarioRepository usuarioRepository,
                                ViajeRepository viajeRepository,
                                ItinerarioRepository itinerarioRepository){
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
        this.viajeRepository = viajeRepository;
        this.paradaRepository = paradaRepository;
        this.itinerarioRepository = itinerarioRepository;
    }

    @Override
    public Solicitud procesarSolicitud(SolicitudRequest solicitud) throws Exception{
        String mensaje = solicitud.getMensaje();
        Long pasajeroId = solicitud.getPasajeroId();
        Long viajeId = solicitud.getViajeId();
        Parada parada = solicitud.getPuntoEncuentro();
        Solicitud solicitudNueva = new Solicitud();
        try{
            /*Parada parada = this.paradaRepository.findById(paradaId).get();
            if (parada == null){
                parada = this.paradaRepository.save(parada);
            }*/
            Parada auxParada = this.paradaRepository.buscarPorLatYLong(parada.getLatitud(), parada.getLongitud());
            if(auxParada == null){
                auxParada = this.paradaRepository.save(parada);
            }
            solicitudNueva.setParada(auxParada);

            Usuario pasajero = this.usuarioRepository.findById(pasajeroId).get();
            Viaje viaje = this.viajeRepository.findById(viajeId).get();
            solicitudNueva = new Solicitud(mensaje, pasajero, viaje, parada);

            DateTimeFormatter dtfFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate fechaSolicitud = LocalDate.parse(LocalDate.now().toString(), dtfFecha);
            DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
            LocalTime horaSolicitud = LocalTime.parse(LocalTime.now().toString(), dtfHora);

            solicitudNueva.setFechaSolicitud(fechaSolicitud);
            solicitudNueva.setHoraSolicitud(horaSolicitud);
            solicitudNueva.setEstadoPasajero("En lista");
            solicitudNueva.setEstadoTabla(true);
            solicitudNueva = this.solicitudRepository.save(solicitudNueva);
            //this.viajeRepository.actualizarNumeroPasajeros(solicitudNueva.getViaje().getId());
        }catch(Exception e){
            throw e;
        }


        return solicitudNueva;
    }

    @Transactional
    public int actualizarEstadoPasajero(Long solicitudId, Long pasajeroId, String estado) throws Exception{
        return this.actualizarEstadoPasajero(solicitudId, pasajeroId, estado);
    }

    @Override
    public List<Solicitud> listarSolicitudesPorUsuario(Long usuarioId) throws Exception{
        return this.solicitudRepository.listarSolicitudesPorUsuario(usuarioId);
    }

    @Override
    public List<Solicitud> listarSolicitudesPorConductor(Long conductorId) throws Exception{
        return this.solicitudRepository.listarSolicitudesPorConductor(conductorId);
    }







}