package com.example.ApiPetTrack.repository;

/**
 * 
 */


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiPetTrack.model.HistorialMedico;

public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
    List<HistorialMedico> findByMascotaId(Long mascotaId);
    Optional<HistorialMedico> findByIdAndMascotaId(Long id, Long mascotaId);
}