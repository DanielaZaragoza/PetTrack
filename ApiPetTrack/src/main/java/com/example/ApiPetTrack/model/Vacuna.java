package com.example.ApiPetTrack.model;

import java.sql.Timestamp;

/**
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_aplicacion")
    private Timestamp fechaAplicacion;

    @Column(name = "tipo_vacuna", length = 100)
    private String tipoVacuna;

    @Column(name = "fecha_proxima_aplicacion")
    private Timestamp fechaProximaAplicacion;

    // Relación con Mascota (Cada vacuna pertenece a una mascota)
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Timestamp fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public Timestamp getFechaProximaAplicacion() {
        return fechaProximaAplicacion;
    }

    public void setFechaProximaAplicacion(Timestamp fechaProximaAplicacion) {
        this.fechaProximaAplicacion = fechaProximaAplicacion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}