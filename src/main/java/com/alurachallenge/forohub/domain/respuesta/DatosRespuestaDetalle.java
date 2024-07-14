package com.alurachallenge.forohub.domain.respuesta;

import com.alurachallenge.forohub.domain.topico.Topico;
import com.alurachallenge.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaDetalle(
        Long Id,
        String mensaje,
        String topicoNombre,
        String autorNombre,
        LocalDateTime fechaCreacion,
        Boolean solucion

) {

    public DatosRespuestaDetalle(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }

}