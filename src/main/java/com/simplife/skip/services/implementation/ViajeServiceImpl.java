package com.simplife.skip.services.implementation;

import com.simplife.skip.models.*;
import com.simplife.skip.payload.requests.PasajeroEnLista;
import com.simplife.skip.payload.requests.ViajeInicio;
import com.simplife.skip.payload.requests.ViajeRequest;
import com.simplife.skip.repositories.*;
import com.simplife.skip.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simplife.skip.models.Parada;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ViajeServiceImpl implements ViajeService {

    private ViajeRepository viajeRepository;
    private UsuarioRepository usuarioRepository;
    private RutaRepository rutaRepository;
    private ItinerarioRepository itinerarioRepository;
    private ParadaRepository paradaRepository;
    private SolicitudRepository solicitudRepository;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository, UsuarioRepository usuarioRepository,
                            ParadaRepository paradaRepository,ItinerarioRepository itinerarioRepository,
                            RutaRepository rutaRepository, SolicitudRepository solicitudRepository){
        this.viajeRepository = viajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.paradaRepository = paradaRepository;
        this.rutaRepository = rutaRepository;
        this.itinerarioRepository = itinerarioRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public Viaje insertarViaje(ViajeRequest viaje) throws Exception {
        Viaje nuevoViaje;
        Usuario conductor;
        Parada partida = viaje.getPartida();
        Parada destino = viaje.getDestino();
        Itinerario itinerario1;
        Itinerario itinerario2;

        System.out.println(viaje.getPartida().toString());
        try {
            if (this.paradaRepository.buscarPorLatYLong(partida.getLatitud(), partida.getLongitud()) == null) {
                partida.setEstadoTabla(true);
                partida = this.paradaRepository.save(partida);
            }
            if (this.paradaRepository.buscarPorLatYLong(destino.getLatitud(), destino.getLongitud()) == null) {
                destino.setEstadoTabla(true);
                destino = this.paradaRepository.save(destino);
            }

            Ruta ruta = new Ruta(viaje.getTiempoEstimado(), viaje.isSentidoRuta(), viaje.getDistancia(), true);
            itinerario1 = new Itinerario(ruta, partida, 1);
            itinerario2 = new Itinerario(ruta, destino, 2);
            this.itinerarioRepository.save(itinerario1);
            this.itinerarioRepository.save(itinerario2);

            conductor = this.usuarioRepository.findById(viaje.getConductorId()).get();

            //Revisar esta cosa
            DateTimeFormatter dtfFechaEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaViaje = LocalDate.parse(viaje.getFechaViaje(), dtfFechaEntrada);
            String fechaViajeString = fechaViaje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            DateTimeFormatter dtfFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate nuevaFechaViaje = LocalDate.parse(fechaViajeString, dtfFecha);

            DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime nuevaHoraInicio = LocalTime.parse(viaje.getHoraInicio(), dtfHora);
            LocalTime nuevaHoraLlegada = LocalTime.parse(viaje.getHoraLlegada(), dtfHora);

            nuevoViaje = new Viaje(conductor, viaje.getDescripcion(),
                    nuevaFechaViaje, nuevaHoraInicio, nuevaHoraLlegada);
            nuevoViaje.setFechaPublicacion(LocalDate.now());

            nuevoViaje.setHoraPublicacion(LocalTime.now());
            nuevoViaje.setVisualizacionHabilitada(true);
            nuevoViaje.setEstadoViaje("PUBLICADO");
            nuevoViaje.setEstadoTabla(true);
            nuevoViaje.setRuta(ruta);
            nuevoViaje.setNumeroPasajeros(0);


            nuevoViaje = this.viajeRepository.save(nuevoViaje);
        }catch(Exception e){
            throw e;
        }
        return nuevoViaje;
    }

    @Override
    public List<Viaje> obtenerViajes() throws Exception {
        List<Viaje> listaViajes;
        try{
            listaViajes = this.viajeRepository.findAll();
        }catch(Exception e){
            throw e;
        }
        return listaViajes;
    }

    @Override
    public List<ViajeInicio> listarViajesPorConductor(Long usuarioConductorId) throws Exception {
        List<ViajeInicio> listaViajesInicio = new ArrayList<>();
        try{

            List<Viaje> listaViajesPorConductor = this.viajeRepository.listarPorConductor(usuarioConductorId);
            for(Viaje viaje: listaViajesPorConductor){
                ViajeInicio viajeInicio = this.crearViajeInicio(viaje);
                listaViajesInicio.add(viajeInicio);
            }
        }catch(Exception e){
            throw e;
        }
        return listaViajesInicio;
    }

    @Override
    public List<ViajeInicio> listarViajesPorPasajero(Long pasajeroId) throws Exception{
        List<ViajeInicio> listaViajes = new ArrayList<>();
        try{
            List<Viaje> auxViajes = this.viajeRepository.listarPorPasajero(pasajeroId);
            for(Viaje viaje: auxViajes){
                ViajeInicio viajeInicio = this.crearViajeInicio(viaje);
                listaViajes.add(viajeInicio);
            }
        }catch(Exception e){
            throw e;
        }
        return listaViajes;
    }

    @Override
    @Transactional
    public int actualizarFechaViaje(String fechaViaje, Long viajeId) throws Exception {
        int viajeActualizado;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            LocalDate nuevaFechaViaje = LocalDate.parse(fechaViaje, dtf);
            viajeActualizado = this.viajeRepository.actualizarFechaViaje(nuevaFechaViaje, viajeId);
        }catch(Exception e){
            throw e;
        }
        return viajeActualizado;
    }

    @Override
    @Transactional
    public int actualizarEstadoViaje(String estado, Long viajeId) throws Exception{
        return this.viajeRepository.actualizarEstadoViaje(estado, viajeId);
    }

    @Override
    public List<Usuario> listarPasajerosPorViaje(Long viajeId) throws Exception{
        return this.viajeRepository.listarPasajerosRegistradosDelViaje(viajeId);
    }

    @Override
    public Viaje listarViajePorId(Long viajeId) throws Exception{
        return viajeRepository.findById(viajeId).get();
    }

    @Override
    public List<ViajeInicio> listarViajesInicio() throws Exception{
        List<Viaje> viajes = this.viajeRepository.listarViajesInicio();
        List<ViajeInicio> viajesInicio = new ArrayList<>();
        for(Viaje viaje: viajes){
            ViajeInicio viajeInicio = this.crearViajeInicio(viaje);
            viajesInicio.add(viajeInicio);
        }
        return viajesInicio;

    }

    @Override
    public ViajeInicio listarViajeInicioPorId(Long viajeId) throws Exception {
        List<Viaje> viajes = this.viajeRepository.listarViajesInicio();
        ViajeInicio viajeInicio;
        for(Viaje viaje: viajes){
            if(viaje.getId() == viajeId)
            {
                viajeInicio = this.crearViajeInicio(viaje);
                return viajeInicio;
            }
        }
        return null;
    }

    @Override
    public List<PasajeroEnLista> listarPasajerosPorViajeId(Long viajeId) throws Exception{
        List<Usuario> pasajerosRegistrados = new ArrayList<>();
        List<PasajeroEnLista> pasajerosEnLista = new ArrayList<>();

        try{
            pasajerosRegistrados = this.viajeRepository.listarPasajerosRegistradosDelViaje(viajeId);
            for (Usuario pasajero: pasajerosRegistrados){
                PasajeroEnLista auxPasajero = new PasajeroEnLista();
                Solicitud auxSolicitud = this.solicitudRepository.listarSolicitudPorPasajeroYViaje(pasajero.getId(), viajeId);

                auxPasajero.setUsuarioId(pasajero.getId());
                auxPasajero.setNombres(pasajero.getNombres() + " " + pasajero.getApellidos());
                auxPasajero.setEstadoPasajero(auxSolicitud.getEstadoPasajero());
                auxPasajero.setViajeId(viajeId);
                auxPasajero.setEstadoPasajero(auxSolicitud.getEstadoPasajero());
                auxPasajero.setPuntoEncuentro(auxSolicitud.getParada().getUbicacion());
                auxPasajero.setImagen(pasajero.getImagen());
                pasajerosEnLista.add(auxPasajero);
            }
        } catch(Exception e){
            throw e;
        }
        return pasajerosEnLista;
    }


    public List<Viaje> listarPorPasajero(Long usuarioPasajeroId) throws Exception{
        List<Viaje> listaViajesPorPasajero;
        try{
            listaViajesPorPasajero = this.viajeRepository.listarPorPasajero(usuarioPasajeroId);
        }catch(Exception e){
            throw e;
        }
        return listaViajesPorPasajero;
    }

    private ViajeInicio crearViajeInicio(Viaje viaje) throws Exception{
        ViajeInicio viajeInicio = new ViajeInicio();
        try{
            List<Parada> paradas = this.viajeRepository.listarParadasPorViajeId(viaje.getId());
            viajeInicio.setId(viaje.getId());
            viajeInicio.setImagen(viaje.getConductor().getImagen());
            viajeInicio.setDescripcion(viaje.getDescripcion());
            viajeInicio.setFechaPublicacion(viaje.getFechaPublicacion().toString());
            viajeInicio.setHoraFin(viaje.getHoraLlegada().toString());
            viajeInicio.setHoraInicio(viaje.getHoraInicio().toString());
            viajeInicio.setNombres(viaje.getConductor().getNombres() + " " + viaje.getConductor().getApellidos());
            viajeInicio.setParadas(paradas);
        } catch(Exception ex){
            throw ex;
        }
        return viajeInicio;

    }
}
