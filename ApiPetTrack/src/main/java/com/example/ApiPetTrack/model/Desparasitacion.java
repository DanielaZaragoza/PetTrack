package com.example.ApiPetTrack.model;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "desparas34itacion")
public class Desparasitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_aplicacion")
    private Timestamp fechaAplicacion;

    @Column(name = "peso_kg")
    private Double pesoKg;

    @Column(name = "dosis")
    private Double dosis;

    @Column(name = "fecha_proxima_aplicacion")
    private Timestamp fechaProximaAplicacion;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

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

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
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
// Getters y setters
}
