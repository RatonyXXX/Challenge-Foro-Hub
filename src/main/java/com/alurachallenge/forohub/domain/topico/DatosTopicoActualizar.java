package com.alurachallenge.forohub.domain.topico;

import com.alurachallenge.forohub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DatosTopicoActualizar(
        @NotNull
        Long id,

        String mensaje,
        TopicoStatus status,
        Long cursoId

) {
        public Curso curso() {
                return cursoId != null ? new Curso(cursoId) : null;
        }

}