package com.simplife.skip.payload.requests;

import com.simplife.skip.models.Cuenta;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String dni;
    private String nombres;
    private String apellidos;
    private String sede;
    private String facebook;
    private String imagen;
    private boolean estadoTabla;
    private Cuenta cuenta;


    public UsuarioResponse(String dni, String nombres, String apellidos, String sede, Cuenta cuenta, String facebook, String imagen) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sede = sede;
        this.facebook = facebook;
        //this.ubicacion = ubicacion;
        this.cuenta = cuenta;
        this.imagen = imagen;

        this.estadoTabla = true;
    }

    @Override
    public String toString() {
        return "UsuarioResponse{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sede='" + sede + '\'' +
                ", facebook='" + facebook + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estadoTabla=" + estadoTabla +
                ", cuenta=" + cuenta +
                '}';
    }

}
