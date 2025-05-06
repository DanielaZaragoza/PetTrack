package com.example.ApiPetTrack.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String sexo; 

    private String raza;

    private Integer edad;

    private String color;

    private Double peso;

    @Column(name = "senias_particulares", length = 45)
    private String seniasParticulares;

    private String foto;

    // Relación: Muchas mascotas pueden pertenecer a un solo usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

	@OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Desparasitacion> desparasitaciones;


	public List<Desparasitacion> getDesparasitaciones() {
		return desparasitaciones;
	}

	public void setDesparasitaciones(List<Desparasitacion> desparasitaciones) {
		this.desparasitaciones = desparasitaciones;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the raza
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * @param raza the raza to set
	 */
	public void setRaza(String raza) {
		this.raza = raza;
	}

	/**
	 * @return the edad
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return the seniasParticulares
	 */
	public String getSeniasParticulares() {
		return seniasParticulares;
	}

	/**
	 * @param seniasParticulares the seniasParticulares to set
	 */
	public void setSeniasParticulares(String seniasParticulares) {
		this.seniasParticulares = seniasParticulares;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
    // Getters y Setters

}
