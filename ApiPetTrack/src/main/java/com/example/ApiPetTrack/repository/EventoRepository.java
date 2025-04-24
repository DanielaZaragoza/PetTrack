package com.example.ApiPetTrack.repository;

/**
 * 
 */


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiPetTrack.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByMascotaId(Long mascotaId);
}