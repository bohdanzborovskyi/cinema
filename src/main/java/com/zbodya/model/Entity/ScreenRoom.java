package com.zbodya.model.Entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class ScreenRoom 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Session> sessions;
	
	@Lob
	private HashMap<Integer,SeatType> seats;

	public long getId() {
		return id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScreenRoom() {
		super();
		this.seats = new HashMap<>();
		for(int i=1; i<31; i++) 
		{
			seats.put(i, SeatType.FREE);
		}
		this.sessions = new ArrayList<>();
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public HashMap<Integer, SeatType> getSeats() {
		return seats;
	}

	public void setSeats(HashMap<Integer, SeatType> seats) {
		this.seats = seats;
	}
	
	
	
	
}
