package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.curso.*;
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
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo curso",
            tags = "Cursos",
            description = "Registra un nuevo curso con los detalles proporcionados en la solicitud.")
    public ResponseEntity registro(@RequestBody @Valid DatosCursoRegistro datosCurso, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(datosCurso);
        cursoRepository.save(curso);
        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosCursoDetalle(curso));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los detalles de un curso",
            tags = "Cursos",
            description = "Actualiza la información de un curso existente con los nuevos detalles proporcionados.")
    public ResponseEntity actualizar(@RequestBody @Valid DatosCursoActualizar datosCurso) {
        var response = cursoService.actualizarCurso(datosCurso);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los detalles de un curso",
            tags = "Cursos",
            description = "Obtiene los detalles completos de un curso específico utilizando su ID.")
    public ResponseEntity detalle(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosCursoDetalle(curso));
    }

    @GetMapping
    @Operation(summary = "Obtiene un listado de cursos",
            tags = "Cursos",
            description = "Obtiene un listado paginado de todos los cursos registrados en el sistema.")
    public ResponseEntity<Page<DatosCursoDetalle>> listado(
            @PageableDefault(size = 100, sort = {"categoria", "nombre"}) Pageable paginacion) {
        var page = cursoRepository.findAll(paginacion).map(DatosCursoDetalle::new);
        return ResponseEntity.ok(page);
    }

}
