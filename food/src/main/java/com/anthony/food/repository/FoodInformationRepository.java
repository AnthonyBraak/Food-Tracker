package com.anthony.food.repository;

import com.anthony.food.entity.FoodInformation;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FoodInformationRepository extends CrudRepository<FoodInformation, Integer> {
    List<FoodInformation> findByFoodName(String name);
    List<FoodInformation> findByFoodKcal(BigDecimal kcal);
    List<FoodInformation> findByFoodFibre(BigDecimal fibre);
}
