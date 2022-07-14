package com.example.obejercicio456.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String nombre;
	
	public Laptop() {
		
	}
	
	public Laptop(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + "]";
	}
	
	
}
