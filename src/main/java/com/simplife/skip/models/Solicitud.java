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
@Table(name = "solicitud")
@Entity
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_id")
    private Long id;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fechaSolicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "horaSolicitud")
    private LocalTime horaSolicitud;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pasajero_id", nullable = false)
    private Usuario pasajero;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;

    //En lista
    //En viaje
    //En destino
    @Column(name = "estado_pasajero", length = 10)
    private String estadoPasajero;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parada_id")
    private Parada parada;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    public Solicitud(String mensaje, Usuario pasajero, Viaje viaje, Parada parada){
        this.mensaje = mensaje;
        this.parada = parada;
        this.viaje = viaje;
        this.pasajero = pasajero;
    }
}
