package com.simplife.skip.payload.requests;

import com.simplife.skip.models.Parada;
import lombok.Data;

import java.util.List;

@Data
public class ViajeRequest {

    private Long conductorId;
    //True - hacia la universidad
    //False - desde la universidad
    private boolean sentidoRuta;
    private Parada partida;
    private Parada destino;
    private String tiempoEstimado;
    private float distancia;
    private String descripcion;
    private String fechaViaje;
    private String horaInicio;
    private String horaLlegada;

}
