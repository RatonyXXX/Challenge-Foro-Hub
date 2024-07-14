package com.alurachallenge.forohub.domain.perfil;

import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Object actualizarPerfil(DatosPerfilActualizar datosPerfil) {

        if (datosPerfil.id() == null || !perfilRepository.findById(datosPerfil.id()).isPresent()) {
            throw new ValidacionIntegridad("Perfil no encontrado con ID: " + datosPerfil.id());
        }
        if (perfilRepository.existsByNombreAndIdNot(datosPerfil.nombre(), datosPerfil.id())) {
            throw new ValidacionIntegridad("El nombre del perfil ya existe");
        }

        var perfil = perfilRepository.findById(datosPerfil.id()).get();
        perfil.actualizarPerfil(datosPerfil);   // Actualizar perfil
        return new DatosPerfilDetalle(perfil);
    }

}