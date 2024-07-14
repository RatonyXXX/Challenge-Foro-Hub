package com.alurachallenge.forohub.domain.perfil;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosPerfilRegistro(
    @NotBlank
    @Column(nullable = false, unique = true)
    String nombre


) {
}
