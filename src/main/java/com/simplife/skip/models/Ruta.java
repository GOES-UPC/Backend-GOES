package com.simplife.skip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ruta")
@Entity
public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tiempo_estimado")
    private String tiempoEstimado;

    @Column(name = "sentido")
    private boolean sentido;

    @Column(name = "distancia")
    private float distancia;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;


    public Ruta(String tiempoEstimado, boolean sentido, float distancia, boolean estadoTabla){
        this.tiempoEstimado = tiempoEstimado;
        this.sentido = sentido;
        this.distancia = distancia;
        this.estadoTabla = estadoTabla;
    }

}
