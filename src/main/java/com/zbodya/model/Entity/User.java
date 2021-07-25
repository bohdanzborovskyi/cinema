package com.zbodya.model.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String surname;
	
	@OneToMany( mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)	
	private List<Reservation> reservations;

	public long getId() {
		return id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public User() {
		super();
		this.reservations = new ArrayList<>();		
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		this.reservations = new ArrayList<>();		
	}
	
	
	
	
	
	
	
}
