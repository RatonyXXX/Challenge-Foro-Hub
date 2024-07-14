package com.alurachallenge.forohub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombreAndCategoria(String nombre, String categoria);

    boolean existsByNombreAndCategoria(String nombre, String categoria);

    boolean existsByNombreAndCategoriaAndIdNot(String nombre, String categoria, Long id);
}
