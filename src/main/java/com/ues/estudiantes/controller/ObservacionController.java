package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.service.ObservacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observaciones")
public class ObservacionController {

    private final ObservacionService service;

    public ObservacionController(ObservacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Observacion> listar() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observacion> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Observacion> crear(@Valid @RequestBody Observacion obs) {
        return ResponseEntity.ok(service.guardar(obs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observacion> actualizar(@PathVariable Long id, @Valid @RequestBody Observacion obs) {
        return service.obtenerPorId(id)
                .map(o -> {
                    obs.setId(id);
                    return ResponseEntity.ok(service.guardar(obs));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
