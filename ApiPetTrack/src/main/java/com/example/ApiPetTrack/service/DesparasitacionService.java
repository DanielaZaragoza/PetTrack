package com.example.ApiPetTrack.service;

import com.example.ApiPetTrack.model.Desparasitacion;
import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.repository.DesparasitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesparasitacionService {

    @Autowired
    private DesparasitacionRepository desparasitacionRepo;

    public List<Desparasitacion> listarPorMascota(Long mascotaId) {
        return desparasitacionRepo.findByMascotaId(mascotaId);
    }

    public Optional<Desparasitacion> obtenerPorId(Long id) {
        return desparasitacionRepo.findById(id);
    }

    public Desparasitacion registrar(Long mascotaId, Desparasitacion desparasitacion) {
        Mascota mascota = new Mascota();
        mascota.setId(mascotaId);
        desparasitacion.setMascota(mascota);
        return desparasitacionRepo.save(desparasitacion);
    }
}
