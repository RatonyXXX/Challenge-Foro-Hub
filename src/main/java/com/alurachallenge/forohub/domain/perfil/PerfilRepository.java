package com.alurachallenge.forohub.domain.perfil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    boolean existsByNombre(String nombre);

    Page<Perfil> findAll(Pageable paginacion);

    boolean existsByNombreAndIdNot(String nombre, Long id);

}
