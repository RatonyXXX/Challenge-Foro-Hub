package com.alurachallenge.forohub.domain.topico;

import com.alurachallenge.forohub.domain.curso.Curso;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicoStatus status = TopicoStatus.ACTIVO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
//    @JsonIgnore
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
//    @JsonIgnore
    private Curso curso;

    @Column(nullable = false)
    private Integer respuestas = 0;

    public Topico(DatosTopicoRegistro datosTopico, Usuario autor, Curso curso) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarTopico(DatosTopicoActualizar datosTopico) {
        if (datosTopico.mensaje() != null) {
            this.mensaje = datosTopico.mensaje();
        }
        if (datosTopico.status() != null) {
            this.status = datosTopico.status();
        }
        if (datosTopico.curso() != null) {
            this.curso = datosTopico.curso();
        }
    }

    public void actualizarCurso(Curso curso) {
        this.curso = curso;
    }

    public int getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(int respuestas) {
        this.respuestas = respuestas;
    }

}