package com.zbodya.model.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zbodya.model.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> 
{
	List<User> findAll();
	List<User> findAllBySurname(String surname);
}
