package com.simplife.skip.services;

import com.simplife.skip.models.Reporte;

import java.util.List;

public interface ReporteService {

    Reporte publicarReporte(Reporte nuevoReporte, Long usuarioId, Long viajeId) throws Exception;
    List<Reporte> listarReportePorViaje(Long viajeId) throws Exception;
}
