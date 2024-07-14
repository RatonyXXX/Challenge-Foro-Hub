package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.topico.*;
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
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
//    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity registro(@RequestBody @Valid DatosTopicoRegistro datosTopico, UriComponentsBuilder uriBuilder) {
        var topico = topicoService.registrarTopico(datosTopico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity actualizar(@RequestBody @Valid DatosTopicoActualizar datosTopico) {

        var response = topicoService.actualizarTopico(datosTopico);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
//    @Operation(summary = "obtiene los detalles de la consulta con el ID indicado")
    public ResponseEntity detalle(@PathVariable Long id) {

//        var topico = topicoRepository.getReferenceById(id);
//        return ResponseEntity.ok(new DatosTopicoDetalle(topico));

        var topico = topicoService.detalleTopico(id);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    //@Operation(sumary = "Obtiene un listado de los perfiles registrados")
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
//    @Operation(summary = "Elimina un paciente a partir del ID")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {

        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}