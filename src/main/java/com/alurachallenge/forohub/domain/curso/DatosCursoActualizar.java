package com.alurachallenge.forohub.domain.curso;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCursoActualizar(
        @NotNull
        @Column(nullable = false)
        Long id,

        @NotBlank
        @Column(nullable = false, unique = true)
        String nombre,

        @NotBlank
        @Column(nullable = false, unique = true)
        String categoria

) {
}
