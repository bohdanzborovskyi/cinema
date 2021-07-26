package com.zbodya.model.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.model.Entity.Movie;
import com.zbodya.model.Entity.Session;

@Repository
public interface SessionRepo extends CrudRepository<Session, Long> 
{
		List<Session> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);
		List<Session> findAllByMovie(Movie movie);
}
