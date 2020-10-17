package com.simplife.skip.payload.requests;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Getter
public class ReportRequest {

    @NotBlank
    private float calificacion;

    @NotBlank
    private String mensaje;
}
