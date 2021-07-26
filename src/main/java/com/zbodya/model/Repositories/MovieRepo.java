package com.zbodya.model.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.model.Entity.Movie;

@Repository
public interface MovieRepo extends CrudRepository<Movie, Long>
{
	List<Movie> findAllByTitle(String title);
}
