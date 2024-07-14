package com.alurachallenge.forohub.domain.curso;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DatosCursoRegistro(
        @NotBlank
        @Column(nullable = false, unique = true)
        String nombre,

        @NotBlank
        @Column(nullable = false)
        String categoria

) {
}
