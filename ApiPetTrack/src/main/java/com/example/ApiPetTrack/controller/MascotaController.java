package com.example.ApiPetTrack.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import com.example.ApiPetTrack.aws.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.model.Usuario;
import com.example.ApiPetTrack.service.MascotaService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/mascotas")
public class MascotaController {

    private final ObjectMapper objectMapper;
    public MascotaController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private S3Service s3Service;

    // Endpoint para listar todas las mascotas de un usuario
    @GetMapping
    public List<Mascota> listarMascotas(@PathVariable Long usuarioId) {
        return mascotaService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascota(@PathVariable Long usuarioId, @PathVariable Long id) {
        Mascota mascota = mascotaService.obtenerPorId(id);
        if (mascota != null && mascota.getUsuario().getId().equals(usuarioId)) {
            return ResponseEntity.ok(mascota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@PathVariable Long usuarioId, @RequestBody Mascota mascota) {
        mascota.setUsuario(new Usuario());
        mascota.getUsuario().setId(usuarioId);
        Mascota nuevaMascota = mascotaService.crear(mascota);
        return ResponseEntity.status(201).body(nuevaMascota);
    }

    // Nuevo endpoint para crear mascota con imagen (EXACTAMENTE lo que pediste)

    @PostMapping(path = "/con-imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Mascota> crearMascotaConImagen(
            @PathVariable Long usuarioId,
            @RequestPart("mascota") String mascotaStr,
            @RequestPart("foto") MultipartFile foto) {

        try {
            // Convertir el JSON string a objeto Mascota
            Mascota mascota = objectMapper.readValue(mascotaStr, Mascota.class);

            // Asociar el usuario
            mascota.setUsuario(new Usuario());
            mascota.getUsuario().setId(usuarioId);

            // Subir imagen a S3 y guardar URL
            String fileKey = s3Service.uploadFile(foto);
            String fotoUrl = s3Service.getFileUrl(fileKey);
            mascota.setFoto(fotoUrl);

            // Guardar la mascota
            Mascota nuevaMascota = mascotaService.crear(mascota);
            return ResponseEntity.status(201).body(nuevaMascota);

        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            return ResponseEntity.internalServerError().build();
        }
    }
    // Endpoint para actualizar los datos de una mascota (sin cambios)
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long usuarioId, @PathVariable Long id, @RequestBody Mascota mascota) {
        mascota.setUsuario(new Usuario());
        mascota.getUsuario().setId(usuarioId);
        Mascota mascotaActualizada = mascotaService.actualizar(id, mascota);
        if (mascotaActualizada != null) {
            return ResponseEntity.ok(mascotaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para obtener detalles completos de una mascota (sin cambios)
    @GetMapping("/{id}/detalles")
    public ResponseEntity<Mascota> obtenerDetallesMascota(@PathVariable Long id) {
        Mascota mascota = mascotaService.obtenerPorId(id);
        if (mascota != null) {
            return ResponseEntity.ok(mascota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}