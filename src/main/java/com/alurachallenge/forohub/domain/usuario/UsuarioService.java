package com.alurachallenge.forohub.domain.usuario;

import com.alurachallenge.forohub.domain.perfil.Perfil;
import com.alurachallenge.forohub.domain.perfil.PerfilRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    public DatosUsuarioDetalle registrarUsuarioConPerfil(DatosUsuarioRegistro datosUsuario) {

        Long perfilId = datosUsuario.perfilId();
        if (perfilId == null || !perfilRepository.existsById(perfilId)) {
            throw new ValidacionIntegridad("Perfil no encontrado con ID: " + perfilId);
        }

        Perfil perfil = perfilRepository.findById(perfilId).get();

        String correoElectronico = datosUsuario.correoElectronico();
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreoElectronico(correoElectronico);
        Usuario usuario;

        if (usuarioExistente.isPresent()) {
            usuario = usuarioExistente.get();

            if (usuario.getPerfiles().contains(perfil)) {
                throw new ValidacionIntegridad("Ya existe un usuario con el mismo correo electrónico y perfil");
            }
        } else {
            usuario = new Usuario(datosUsuario);
        }

        usuario.getPerfiles().add(perfil);
        usuarioRepository.save(usuario);
        return new DatosUsuarioDetalle(usuario);
    }
}