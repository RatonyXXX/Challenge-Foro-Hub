package com.alurachallenge.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //Page<Topico> findByActivoTrue(Pageable paginacion);

    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);

    Optional<Topico> findByTitulo(String titulo);

//    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = LIKE:cursoNombre")
//    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:cursoNombre%")
    @Query("SELECT t FROM Topico t WHERE UPPER(t.curso.nombre) LIKE UPPER(CONCAT('%', :cursoNombre, '%'))")
    Page<Topico> findByCursoNombre(String cursoNombre, Pageable paginacion);

    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByFechaCreacionAnio(Integer anio, Pageable paginacion);

//    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :cursoNombre AND YEAR(t.fechaCreacion) = :ano")
//    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:cursoNombre% AND YEAR(t.fechaCreacion) = :ano")
    @Query("SELECT t FROM Topico t WHERE UPPER(t.curso.nombre) LIKE UPPER(CONCAT('%', :cursoNombre, '%')) AND YEAR(t.fechaCreacion) = :ano")
    Page<Topico> findByCursoNombreAndFechaCreacionAnio(String cursoNombre, Integer ano, Pageable paginacion);

}
