package com.example.ApiPetTrack.controller;

/**
 * 
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ApiPetTrack.model.Evento;
import com.example.ApiPetTrack.service.EventoService;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/mascotas/{mascotaId}/eventos")
    public List<Evento> listarPorMascota(@PathVariable Long mascotaId) {
        return eventoService.listarPorMascota(mascotaId);
    }

    @GetMapping("/eventos/{id}")
    public Evento obtenerEvento(@PathVariable Long id) {
        return eventoService.obtenerPorId(id);
    }

    @PostMapping("/mascotas/{mascotaId}/eventos")
    public Evento crearEvento(@PathVariable Long mascotaId, @RequestBody Evento evento) {
        return eventoService.crear(mascotaId, evento);
    }

    @PutMapping("/eventos/{id}")
    public Evento actualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.actualizar(id, evento);
    }

    @DeleteMapping("/eventos/{id}")
    public void eliminarEvento(@PathVariable Long id) {
        eventoService.eliminar(id);
    }
}
