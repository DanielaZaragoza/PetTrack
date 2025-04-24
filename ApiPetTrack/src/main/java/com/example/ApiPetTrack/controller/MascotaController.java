package com.example.ApiPetTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.model.Usuario;
import com.example.ApiPetTrack.service.MascotaService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // Endpoint para listar todas las mascotas de un usuario
    @GetMapping
    public List<Mascota> listarMascotas(@PathVariable Long usuarioId) {
        return mascotaService.listarPorUsuario(usuarioId);
    }

    // Endpoint para obtener una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascota(@PathVariable Long usuarioId, @PathVariable Long id) {
        Mascota mascota = mascotaService.obtenerPorId(id);
        if (mascota != null && mascota.getUsuario().getId().equals(usuarioId)) {
            return ResponseEntity.ok(mascota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para crear una nueva mascota
    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@PathVariable Long usuarioId, @RequestBody Mascota mascota) {
        mascota.setUsuario(new Usuario());  // Aseguramos que la mascota esté asociada al usuario correcto
        mascota.getUsuario().setId(usuarioId); // Asociamos el usuario de la URL
        Mascota nuevaMascota = mascotaService.crear(mascota);
        return ResponseEntity.status(201).body(nuevaMascota);
    }

    // Endpoint para actualizar los datos de una mascota
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long usuarioId, @PathVariable Long id, @RequestBody Mascota mascota) {
        mascota.setUsuario(new Usuario());  // Aseguramos que la mascota esté asociada al usuario correcto
        mascota.getUsuario().setId(usuarioId); // Asociamos el usuario de la URL
        Mascota mascotaActualizada = mascotaService.actualizar(id, mascota);
        if (mascotaActualizada != null) {
            return ResponseEntity.ok(mascotaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener detalles completos de una mascota (vacunas, eventos, etc.)
    @GetMapping("/{id}/detalles")
    public ResponseEntity<Mascota> obtenerDetallesMascota(@PathVariable Long id) {
        Mascota mascota = mascotaService.obtenerPorId(id);
        if (mascota != null) {
            // Puedes agregar la lógica para obtener detalles adicionales (vacunas, eventos, etc.)
            return ResponseEntity.ok(mascota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}