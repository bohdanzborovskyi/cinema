package com.zbodya;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.zbodya.Service.CinemaService;
import com.zbodya.model.Entity.Movie;
import com.zbodya.model.Entity.Session;
import com.zbodya.model.Repositories.MovieRepo;
import com.zbodya.model.Repositories.SessionRepo;

@ComponentScan("com.zbodya")
@DataJpaTest
public class DBTest 
{

	@Autowired 
	MovieRepo movieRepo;
	
	@Autowired
	CinemaService cinemaService;
	
	@Autowired 
	SessionRepo sessionRepo;
	
	@Test
	public void should_not_be_empty() 
	{
		Iterable<Movie> movies = movieRepo.findAll();
		assertThat(movies).isNotEmpty();
	}
	
	@Test 
	public void should_save_movie() 
	{
		Movie movie = new Movie();
		movie.setTitle("Avengers");
		movie.setDuration("2h 30m");
		
		Movie m = movieRepo.save(movie);
		assertThat(m).hasFieldOrPropertyWithValue("title", "Avengers");
		assertThat(m).hasFieldOrPropertyWithValue("duration", "2h 30m");
	}
	
	@Test 
	public void should_return_movies_by_title() 
	{
		List<Movie> movies = movieRepo.findAllByTitle("Avatar");
		
		assertThat(movies).hasSize(1);
		assertThat(movies.get(0)).hasFieldOrPropertyWithValue("title", "Avatar");
	}
	
	@Test
	public void should_return_sessions_by_movie() 
	{
		Movie avatar = movieRepo.findAllByTitle("Avatar").get(0);
		List<Session> sessions = sessionRepo.findAllByMovie(avatar);
		
		assertThat(sessions).hasSize(2);
		assertThat(sessions.get(0).getMovie()).hasFieldOrPropertyWithValue("title", "Avatar");
	}
	
	@Test
	public void get_movies_by_date() 
	{
		ArrayList<String>moviesSorted = cinemaService.getAvailableMovies(LocalDateTime.of(2020, 7, 19, 10, 10), LocalDateTime.of(2022, 7, 19, 10, 10));
		
		assertThat(moviesSorted.get(0)).isEqualTo("Avatar 2h 30m");
	}
}
