package com.alurachallenge.forohub.domain.perfil;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DatosPerfilRegistro(
    @NotBlank
    @Column(nullable = false, unique = true)
    String nombre
) {
}
