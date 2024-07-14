package com.alurachallenge.forohub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Optional<Respuesta> findByMensajeAndTopicoId(String mensaje, Long topicoId);

    Optional<Respuesta> findByMensajeAndTopicoIdAndIdNot(String mensaje, Long topicoId, Long id);

    int countByTopicoId(Long topicoId);

}
