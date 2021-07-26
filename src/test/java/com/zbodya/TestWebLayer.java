package com.zbodya;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zbodya.Service.CinemaService;
import com.zbodya.model.Repositories.MovieRepo;
import com.zbodya.model.Repositories.ReservatioRepo;
import com.zbodya.model.Repositories.ScreenRoomRepo;
import com.zbodya.model.Repositories.SessionRepo;
import com.zbodya.model.Repositories.UserRepo;


@SpringBootTest
@AutoConfigureMockMvc
public class TestWebLayer 
{

	@Autowired 
	MockMvc mvc;
	
	@Autowired
	CinemaService cinema;
	
	@Autowired
	ScreenRoomRepo roomRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired 
	MovieRepo movieRepo;
	
	@Autowired
	ReservatioRepo reservRepo;
	
	@Test
	public void availableMovies() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.
				get("http://localhost:8080/cinemaApi/availableMovies")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());					
	}
	
	@Test
	public void getMovieDates() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.
				get("http://localhost:8080/cinemaApi/getMovieDates?movie=Avatar")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	

	@Test
	public void getSessionDetails() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.
				get("http://localhost:8080/cinemaApi/getSessionDetails")
				.param("sessionID", "10")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void selectMovie() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.
				get("/cinemaApi/selectMovie")
				.param("sessionID", "11")
				.param("seat", "20")
				.param("ticket", "adult")
				.param("user_surname","Holowits")
				.param("user_name", "Dan")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));				
	}
	
	
	
}
