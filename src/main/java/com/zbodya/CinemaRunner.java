package com.zbodya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zbodya.Service.InitDBService;
import com.zbodya.Service.WebService;


@Component
public class CinemaRunner implements CommandLineRunner 
{
	
	@Autowired
	InitDBService db;
	
	@Autowired
	WebService web;

	@Override
	public void run(String... args) throws Exception 
	{
		db.initDB();
		web.performAllRequests();
	}

}
