package com.zbodya.model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.model.Repositories.ScreenRoomRepo;

@Entity
public class Reservation 
{

	 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Session session;
	
	@ManyToOne
	private User user;
	
	private TicketType ticket;
	
	private int seat;

	public long getId() {
		return id;
	}	

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}	

	public TicketType getTicket() {
		return ticket;
	}

	public void setTicket(TicketType ticket) {
		this.ticket = ticket;
	}

	public Reservation() 
	{
		super();
	}
	
	public void createReservation(User u, Session s, int seat, TicketType ticket) 
	{
		this.setSession(s);
		this.setUser(u);
		u.getReservations().add(this);
		s.getReservations().add(this);
		s.getRoom().getSeats().put(seat, SeatType.RESERVATED);
		this.setSeat(seat);
		this.setTicket(ticket);		
	}
	
	
	
	
	
}
