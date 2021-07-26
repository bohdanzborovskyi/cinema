package com.zbodya.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zbodya.Service.CinemaService;
import com.zbodya.model.Entity.Reservation;
import com.zbodya.model.Entity.Session;
import com.zbodya.model.Entity.TicketType;
import com.zbodya.model.Entity.User;
import com.zbodya.model.Repositories.SessionRepo;


@RestController
@RequestMapping("/cinemaApi")
public class CinemaController 
{
	
	@Autowired 
	CinemaService cinemaService;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@GetMapping("/availableMovies")
	public ArrayList<String> getAvailableMovies()
	{
		return cinemaService.getAvailableMovies(LocalDateTime.now(), LocalDateTime.of(2022, 7, 19, 10, 10));
	}
	
	@GetMapping("/getMovieDates")
	public ArrayList<String> getMovieDates(@RequestParam("movie")String title)
	{
		return cinemaService.getMovieSessions(title);
	}
	
	@GetMapping("/selectMovie")
	public String createReservation(@RequestParam("sessionID")long sessionID, @RequestParam("seat") int seat,
									@RequestParam("ticket") String ticket, @RequestParam("user_surname") String surname,
									@RequestParam("user_name") String name) 
	{		
		
		if(cinemaService.checkUser(name, surname))
		{
			if(sessionRepo.findById(sessionID).get().getRoom().getSeats().get(seat).name().equals("RESERVATED"))
			{
				return "Your seat is already reservated! Please choose another seat!";
			}
			else if(LocalDateTime.now().plusMinutes(15).isAfter(sessionRepo.findById(sessionID).get().getDateTime()))
			{
				return "It is too late for reservation!";
			}else 
			{
				Session session = sessionRepo.findById(sessionID).get();
				TicketType ticketType = cinemaService.returnTicketType(ticket);
				User user = cinemaService.findUser(name, surname);
				Reservation reserv = cinemaService.createReservation(sessionID, user, seat, ticketType);
				return "User: " + user.getName() + " " + user.getSurname() + ", seat:" + seat + ", ticket type: " + ticketType.toString() 
				+ ", movie:" + session.getMovie().getTitle() + ", date: " + session.getDateTime() + ", amount to pay: " + ticketType.getPrice() + " PLN." ;
			}
		}
		else 
		{
			return "Invalid user name or surname! It must contains at least 3 characters and start with capital character!";
		}
	}
	
	
	@GetMapping("/getSessionDetails")
	public String getSessionDetails(@RequestParam("sessionID")long sessionID) 
	{
		Session session = sessionRepo.findById(sessionID).get();		
		return "Screnning room: " + session.getRoom().getName() + " available seats: " + session.getRoom().getSeats() 
				+ " date of session: " + session.getDateTime();
	}
}
