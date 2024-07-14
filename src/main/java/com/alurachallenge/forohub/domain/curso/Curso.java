package com.alurachallenge.forohub.domain.curso;

import com.alurachallenge.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Curso")
@Table(name = "curso",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre", "categoria"})})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos = new ArrayList<>();

    public void actualizarCurso(DatosCursoActualizar datosCurso) {
        if (datosCurso.nombre() != null) {
            this.nombre = datosCurso.nombre();
        }
        if (datosCurso.categoria() != null) {
            this.categoria = datosCurso.categoria() ;
        }
    }

    public Curso(DatosCursoRegistro datosCurso) {
        this.nombre = datosCurso.nombre();
        this.categoria = datosCurso.categoria();
    }

    public Curso(Long id) {
        this.Id = id;
    }

}