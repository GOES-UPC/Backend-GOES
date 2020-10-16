package com.simplife.skip.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    public  static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long Id;

    @Column(name = "codigo_upc", nullable = false)
    private String codigoUpc;

    @Column(name = "correo_upc", nullable = false)
    private String correoUPC;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado_tabla")
    private boolean estadoTabla;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cuenta_rol",
            joinColumns = @JoinColumn(name = "cuenta_id"),
            inverseJoinColumns = @JoinColumn(name ="rol_id"))
    private Set<Rol> roles;

    public Cuenta(String codigo, String email, String encode) {
        this.codigoUpc = codigo;
        this.correoUPC = email;
        this.contrasena = encode;

        this.estadoTabla = true;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "Id=" + Id +
                ", codigoUpc='" + codigoUpc + '\'' +
                ", correoUPC='" + correoUPC + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", estadoTabla=" + estadoTabla +
                ", roles=" + roles +
                '}';
    }
}

