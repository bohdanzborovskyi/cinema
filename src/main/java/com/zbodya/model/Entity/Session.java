package com.zbodya.model.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Session
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private ScreenRoom room;
	
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Reservation> reservations;
	
	private LocalDateTime dateTime;

	public long getId() {
		return id;
	}	

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		movie.getSessions().add(this);
	}

	public ScreenRoom getRoom() {
		return room;
	}

	public void setRoom(ScreenRoom room) {
		this.room = room;
		room.getSessions().add(this);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Session() {
		super();
		this.reservations = new ArrayList<>();
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
	

}
