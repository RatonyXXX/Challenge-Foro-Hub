package com.alurachallenge.forohub.domain.topico;


import java.time.LocalDateTime;

public record DatosTopicoDetalle(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        TopicoStatus status,
        String autorNombre,
        String cursoNombre,
        String cursoGategoria,
        Integer respuestas

) {

    public DatosTopicoDetalle(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getCurso().getCategoria(),
                topico.getRespuestas()
        );
    }
}