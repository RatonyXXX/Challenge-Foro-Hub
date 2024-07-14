package com.alurachallenge.forohub.domain.respuesta;

import com.alurachallenge.forohub.domain.topico.Topico;
import com.alurachallenge.forohub.domain.topico.TopicoRepository;
import com.alurachallenge.forohub.domain.topico.TopicoStatus;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.alurachallenge.forohub.domain.usuario.UsuarioRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaDetalle registrarRespuesta(DatosRespuestaRegistro datosRespuesta) {
        Usuario autor = usuarioRepository.findById(datosRespuesta.autorId())
                .orElseThrow(() -> new ValidacionIntegridad("Autor no encontrado con ID: " + datosRespuesta.autorId()));

        Topico topico = topicoRepository.findById(datosRespuesta.topicoId())
                .orElseThrow(() -> new ValidacionIntegridad("Tópico no encontrado con ID: " + datosRespuesta.topicoId()));

        validarTopicoAbierto(topico);
        validarMensaje(datosRespuesta.mensaje(), datosRespuesta.topicoId(), null);

        Respuesta respuesta = new Respuesta(datosRespuesta, autor, topico);
        respuesta = respuestaRepository.save(respuesta);

        actualizarContadorRespuestas(respuesta.getTopico().getId());

        return new DatosRespuestaDetalle(respuesta);
    }

    public DatosRespuestaDetalle actualizarRespuesta(DatosRespuestaActualizar datosRespuesta) {
        Respuesta respuesta = respuestaRepository.findById(datosRespuesta.Id())
                .orElseThrow(() -> new ValidacionIntegridad("Respuesta no encontrada con ID: " + datosRespuesta.Id()));

        Topico topico = topicoRepository.findById(datosRespuesta.topicoId())
                .orElseThrow(() -> new ValidacionIntegridad("Tópico no encontrado con ID: " + datosRespuesta.topicoId()));

        validarTopicoAbierto(topico);
//        validarMensaje(datosRespuesta.mensaje(), datosRespuesta.topicoId());
        if (!respuesta.getMensaje().equals(datosRespuesta.mensaje())) {
            validarMensaje(datosRespuesta.mensaje(), datosRespuesta.topicoId(), respuesta.getId());
        }

        respuesta.actualizarRespuesta(datosRespuesta);
        return new DatosRespuestaDetalle(respuesta);

    }

    public void eliminarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionIntegridad("Respuesta no encontrada con ID: " + id));

        Long topicoId = respuesta.getTopico().getId();
        respuestaRepository.deleteById(id);
        actualizarContadorRespuestas(topicoId);
    }

    private void validarTopicoAbierto(Topico topico) {
        if (!TopicoStatus.ACTIVO.equals(topico.getStatus())) {
            throw new ValidacionIntegridad("No se puede realizar la operación porque el tópico no está ABIERTO");
        }
    }

//    private void validarMensaje(String mensaje, Long topicoId) {
//        if (respuestaRepository.findByMensajeAndTopicoId(mensaje, topicoId).isPresent()) {
//            throw new ValidacionIntegridad("Ya existe una respuesta con el mismo mensaje para este tópico");
//        }
//    }

    private void validarMensaje(String mensaje, Long topicoId, Long respuestaId) {
        boolean mensajeExistente;
        if (respuestaId == null) {
            mensajeExistente = respuestaRepository.findByMensajeAndTopicoId(mensaje, topicoId).isPresent();
        } else {
            mensajeExistente = respuestaRepository.findByMensajeAndTopicoIdAndIdNot(mensaje, topicoId, respuestaId).isPresent();
        }

        if (mensajeExistente) {
            throw new ValidacionIntegridad("Ya existe una respuesta con el mismo mensaje para este tópico");
        }
    }

    private void actualizarContadorRespuestas(Long topicoId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacionIntegridad("Tópico no encontrado con ID: " + topicoId));

        // Obtener y actualizar el número de respuestas
        int cantidadRespuestas = respuestaRepository.countByTopicoId(topicoId);
        topico.setRespuestas(cantidadRespuestas);

        // Guardar el Topico actualizado
        topicoRepository.save(topico);
    }
}
