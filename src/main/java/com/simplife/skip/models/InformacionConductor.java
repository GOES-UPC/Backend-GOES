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
@Table(name = "informacion_conductor")
@Entity
public class InformacionConductor implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_conductor_id")
    private Long id;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "licencia_conducir")
    private String licenciaConducir;

    /*@Column(name = "direccion")
    private String direccion;

    @Column(name = "latitud")
    private double latitud;

    @Column(name = "longitud")
    private double longitud;*/

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @Override
    public String toString() {
        return "InformacionConductor{" +
                "id=" + id +
                ", facebook='" + facebook + '\'' +
                ", telefono='" + telefono + '\'' +
                ", licenciaConducir='" + licenciaConducir + '\'' +
                ", usuario=" + usuario +
                ", estadoTabla=" + estadoTabla +
                '}';
    }
}
