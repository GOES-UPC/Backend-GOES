package com.simplife.skip.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroEnLista {
    private Long usuarioId;
    private Long viajeId;
    private String imagen;
    private String nombres;
    private String estadoPasajero;
    private String puntoEncuentro;

}
