package com.simplife.skip.payload.requests;
import com.simplife.skip.models.Parada;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SolicitudRequest {

    //Mensaje al conductor
    private String mensaje;

    private Long pasajeroId;

    private Long viajeId;

    //punto en donde se recogerá al pasajero
    private Parada puntoEncuentro;

}
