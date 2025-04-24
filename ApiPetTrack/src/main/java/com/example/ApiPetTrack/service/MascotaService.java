package com.example.ApiPetTrack.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.repository.MascotaRepository;

/**
 * 
 */

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listarPorUsuario(Long usuarioId) {
        return mascotaRepository.findByUsuarioId(usuarioId);
    }

    public Mascota obtenerPorId(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public Mascota crear(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizar(Long id, Mascota nuevaMascota) {
        return mascotaRepository.findById(id).map(mascota -> {
            mascota.setNombre(nuevaMascota.getNombre());
            mascota.setSexo(nuevaMascota.getSexo());
            mascota.setRaza(nuevaMascota.getRaza());
            mascota.setEdad(nuevaMascota.getEdad());
            return mascotaRepository.save(mascota);
        }).orElse(null);
    }
}
