package com.alurachallenge.forohub.domain.respuesta;

import com.alurachallenge.forohub.domain.topico.Topico;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private Boolean solucion = false;

    public Respuesta(DatosRespuestaRegistro datosRespuesta, Usuario autor, Topico topico) {
        this.mensaje = datosRespuesta.mensaje();
        this.autor = autor;
        this.topico = topico;
    }

    public void actualizarRespuesta(DatosRespuestaActualizar datosRespuesta) {
        if (datosRespuesta.mensaje() != null) {
            this.mensaje = datosRespuesta.mensaje();
        }
        if (datosRespuesta.solucion() != null) {
            this.solucion = datosRespuesta.solucion();
        }
    }
}