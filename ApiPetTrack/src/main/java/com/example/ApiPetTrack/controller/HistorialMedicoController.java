package com.example.ApiPetTrack.controller;

import com.example.ApiPetTrack.aws.S3Service;
import com.example.ApiPetTrack.model.HistorialMedico;
import com.example.ApiPetTrack.service.HistorialMedicoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mascotas/{mascotaId}/historial-medico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialService;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public List<HistorialMedico> listar(@PathVariable Long mascotaId) {
        return historialService.listarPorMascota(mascotaId);
    }

    @GetMapping("/{id}")
    public HistorialMedico obtenerPorId(@PathVariable Long mascotaId, @PathVariable Long id) {
        return historialService.obtenerPorId(id, mascotaId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HistorialMedico crear(
            @PathVariable Long mascotaId,
            @RequestPart("historial") String historialJson,
            @RequestPart("archivo") MultipartFile archivo) {

        // Convertir JSON a objeto
        ObjectMapper objectMapper = new ObjectMapper();
        HistorialMedico historial;
        try {
            historial = objectMapper.readValue(historialJson, HistorialMedico.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el historial", e);
        }

        // Subir archivo a S3
        String archivoUrl = s3Service.uploadFile(archivo, "historiales");
        historial.setRuta(archivoUrl); // Usamos "ruta" como campo para la URL del archivo

        // Guardar historial
        return historialService.crear(mascotaId, historial);
    }
}
