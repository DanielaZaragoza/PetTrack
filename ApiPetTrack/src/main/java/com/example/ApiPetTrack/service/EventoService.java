package com.example.ApiPetTrack.service;

/**
 * 
 */


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiPetTrack.model.Evento;
import com.example.ApiPetTrack.model.Mascota;
import com.example.ApiPetTrack.repository.EventoRepository;
import com.example.ApiPetTrack.repository.MascotaRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepo;

    @Autowired
    private MascotaRepository mascotaRepo;

    public List<Evento> listarPorMascota(Long mascotaId) {
        return eventoRepo.findByMascotaId(mascotaId);
    }

    public Evento obtenerPorId(Long id) {
        return eventoRepo.findById(id).orElse(null);
    }

    public Evento crear(Long mascotaId, Evento evento) {
        Mascota mascota = mascotaRepo.findById(mascotaId).orElse(null);
        if (mascota != null) {
            evento.setMascota(mascota);
            return eventoRepo.save(evento);
        }
        return null;
    }

    public Evento actualizar(Long id, Evento nuevoEvento) {
        return eventoRepo.findById(id).map(evento -> {
            evento.setTitulo(nuevoEvento.getTitulo());
            evento.setDescripcion(nuevoEvento.getDescripcion());
            evento.setDireccion(nuevoEvento.getDireccion());
            evento.setTipo(nuevoEvento.getTipo());
            evento.setRepetir(nuevoEvento.getRepetir());
            return eventoRepo.save(evento);
        }).orElse(null);
    }

    public void eliminar(Long id) {
        eventoRepo.deleteById(id);
    }
}
