package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itinerario")
@Entity
public class Itinerario {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerario_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parada_id", nullable = false)
    private Parada parada;

    @Column(name = "num_orden")
    private int numOrden;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    public Itinerario(Ruta ruta, Parada parada, int numOrden){
        this.ruta = ruta;
        this.parada = parada;
        this.numOrden = numOrden;
    }
}
