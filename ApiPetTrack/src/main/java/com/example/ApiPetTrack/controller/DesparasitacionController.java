package com.example.ApiPetTrack.controller;

import com.example.ApiPetTrack.model.Desparasitacion;
import com.example.ApiPetTrack.service.DesparasitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas/{mascotaId}/desparasitaciones")
public class DesparasitacionController {

    @Autowired
    private DesparasitacionService servicio;

    @GetMapping
    public ResponseEntity<List<Desparasitacion>> listar(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(servicio.listarPorMascota(mascotaId));
    }

    @PostMapping
    public ResponseEntity<Desparasitacion> registrar(@PathVariable Long mascotaId, @RequestBody Desparasitacion desparasitacion) {
        return new ResponseEntity<>(servicio.registrar(mascotaId, desparasitacion), HttpStatus.CREATED);
    }

    @GetMapping("/{desparasitacionId}")
    public ResponseEntity<Desparasitacion> obtenerPorId(
            @PathVariable Long mascotaId,
            @PathVariable Long desparasitacionId) {

        return servicio.obtenerPorId(desparasitacionId)
                .filter(d -> d.getMascota().getId().equals(mascotaId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
