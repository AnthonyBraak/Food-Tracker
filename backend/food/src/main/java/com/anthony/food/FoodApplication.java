package com.anthony.food;

import com.anthony.food.entity.FoodInformation;
import com.anthony.food.repository.FoodInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class FoodApplication implements CommandLineRunner {

	@Autowired
	private FoodInformationRepository foodInformationRepository;

	public static void main(String[] args) {
		SpringApplication.run(FoodApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// No manual seeding — data.sql handles all database initialization
	}
}
