package com.simplife.skip.controllers;

import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.PasajeroEnLista;
import com.simplife.skip.payload.requests.ViajeInicio;
import com.simplife.skip.payload.requests.ViajeRequest;
import com.simplife.skip.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/skip/viajes")
public class ViajeController {

    private ViajeService viajeService;

    @Autowired
    public ViajeController(ViajeService viajeService){
        this.viajeService = viajeService;
    }

    @PostMapping("/publicacion")
    public Viaje publicarViaje(@RequestBody ViajeRequest viaje) throws Exception{
        System.out.println("ENTROOOO");
        return this.viajeService.insertarViaje(viaje);
    }

    @GetMapping
    public List<Viaje> visualizarViajes() throws Exception{
        return this.viajeService.obtenerViajes();
    }

    @PutMapping("/{viajeId}/actualizarFecha")
    public int actualizarFechaViaje(@RequestBody String fecha,
                                    @PathVariable("viajeId") Long viajeId) throws Exception{
        return this.viajeService.actualizarFechaViaje(fecha, viajeId);
    }

    @GetMapping("/conductor/{conductorId}")
    public List<Viaje> listarViajesPorConductor(@PathVariable("conductorId") Long conductorId) throws Exception{
        return this.viajeService.listarViajesPorConductor(conductorId);
    }

    @GetMapping("/{id}")
    public Viaje listarViajesPorId(@PathVariable("id") Long viajeId) throws Exception{
        return this.viajeService.listarViajePorId(viajeId);
    }

    @Transactional
    @PutMapping("actualizar/{viajeId}")
    public int actualizarEstadoDeViaje(@PathVariable("viajeId") Long viajeId, @RequestParam("estado") String estado) throws Exception{
        return this.viajeService.actualizarEstadoViaje(estado,viajeId);
    }

    @GetMapping("/{id}/pasajeros")
    public List<PasajeroEnLista> listarPasajerosRegistradosDeViaje(@PathVariable("id") Long viajeId) throws Exception{
        return this.viajeService.listarPasajerosPorViajeId(viajeId);
    }


    @GetMapping("/busqueda")
    public List<Viaje> buscarViajes(@RequestParam(name = "puntoPartida", required = false) String puntoPartida,
                                    @RequestParam(name = "puntoDestino", required = false) String puntoDestino,
                                    @RequestParam(name = "horaPartida", required = false) String horaPartida,
                                    @RequestParam(name = "horaDestino", required = false) String horaDestino){
        return null;
    }

    @GetMapping("/inicio")
    public List<ViajeInicio> listarViajesInicio() throws Exception{
        return viajeService.listarViajesInicio();
    }

}
