package com.example.ApiPetTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.model.Vacuna;
import com.example.ApiPetTrack.service.VacunaService;

@RestController
@RequestMapping("/mascotas/{mascotaId}/vacunas")
public class VacunaController {

    @Autowired
    private VacunaService vacunaService;

    // Endpoint para listar todas las vacunas de una mascota
    @GetMapping
    public List<Vacuna> listarVacunas(@PathVariable Long mascotaId) {
        return vacunaService.listarVacunasPorMascota(mascotaId);
    }

    // Endpoint para obtener una vacuna por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vacuna> obtenerVacuna(@PathVariable Long mascotaId, @PathVariable Long id) {
        Vacuna vacuna = vacunaService.obtenerVacuna(id, mascotaId);
        if (vacuna != null) {
            return ResponseEntity.ok(vacuna);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para registrar una nueva vacuna para una mascota
    @PostMapping
    public ResponseEntity<Vacuna> registrarVacuna(@PathVariable Long mascotaId, @RequestBody Vacuna vacuna) {
        vacuna.setMascota(new Mascota());
        vacuna.getMascota().setId(mascotaId);
        Vacuna nuevaVacuna = vacunaService.registrarVacuna(vacuna);
        return ResponseEntity.status(201).body(nuevaVacuna);
    }
}