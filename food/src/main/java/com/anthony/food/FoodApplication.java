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
	FoodInformationRepository foodInformationRepository;
	public static void main(String[] args) {
		SpringApplication.run(FoodApplication.class, args);
	}
	@Override
	public void run(String... args){
		FoodInformation foodInformationOne = new FoodInformation("Avocado", new BigDecimal(322), new BigDecimal(13));
		FoodInformation foodInformationTwo = new FoodInformation("Kidney Beans", new BigDecimal(127), new BigDecimal("6.4"));
		FoodInformation foodInformationThree = new FoodInformation("Tomatoes", new BigDecimal(18), new BigDecimal("1.2"));
		FoodInformation foodInformationFour = new FoodInformation("Test", new BigDecimal(0), new BigDecimal(0));
		foodInformationRepository.save(foodInformationOne);
		foodInformationRepository.save(foodInformationTwo);
		foodInformationRepository.save(foodInformationThree);
		foodInformationRepository.save(foodInformationFour);

		foodInformationRepository.findByFoodName("Avocado").forEach(System.out::println);
		foodInformationRepository.findByFoodKcal(new BigDecimal(127)).forEach(System.out::println);
		foodInformationRepository.findByFoodFibre(new BigDecimal("1.2")).forEach(System.out::println);
		System.out.println(foodInformationRepository.count());
		foodInformationRepository.deleteById(4);
		System.out.println(foodInformationRepository.count());
		foodInformationRepository.findById(4).ifPresent(System.out::println);
	}
}
