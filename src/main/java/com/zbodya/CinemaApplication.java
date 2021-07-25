package com.zbodya;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zbodya.Service.InitDBService;





@SpringBootApplication
public class CinemaApplication 
{
	
	
	
	@Autowired
	InitDBService initDB;

	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);		
		
	}
	
	
	
	@PostConstruct
	@Transactional
	private void initDB() 
	{
	//	initDB.initDB();
	}
	
	

}
