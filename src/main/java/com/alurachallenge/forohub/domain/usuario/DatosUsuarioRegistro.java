package com.alurachallenge.forohub.domain.usuario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record DatosUsuarioRegistro(
    @NotBlank
    String nombre,

    @NotBlank
    @Email
    @Column(name = "correo_electronico", nullable = false, unique = true)
    String correoElectronico,

    @NotBlank
    String contrasena,

    @NotNull
    @Column(name = "perfil_id", nullable = false)
    Long perfilId

) {
}