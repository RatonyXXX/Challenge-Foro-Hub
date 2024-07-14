package com.alurachallenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopicoRegistro(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

//        @NotBlank
//        LocalDateTime fechaCreacion,

//        @NotNull
//        TopicoStatus status,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId

//        @NotNull
//        Long respuestas
) {
}
