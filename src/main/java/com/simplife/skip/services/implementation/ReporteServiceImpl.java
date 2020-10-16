package com.simplife.skip.services.implementation;

import com.simplife.skip.models.Reporte;
import com.simplife.skip.models.Usuario;
import com.simplife.skip.models.Viaje;
import com.simplife.skip.payload.requests.ReportRequest;
import com.simplife.skip.repositories.ReporteRepository;
import com.simplife.skip.repositories.UsuarioRepository;
import com.simplife.skip.repositories.ViajeRepository;
import com.simplife.skip.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private ReporteRepository reporteRepository;
    private UsuarioRepository usuarioRepository;
    private ViajeRepository viajeRepository;

    @Autowired
    public ReporteServiceImpl(ReporteRepository reporteRepository, UsuarioRepository usuarioRepository, ViajeRepository viajeRepository){
        this.reporteRepository = reporteRepository;
        this.usuarioRepository = usuarioRepository;
        this.viajeRepository = viajeRepository;
    }

    public Reporte publicarReporte(ReportRequest nuevoReporte, Long usuarioId, Long viajeId) throws Exception{
        Usuario usuario;
        Viaje viaje;
        Reporte reporte = new Reporte();
        try{
            usuario = this.usuarioRepository.findById(usuarioId).get();
            viaje = this.viajeRepository.findById(viajeId).get();
            reporte.setContenido(nuevoReporte.getMensaje());
            reporte.setValoracion(nuevoReporte.getCalificacion());
            reporte.setTipo_reporte(true);
            reporte.setEstado_tabla(true);
            reporte.setUsuario(usuario);
            reporte.setViaje(viaje);
            reporte = this.reporteRepository.save(reporte);
        }catch(Exception e){
            throw e;
        }
        return reporte;
    }

    public List<Reporte> listarReportePorViaje(Long viajeId) throws Exception{
        List<Reporte> reportesPorViaje;
        try{
            reportesPorViaje = this.reporteRepository.listarReportesPorViaje(viajeId);
        } catch(Exception e){
            throw e;
        }
        return reportesPorViaje;
    }

}
