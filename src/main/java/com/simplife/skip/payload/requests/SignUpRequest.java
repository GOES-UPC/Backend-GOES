package com.simplife.skip.payload.requests;

import com.simplife.skip.models.Auto;
import com.simplife.skip.models.InformacionConductor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min=5, max=25)
    private String codigo;

    @NotBlank
    @Size(min=6, max=25)
    private String contrasena;

    @NotBlank
    private String dni;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String sede;

    /*@NotBlank
    private String facebook;*/

    /*@NotBlank
    private String ubicacion;*/

    @NotBlank
    private String imagen;

    //@NotBlank
    private Set<String> role;

    private InformacionConductor infoConductor;

    private Auto auto;

}
