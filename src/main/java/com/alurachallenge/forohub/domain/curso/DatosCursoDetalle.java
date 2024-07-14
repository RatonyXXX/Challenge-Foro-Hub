package com.alurachallenge.forohub.domain.curso;

import com.alurachallenge.forohub.domain.topico.TopicoStatus;
import com.alurachallenge.forohub.domain.topico.Topico;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DatosCursoDetalle(
        Long Id,
        String nombre,
        String categoria,
        Set<DatosTopico> topico
) {

    public DatosCursoDetalle(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria(),
                curso.getTopicos().stream().map(DatosTopico::new).collect(Collectors.toSet())
        );
    }

    public record DatosTopico(Long id, String titulo, LocalDateTime fechaCreacion, TopicoStatus status, String autorNombre, Integer respuestas) {
        public DatosTopico(Topico topico) {
            this(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getFechaCreacion(),
                    topico.getStatus(),
                    topico.getAutor().getNombre(),
                    topico.getRespuestas()
            );
        }
    }

}