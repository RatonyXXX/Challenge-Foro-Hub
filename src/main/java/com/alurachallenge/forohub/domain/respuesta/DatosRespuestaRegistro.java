package com.alurachallenge.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRespuestaRegistro(
        @NotNull
        Long autorId,

        @NotNull
        Long topicoId,

        @NotBlank
        String mensaje

//        @NotNull
//        boolean solucion
) {
}