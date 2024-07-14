package com.alurachallenge.forohub.domain.usuario;

import com.alurachallenge.forohub.domain.perfil.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    //Page<Usuario>findByActivoTrue(Pageable paginacion);

}
