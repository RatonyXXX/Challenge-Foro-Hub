package com.alurachallenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopicoRegistro(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId

) {
}
