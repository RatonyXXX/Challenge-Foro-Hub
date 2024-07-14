package com.alurachallenge.forohub.domain.usuario;

import com.alurachallenge.forohub.domain.perfil.Perfil;
import com.alurachallenge.forohub.domain.topico.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record DatosUsuarioDetalle(
        Long id,
        String nombre,
        String correoElectronico,
        LocalDateTime fechaCreacion,
        Set<DatosPerfil> perfiles
)  {

    public DatosUsuarioDetalle(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getFechaCreacion(),
                usuario.getPerfiles().stream().map(DatosPerfil::new).collect(Collectors.toSet())
        );
    }

    public record DatosPerfil(Long id, String nombre) {
        public DatosPerfil(Perfil perfil) {
            this(
                    perfil.getId(),
                    perfil.getNombre());
        }
    }

}
