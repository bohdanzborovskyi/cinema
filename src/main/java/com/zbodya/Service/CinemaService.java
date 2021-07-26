package com.zbodya.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

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
public class CinemaService 
{
	
	@Autowired
	ScreenRoomRepo roomRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired 
	MovieRepo movieRepo;

	@Autowired 
	ReservatioRepo reservRepo;
	
	@Autowired
	UserRepo userRepo;
	
	
	public Map<Movie,LocalDateTime> getAvailableMoviesWithDate(LocalDateTime start, LocalDateTime end)
	{
		ArrayList<Session> sessions = (ArrayList<Session>) sessionRepo.findAllByDateTimeBetween(start, end);
		Map<Movie,LocalDateTime> movies = new TreeMap<>(Comparator.comparing(Movie::getTitle).thenComparing(Movie::getDuration));
		for(Session s : sessions) 
		{			
			movies.put(s.getMovie(),s.getDateTime());
		}		
		return movies;
	}
	
	public ArrayList<String> getAvailableMovies(LocalDateTime start, LocalDateTime end)
	{
		ArrayList<Session> sessions = (ArrayList<Session>) sessionRepo.findAllByDateTimeBetween(start, end);			
		SortedSet<Movie> moviesSorted = new TreeSet<>(Comparator.comparing(Movie::getTitle).thenComparing(Movie::getDuration));
		for(Session s : sessions) 
		{
			moviesSorted.add(s.getMovie());
		}
		ArrayList<String> movies = new ArrayList<>();
		for(Movie m : moviesSorted) 
		{
			movies.add(m.getTitle() + " " + m.getDuration());
		}
		return movies;
	}
	
	public ArrayList<String> getMovieSessions(String title)
	{
		Movie movie = movieRepo.findAllByTitle(title).get(0);
		System.out.println(movie.getTitle());
		ArrayList<Session>sessions = (ArrayList<Session>) sessionRepo.findAllByMovie(movie);
		Collections.sort(sessions, Comparator.comparing(Session::getDateTime));
		ArrayList<String>movieSessions = new ArrayList<>();
		for(Session s: sessions) 
		{
			movieSessions.add(s.getId() + " " + s.getMovie().getTitle() + " " + s.getDateTime() + " room:" + s.getRoom().getName());
		}
		return movieSessions;
	}
	
	public Reservation createReservation(long id, User u, int seat, TicketType ticket) 
	{
		Session session = sessionRepo.findById(id).get();
		Reservation reservation = new Reservation();
		reservation.createReservation(u, session, seat, ticket);
		reservRepo.save(reservation);
		roomRepo.save(reservation.getSession().getRoom());
		return reservation;
	}
	
	public TicketType returnTicketType(String ticket) 
	{		
		if(ticket.toUpperCase().equals("STUDENT"))
			return TicketType.STUDENT;
		else if(ticket.toUpperCase().equals("CHILD"))
			return TicketType.CHILD;
		else 
			return TicketType.ADULT;
	}
	
	public User findUser(String name, String surname) 
	{
		List<User> users = userRepo.findAllBySurname(surname);
		User newUser = users.stream().filter(user-> name.equals(user.getName())).findAny().orElse(new User(name,surname));
		userRepo.save(newUser);
		return newUser;
	}
	
	public boolean checkUser(String name, String surname) 
	{
		Pattern patternTwoSurname = Pattern.compile("[A-ZŹŻŁĆÓŚĄĘ]{1}[a-zżźłćśóąę]{2,}-[A-ZŹŻŁĆÓŚĄĘ]{1}[a-zżźłćśóąę]{2,}");
		Pattern patternNameSurname = Pattern.compile("[A-ZŹŻŁĆÓŚĄĘ]{1}[a-zżźłćśóąę]{2,}");
		if((patternNameSurname.matcher(surname).matches() || patternTwoSurname.matcher(surname).matches())
				&& (patternNameSurname.matcher(name).matches()))
			return true;
		else 
			return false;
		


		
		
	}

}
