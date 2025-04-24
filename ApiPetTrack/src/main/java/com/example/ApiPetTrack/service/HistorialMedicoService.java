package com.example.ApiPetTrack.service;

/**
 * 
 */


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiPetTrack.model.HistorialMedico;
import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.repository.HistorialMedicoRepository;
import com.example.ApiPetTrack.repository.MascotaRepository;

@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialRepo;

    @Autowired
    private MascotaRepository mascotaRepo;

    public List<HistorialMedico> listarPorMascota(Long mascotaId) {
        return historialRepo.findByMascotaId(mascotaId);
    }

    public HistorialMedico obtenerPorId(Long id, Long mascotaId) {
        return historialRepo.findByIdAndMascotaId(id, mascotaId).orElse(null);
    }

    public HistorialMedico crear(Long mascotaId, HistorialMedico historial) {
        Mascota mascota = mascotaRepo.findById(mascotaId).orElse(null);
        if (mascota != null) {
            historial.setMascota(mascota);
            return historialRepo.save(historial);
        }
        return null;
    }
}