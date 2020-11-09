package com.simplife.skip.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConductorDeViajeResponse {
    private Long id;
    private String nombres;
    private String placa;
    private String modelo;
}
