package com.zbodya.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbodya.model.Entity.Movie;
import com.zbodya.model.Entity.Reservation;
import com.zbodya.model.Entity.ScreenRoom;
import com.zbodya.model.Entity.Session;
import com.zbodya.model.Entity.TicketType;
import com.zbodya.model.Entity.User;
import com.zbodya.model.Repositories.MovieRepo;
import com.zbodya.model.Repositories.ReservatioRepo;
import com.zbodya.model.Repositories.ScreenRoomRepo;
import com.zbodya.model.Repositories.SessionRepo;
import com.zbodya.model.Repositories.UserRepo;

@Service
public class InitDBService 
{
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	ReservatioRepo reservationRepo;
	
	@Autowired
	ScreenRoomRepo roomRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	public void initDB() 
	{
		// create screnRooms
		ScreenRoom room = createScreenRoom("Action room");
		ScreenRoom room2 = createScreenRoom("Horror room");
		ScreenRoom room3 = createScreenRoom("Comedy room");
		roomRepo.saveAll(Arrays.asList(room,room2,room3));

		//create movies
		Movie movie = createMovie("Avatar", "2h 30m");
		Movie movie2 = createMovie("Terminator", "1h 45m");
		Movie movie3 = createMovie("Conjuring", "1h 40m");
		Movie movie4 = createMovie("Home alone", "1h 35m");
		movieRepo.saveAll(Arrays.asList(movie,movie2,movie3,movie4));
		
		//create sessions
		Session session = createSession(movie, room, LocalDateTime.of(2021, 9, 26, 14, 00));
		Session session2 = createSession(movie, room, LocalDateTime.of(2021, 9, 28, 14, 00));
		Session session3 = createSession(movie4, room3, LocalDateTime.of(2020, 9, 30, 17, 00));
		Session session4 = createSession(movie4, room3, LocalDateTime.of(2021, 9, 28, 17, 00));
		Session session5 = createSession(movie3, room2, LocalDateTime.of(2021, 9, 30, 20, 00));
		Session session6 = createSession(movie3, room2, LocalDateTime.of(2021, 9, 28, 20, 00));
		sessionRepo.saveAll(Arrays.asList(session,session2,session3,session4,session5,session6));
		
		//create users
		User u = createUser("Dan", "Holowitz");
		User u2 = createUser("Tom", "Brady");
		userRepo.saveAll(Arrays.asList(u,u2));


	}	
	
	
	public User createUser(String name, String surname) 
	{
		User u = new User();
		u.setName(name);
		u.setSurname(surname);
		return u;
	}
	
	public Movie createMovie(String title, String duration) 
	{
		Movie m = new Movie();
		m.setDuration(duration);
		m.setTitle(title);
		return m;
	}
	
	public ScreenRoom createScreenRoom(String name) 
	{
		ScreenRoom sr = new ScreenRoom();
		sr.setName(name);
		return sr;
	}
	
	public Session createSession(Movie m, ScreenRoom sr, LocalDateTime time) 
	{
		Session s = new Session();
		s.setDateTime(time);
		s.setMovie(m);
		s.setRoom(sr);
		return s;
	}
	
	public Reservation createReservation(User u, Session s, int seat, TicketType ticket) 
	{
		Reservation r = new Reservation();
		r.createReservation(u, s, seat, ticket);
		return r;
	}
	
	
}
