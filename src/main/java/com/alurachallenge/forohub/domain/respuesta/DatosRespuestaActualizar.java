package com.alurachallenge.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaActualizar(
        @NotNull
        Long Id,

        @NotNull
        Long topicoId,

        String mensaje,
        Boolean solucion


) { }

