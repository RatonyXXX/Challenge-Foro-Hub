package com.alurachallenge.forohub.domain.perfil;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DatosPerfilDetalle(
        Long Id,
        String nombre,
        Set<DatosUsuario> usuarios
) {

    public DatosPerfilDetalle(Perfil perfil) {
        this(
                perfil.getId(),
                perfil.getNombre(),
                perfil.getUsuarios().stream().map(DatosUsuario::new).collect(Collectors.toSet())
        );
    }

    public record DatosUsuario(Long id, String nombre, String correoElectronico, LocalDateTime fechaCreacion) {
        public DatosUsuario(Usuario usuario) {
            this(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getCorreoElectronico(),
                    usuario.getFechaCreacion()
            );
        }
    }

}