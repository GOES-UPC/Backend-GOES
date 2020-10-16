package com.simplife.skip.services;

import com.simplife.skip.models.Reporte;
import com.simplife.skip.payload.requests.ReportRequest;

import java.util.List;

public interface ReporteService {

    Reporte publicarReporte(ReportRequest nuevoReporte, Long usuarioId, Long viajeId) throws Exception;
    List<Reporte> listarReportePorViaje(Long viajeId) throws Exception;
}
