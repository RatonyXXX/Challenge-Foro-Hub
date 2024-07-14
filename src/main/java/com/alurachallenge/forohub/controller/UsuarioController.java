package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.usuario.*;
import com.alurachallenge.forohub.infra.errores.ValidacionIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registra un nuevo medico en la base de datos")
    public ResponseEntity registrarUsuarioConPerfil(@RequestBody @Valid DatosUsuarioRegistro datosUsuario, UriComponentsBuilder uriBuilder)
            throws ValidacionIntegridad {
        var usuario = usuarioService.registrarUsuarioConPerfil(datosUsuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity actualizar(@RequestBody @Valid DatosUsuarioActualizar datosUsuario) {
        var usuario = usuarioRepository.getReferenceById(datosUsuario.id());
        usuario.actualizarUsuario(datosUsuario);
        return ResponseEntity.ok(new DatosUsuarioDetalle(usuario));
    }

    @GetMapping("/{id}")
//    @Operation(summary = "obtiene los detalles de la consulta con el ID indicado")
    public ResponseEntity detalle(@PathVariable Long id) {

        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosUsuarioDetalle(usuario));
    }

    @GetMapping
    //@Operation(sumary = "Obtiene un listado de los perfiles registrados")
    public ResponseEntity<Page<DatosUsuarioDetalle>> listado(@PageableDefault(size = 100, sort = "id") Pageable paginacion) {
        var page = usuarioRepository.findAll(paginacion).map(DatosUsuarioDetalle::new);
        return ResponseEntity.ok(page);
    }

}