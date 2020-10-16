package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viaje")
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viaje_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "conductor_id", nullable = false)
    private Usuario conductor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "fecha_viaje")
    private LocalDate fechaViaje;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_llegada")
    private LocalTime horaLlegada;

    @Column(name = "hora_publicacion")
    private LocalTime horaPublicacion;

    @Column(name = "visualizacion_habilitada")
    private boolean visualizacionHabilitada;

    @Column(name = "numero_pasajeros")
    private int numeroPasajeros;

    //Publicado - En curso - Finalizado
    @Column(name = "estado_viaje", length = 10)
    private String estadoViaje;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;


    public Viaje(Usuario conductor, String descripcion, LocalDate fechaViaje,
                 LocalTime horaInicio, LocalTime horaLlegada){
        this.conductor = conductor;
        this.descripcion = descripcion;
        this.fechaViaje = fechaViaje;
        this.horaInicio = horaInicio;
        this.horaLlegada = horaLlegada;
    }


}
