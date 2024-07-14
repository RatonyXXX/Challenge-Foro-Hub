package com.alurachallenge.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUsuarioActualizar(
        @NotNull
        Long id,

        String nombre,
        String contrasena

) {
}
