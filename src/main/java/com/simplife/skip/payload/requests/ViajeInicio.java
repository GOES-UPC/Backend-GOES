package com.simplife.skip.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.simplife.skip.models.Parada;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ViajeInicio {

    private Long id;
    private String nombres;
    private String imagen;
    private String fechaPublicacion;
    private String descripcion;
    private List<Parada> paradas;
    private String horaInicio;
    private String horaFin;

}
