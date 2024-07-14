package com.alurachallenge.forohub.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record DatosPerfilActualizar(
        @NotNull
        Long id,

        @NotNull
        String nombre

) { }
