package com.example.ApiPetTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiPetTrack.model.Usuario;
import com.example.ApiPetTrack.repository.UsuarioRepository;

/**
 * 
 */

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (!usuario.getContrasena().equals(contrasena)) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
        return usuario;
    }


}
