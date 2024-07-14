package com.alurachallenge.forohub.domain.topico;

import com.alurachallenge.forohub.domain.curso.Curso;
import com.alurachallenge.forohub.domain.curso.CursoRepository;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.alurachallenge.forohub.domain.usuario.UsuarioRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosTopicoDetalle registrarTopico(DatosTopicoRegistro datosTopico) {
        Usuario autor = obtenerUsuario(datosTopico.autorId());
        Curso curso = obtenerCurso(datosTopico.cursoId());
        validarTopicoTitulo(datosTopico.titulo());
        Topico topico = new Topico(datosTopico, autor, curso);
        topico = topicoRepository.save(topico);
        return new DatosTopicoDetalle(topico);
    }

    public DatosTopicoDetalle actualizarTopico(DatosTopicoActualizar datosTopico) {
        Topico topico = obtenerTopico(datosTopico.id());

        Curso curso = null;
        if (datosTopico.cursoId() != null) {
          curso = obtenerCurso(datosTopico.cursoId());
        }

        if (!topico.getStatus().equals(TopicoStatus.ACTIVO) ) {
            throw new ValidacionIntegridad("No se puede actualizar un tópico que no está ABIERTO");
        }

        topico.actualizarTopico(datosTopico);
        if (curso != null) {
            topico.actualizarCurso(curso);
        }
        topico = topicoRepository.save(topico);
        return new DatosTopicoDetalle(topico);
    }

    public DatosTopicoDetalle detalleTopico(Long id) {
        Topico topico = obtenerTopico(id);
        return new DatosTopicoDetalle(topico);
    }

    public void eliminarTopico(Long id) {
        Topico topico = obtenerTopico(id);
        topicoRepository.deleteById(id);
    }

    private Usuario obtenerUsuario(Long autorId) {
        if (autorId == null) {
            throw new ValidacionIntegridad("El ID del autor no puede ser nulo");
        }
        return usuarioRepository.findById(autorId)
                .orElseThrow(() -> new ValidacionIntegridad("Autor no encontrado con ID: " + autorId));
    }

    private Curso obtenerCurso(Long cursoId) {
        if (cursoId == null) {
            throw new ValidacionIntegridad("El ID del curso no puede ser nulo");
        }
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ValidacionIntegridad("Curso no encontrado con ID: " + cursoId));
    }

    private Topico obtenerTopico(Long topicoId) {
        if (topicoId == null) {
            throw new ValidacionIntegridad("El ID del topico no puede ser nulo");
        }
        return topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacionIntegridad("Tópico no encontrado con ID: " + topicoId));
    }

    private void validarTopicoTitulo(String titulo) {
        if (titulo == null) {
            throw new ValidacionIntegridad("El Titulo no puede ser nulo");
        }
        if (topicoRepository.findByTitulo(titulo)
                        .stream().anyMatch(
                                topico -> !topico.getStatus().equals(TopicoStatus.CERRADO) &&
                                        !topico.getStatus().equals(TopicoStatus.RESUELTO))) {
            throw new ValidacionIntegridad("El título del tópico ya existe y sigue ABIERTO");
        }
    }

}