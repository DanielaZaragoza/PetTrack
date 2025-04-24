package com.example.ApiPetTrack.controller;

/**
 * 
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ApiPetTrack.model.HistorialMedico;
import com.example.ApiPetTrack.service.HistorialMedicoService;

@RestController
@RequestMapping("/mascotas/{mascotaId}/historial-medico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialService;

    @GetMapping
    public List<HistorialMedico> listar(@PathVariable Long mascotaId) {
        return historialService.listarPorMascota(mascotaId);
    }

    @GetMapping("/{id}")
    public HistorialMedico obtenerPorId(@PathVariable Long mascotaId, @PathVariable Long id) {
        return historialService.obtenerPorId(id, mascotaId);
    }

    @PostMapping
    public HistorialMedico crear(@PathVariable Long mascotaId, @RequestBody HistorialMedico historial) {
        return historialService.crear(mascotaId, historial);
    }
}