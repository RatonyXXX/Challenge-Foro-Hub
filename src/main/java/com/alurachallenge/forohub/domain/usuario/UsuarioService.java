package com.alurachallenge.forohub.domain.usuario;

import com.alurachallenge.forohub.domain.perfil.Perfil;
import com.alurachallenge.forohub.domain.perfil.PerfilRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

//    @Autowired
//    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
//        this.usuarioRepository = usuarioRepository;
//        this.perfilRepository = perfilRepository;
//    }

    @Transactional
    public DatosUsuarioDetalle registrarUsuarioConPerfil(DatosUsuarioRegistro datosUsuario) {

        Long perfilId = datosUsuario.perfilId();
        if (perfilId == null || !perfilRepository.existsById(perfilId)) {
            throw new ValidacionIntegridad("Perfil no encontrado con ID: " + perfilId);
        }

        // Obtener el perfil una sola vez
        Perfil perfil = perfilRepository.findById(perfilId).get();

        String correoElectronico = datosUsuario.correoElectronico();
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreoElectronico(correoElectronico);
        Usuario usuario;

        if (usuarioExistente.isPresent()) {
            usuario = usuarioExistente.get();

            if (usuario.getPerfiles().contains(perfil)) {
                throw new ValidacionIntegridad("Ya existe un usuario con el mismo correo electrónico y perfil");
            }

//            usuario.getPerfiles().add(perfil);

        } else {
            usuario = new Usuario(datosUsuario);
//            usuario.getPerfiles().add(perfil);
        }

//        usuario.getPerfiles().add(perfil);
//        usuarioRepository.save(usuario);
//        return new DatosUsuarioDetalle(usuario);

        usuario.getPerfiles().add(perfil);
        usuarioRepository.save(usuario);
        return new DatosUsuarioDetalle(usuario);




//
//        // Verificar si el ID de perfil está presente y existe
//        if (datosUsuario.idPerfil() == null || !perfilRepository.existsById(datosUsuario.idPerfil())) {
//            throw new ValidacionIntegridad("Perfil no encontrado con ID: " + datosUsuario.idPerfil());
//        }
//
//        // Verificar si ya existe un usuario con el mismo correo electrónico
//        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreoElectronico(datosUsuario.correoElectronico());
//        if (usuarioExistente.isPresent()) {
//            Usuario usuario = usuarioExistente.get();
//            // Verificar si el usuario ya tiene asociado el perfil en cuestión
//            Perfil perfil = perfilRepository.findById(datosUsuario.idPerfil())
//                    .orElseThrow(() -> new ValidacionIntegridad("Perfil no encontrado con ID: " + datosUsuario.idPerfil()));
//
//            if (usuario.getPerfiles().contains(perfil)) {
//                throw new ValidacionIntegridad("El usuario ya tiene asociado este perfil");
//            }
//
//            usuario.getPerfiles().add(perfil);  // Asignar el perfil al usuario
//            perfil.getUsuarios().add(usuario);  // Agregar el usuario al perfil
//            usuarioRepository.save(usuario);
//            return new DatosUsuarioDetalle(usuario);
//        }
//
//        // Crear un nuevo usuario y asociar el perfil
//        Usuario usuario = new Usuario(datosUsuario);
//        Perfil perfil = perfilRepository.findById(datosUsuario.idPerfil())
//                .orElseThrow(() -> new ValidacionIntegridad("Perfil no encontrado con ID: " + datosUsuario.idPerfil()));
//
//        usuario.getPerfiles().add(perfil);  // Asignar el perfil al usuario
//        perfil.getUsuarios().add(usuario);  // Agregar el usuario al perfil
//
//        usuarioRepository.save(usuario);
//
//        return new DatosUsuarioDetalle(usuario);




//        if(datosUsuario.idPerfil()==null || !perfilRepository.existsById(datosUsuario.idPerfil())) {
//            throw new ValidacionIntegridad("Perfil no encontrado con ID: " + datosUsuario.idPerfil());
//        }
//        var perfil = perfilRepository.findById(datosUsuario.idPerfil()).get();
//        Optional<Usuario> usuarioExistente = usuarioRepository
//                .findByCorreoElectronicoAndPerfiles(datosUsuario.correoElectronico(), perfil);
//        if (usuarioExistente.isPresent()) {
//            throw new ValidacionIntegridad("Ya existe un usuario con el mismo correo electrónico y perfil");
//        }
//        var usuario = new Usuario(datosUsuario);
//        usuarioRepository.save(usuario);
//        usuario.getPerfiles().add(perfil);
//        perfil.getUsuarios().add(usuario);
//        return new DatosUsuarioDetalle(usuario);
    }

//    public Usuario findUsuarioById(Long idUsuario) {
//        return usuarioRepository.findById(idUsuario)
//                .orElseThrow(() -> new ValidacionIntegridad("Usuario no encontrado con ID: " + idUsuario));
//    }
}