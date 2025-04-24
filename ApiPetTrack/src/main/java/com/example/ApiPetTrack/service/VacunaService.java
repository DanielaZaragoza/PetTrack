package com.example.ApiPetTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */


import com.example.ApiPetTrack.model.Vacuna;
import com.example.ApiPetTrack.repository.VacunaRepository;

@Service
public class VacunaService {

    @Autowired
    private VacunaRepository vacunaRepository;

    // Listar todas las vacunas de una mascota
    public List<Vacuna> listarVacunasPorMascota(Long mascotaId) {
        return vacunaRepository.findByMascotaId(mascotaId);
    }

    // Obtener una vacuna por ID
    public Vacuna obtenerVacuna(Long id, Long mascotaId) {
        return vacunaRepository.findByIdAndMascotaId(id, mascotaId).orElse(null);
    }

    // Registrar una nueva vacuna
    public Vacuna registrarVacuna(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }
}
