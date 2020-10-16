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
@Table(name = "parada")
@Entity
public class Parada {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parada_id")
    private Long id;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @Override
    public String toString() {
        return "Parada{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", estadoTabla=" + estadoTabla +
                '}';
    }
}
