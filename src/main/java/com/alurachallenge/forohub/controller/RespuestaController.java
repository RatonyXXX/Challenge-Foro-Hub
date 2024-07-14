package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.respuesta.*;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;


    @PostMapping
    @Transactional
//    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity registro(@RequestBody @Valid DatosRespuestaRegistro datosRespuesta, UriComponentsBuilder uriBuilder) {
        var respuesta = respuestaService.registrarRespuesta(datosRespuesta);
        var uri = uriBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.Id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping
    @Transactional
//    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity actualizar(@RequestBody @Valid DatosRespuestaActualizar datosRespuesta) {
        var response = respuestaService.actualizarRespuesta(datosRespuesta);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
//    @Operation(summary = "obtiene los detalles de la consulta con el ID indicado")
    public ResponseEntity detalle(@PathVariable Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaDetalle(respuesta));
    }

    @GetMapping
    //@Operation(sumary = "Obtiene un listado de los perfiles registrados")
    public ResponseEntity<Page<DatosRespuestaDetalle>> listado(
            @PageableDefault(size = 100, sort = "Id") Pageable paginacion) {
        var page = respuestaRepository.findAll(paginacion).map(DatosRespuestaDetalle::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
//    @Operation(summary = "Elimina un paciente a partir del ID")
    public ResponseEntity eliminar(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }

}