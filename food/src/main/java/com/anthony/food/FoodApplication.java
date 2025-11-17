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
		// Seed sample foods with all nutrient fields
		FoodInformation avocado = new FoodInformation(
				"Avocado",
				new BigDecimal("322"),    // kcal
				new BigDecimal("29.5"),   // fat
				new BigDecimal("4.2"),    // saturated fat
				new BigDecimal("17"),     // carbs
				new BigDecimal("0.7"),    // sugars
				new BigDecimal("13.5"),   // fibre
				new BigDecimal("4"),      // protein
				new BigDecimal("0.01")    // salt
		);

		FoodInformation kidneyBeans = new FoodInformation(
				"Kidney Beans",
				new BigDecimal("127"),
				new BigDecimal("0.5"),
				new BigDecimal("0.1"),
				new BigDecimal("22.8"),
				new BigDecimal("0.3"),
				new BigDecimal("6.4"),
				new BigDecimal("8.7"),
				new BigDecimal("0.01")
		);

		FoodInformation tomatoes = new FoodInformation(
				"Tomatoes",
				new BigDecimal("18"),
				new BigDecimal("0.2"),
				new BigDecimal("0"),
				new BigDecimal("3.9"),
				new BigDecimal("2.6"),
				new BigDecimal("1.2"),
				new BigDecimal("0.9"),
				new BigDecimal("0.01")
		);

		FoodInformation testFood = new FoodInformation(
				"Test Food",
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO,
				BigDecimal.ZERO
		);

		// Save to DB
		foodInformationRepository.save(avocado);
		foodInformationRepository.save(kidneyBeans);
		foodInformationRepository.save(tomatoes);
		foodInformationRepository.save(testFood);

	}
}
