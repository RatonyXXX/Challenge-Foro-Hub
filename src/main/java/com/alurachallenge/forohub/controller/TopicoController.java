package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo tópico",
            tags = "Tópicos",
            description = "Registra un nuevo tópico con los detalles proporcionados en la solicitud.")
        public ResponseEntity registro(@RequestBody @Valid DatosTopicoRegistro datosTopico, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.registrarTopico(datosTopico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza un tópico",
            tags = "Tópicos",
            description = "Actualiza la información de un tópico existente con los nuevos detalles proporcionados.")
    public ResponseEntity actualizar(@RequestBody @Valid DatosTopicoActualizar datosTopico) {
        var response = topicoService.actualizarTopico(datosTopico);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los detalles de un tópico",
            tags = "Tópicos",
            description = "Obtiene los detalles completos de un tópico específico utilizando su ID.")
    public ResponseEntity detalle(@PathVariable Long id) {
        var topico = topicoService.detalleTopico(id);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    @Operation(summary = "Obtiene un listado de tópicos",
            tags = "Tópicos",
            description = "Obtiene un listado paginado de todos los tópicos registrados en el sistema.")
    public ResponseEntity<Page<DatosTopicoDetalle>> listado(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion,
            @RequestParam(required = false) String cursoNombre,
            @RequestParam(required = false) Integer anio
    ) {
        Page<DatosTopicoDetalle> page;

        if (cursoNombre != null && anio != null) {
            page = topicoRepository.findByCursoNombreAndFechaCreacionAnio(cursoNombre, anio, paginacion).map(DatosTopicoDetalle::new);
        } else if (cursoNombre != null) {
            page = topicoRepository.findByCursoNombre(cursoNombre, paginacion).map(DatosTopicoDetalle::new);
        } else if (anio != null) {
            page = topicoRepository.findByFechaCreacionAnio(anio, paginacion).map(DatosTopicoDetalle::new);
        } else {
            page = topicoRepository.findAll(paginacion).map(DatosTopicoDetalle::new);
        }
        return ResponseEntity.ok(page);

    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un tópico",
            tags = "Tópicos",
            description = "Elimina un tópico específico utilizando su ID.")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {

        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}