package com.example.ApiPetTrack.repository;

import com.example.ApiPetTrack.model.Desparasitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesparasitacionRepository extends JpaRepository<Desparasitacion, Long> {
    List<Desparasitacion> findByMascotaId(Long mascotaId);
}
