package com.zbodya.Service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WebService
{	
	
	RestTemplate rest = new RestTemplate();
	
	public void getAvailableMovies() 
	{		
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for available movies from now!");
		ResponseEntity<String[]>response = rest.getForEntity("http://localhost:8081/cinemaApi/availableMovies", String[].class);
		String[] movies = response.getBody();
		System.out.println("Available movie:");
		for(String s : movies) 
		{
			System.out.println(s);
		}
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void getMovieAvailableDates() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for available dates for Avatar movie!");
		ResponseEntity<String[]>response = rest.getForEntity("http://localhost:8081/cinemaApi/getMovieDates?movie=Avatar", String[].class);
		String[] dates = response.getBody();
		System.out.println("Available dates for Avatar movie:");
		for(String d : dates) 
		{
			System.out.println(d);
		}
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void getSessionDetails() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for session details!");
		ResponseEntity<String>response = rest.getForEntity("http://localhost:8081/cinemaApi/getSessionDetails?sessionID=10", String.class);
		String details = response.getBody();
		System.out.println("Session details for session nr. 10:");		
		System.out.println(details);		
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void createReservationOK() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for create reservation!");
		ResponseEntity<String>response = rest.getForEntity("http://localhost:8081/cinemaApi/selectMovie?sessionID=11&seat=12&ticket=student&user_surname=Pakk&user_name=Grzmilą", String.class);
		String reservation = response.getBody();
		System.out.println("Reservation status:");		
		System.out.println(reservation);		
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void createReservationInvalidUserName() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for create reservation!");
		ResponseEntity<String>response = rest.getForEntity("http://localhost:8081/cinemaApi/selectMovie?sessionID=11&seat=12&ticket=student&user_surname=Pa&user_name=Grzmilą", String.class);
		String reservation = response.getBody();
		System.out.println("Reservation status:");		
		System.out.println(reservation);		
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void createReservationSeatAlreadyReservated() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for create reservation!");
		ResponseEntity<String>response = rest.getForEntity("http://localhost:8081/cinemaApi/selectMovie?sessionID=11&seat=12&ticket=student&user_surname=Pakk&user_name=Grzmilą", String.class);
		String reservation = response.getBody();
		System.out.println("Reservation status:");		
		System.out.println(reservation);		
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void createReservationLateDate() 
	{
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Request for create reservation!");
		ResponseEntity<String>response = rest.getForEntity("http://localhost:8081/cinemaApi/selectMovie?sessionID=10&seat=20&ticket=student&user_surname=Pakk&user_name=Grzmilą", String.class);
		String reservation = response.getBody();
		System.out.println("Reservation status:");		
		System.out.println(reservation);		
		System.out.println("Request OK");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
	}
	
	public void performAllRequests() 
	{
		getAvailableMovies();
		getMovieAvailableDates();
		getSessionDetails();
		createReservationOK();
		createReservationInvalidUserName();
		createReservationSeatAlreadyReservated();
		createReservationLateDate();
	}
	
	
	
}
