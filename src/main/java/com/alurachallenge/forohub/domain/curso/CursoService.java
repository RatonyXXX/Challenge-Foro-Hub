package com.alurachallenge.forohub.domain.curso;

import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Object actualizarCurso(DatosCursoActualizar datosCurso) {

        if (!cursoRepository.findById(datosCurso.id()).isPresent()) {
            throw new ValidacionIntegridad("Curso no encontrado con ID: " + datosCurso.id());
        }

        if (datosCurso.nombre() != null && datosCurso.categoria() != null &&
                cursoRepository.existsByNombreAndCategoriaAndIdNot(datosCurso.nombre(), datosCurso.categoria(), datosCurso.id() )) {
            throw new ValidacionIntegridad("El nombre del curso ya existe");
        }

        var curso = cursoRepository.findById(datosCurso.id()).get();
        curso.actualizarCurso(datosCurso);   // Actualizar perfil
        return cursoRepository.save(curso);
    }

}