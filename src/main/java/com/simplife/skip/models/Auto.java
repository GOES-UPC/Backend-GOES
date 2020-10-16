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
@Entity
@Table(name = "auto")
public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private Long id;

    @Column(name = "placa", length = 7)
    private String placa;

    @Column(name = "poliza_soat")
    private int polizaSoat;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "limite_personas")
    private int limitePersonas;

    @Column(name = "anhos_uso")
    private int anhosUso;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @ManyToOne
    @JoinColumn(name="info_conductor_id", nullable = false)
    private InformacionConductor infoConductor;




}
