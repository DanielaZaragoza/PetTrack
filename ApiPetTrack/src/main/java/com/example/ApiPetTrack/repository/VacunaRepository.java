package com.example.ApiPetTrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiPetTrack.model.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
    List<Vacuna> findByMascotaId(Long mascotaId);
    
   Optional <Vacuna> findByIdAndMascotaId(Long id, Long mascotaId);
}