package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.perfil.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/perfil")
//@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PerfilService perfilService;


    @PostMapping
    @Transactional
//    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity registro(@RequestBody @Valid DatosPerfilRegistro datosPerfil, UriComponentsBuilder uriBuilder) {

        var perfil = new Perfil(datosPerfil);
        perfilRepository.save(perfil);
        var uri = uriBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosPerfilDetalle(perfil));
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity actualizar(@RequestBody @Valid DatosPerfilActualizar datosPerfil) {

        var response = perfilService.actualizarPerfil(datosPerfil);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    //@Operation(sumary = "Obtiene un listado de los perfiles registrados")
    public ResponseEntity<Page<DatosPerfilDetalle>> listado(@PageableDefault(size = 100, sort = {"id"}) Pageable paginacion) {

        var page = perfilRepository.findAll(paginacion).map(DatosPerfilDetalle::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
//    @Operation(summary = "obtiene los detalles de la consulta con el ID indicado")
    public ResponseEntity detalle(@PathVariable Long id) {

        var perfil = perfilRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosPerfilDetalle(perfil));
    }

}