package com.simplife.skip.controllers;

import com.simplife.skip.models.Reporte;
import com.simplife.skip.services.ReporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth/skip/reportes")
public class ReporteController {

    private ReporteService reporteService;

    public ReporteController(ReporteService reporteService){
        this.reporteService = reporteService;
    }

    @PostMapping
    public Reporte publicarReporte(@RequestBody Reporte nuevoReporte,
                                   @RequestParam("usuarioId") Long usuarioId,
                                   @RequestParam("viajeId") Long viajeId) throws Exception{

        return this.reporteService.publicarReporte(nuevoReporte, usuarioId, viajeId);

    }

    @GetMapping
    public List<Reporte> listarReportesPorViaje(@RequestParam("viajeId") Long viajeId) throws Exception{
        return this.listarReportesPorViaje(viajeId);
    }

}
