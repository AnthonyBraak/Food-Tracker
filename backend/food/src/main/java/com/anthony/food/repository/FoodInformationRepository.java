package com.anthony.food.repository;

import com.anthony.food.entity.FoodInformation;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FoodInformationRepository extends CrudRepository<FoodInformation, Long> {

    // Search by name
    List<FoodInformation> findByFoodName(String name);

    // Search by calories
    List<FoodInformation> findByFoodKcal(BigDecimal kcal);

    // Search by macronutrients
    List<FoodInformation> findByFat(BigDecimal fat);
    List<FoodInformation> findBySaturatedFat(BigDecimal saturatedFat);
    List<FoodInformation> findByCarbs(BigDecimal carbs);
    List<FoodInformation> findBySugars(BigDecimal sugars);
    List<FoodInformation> findByFibre(BigDecimal fibre);
    List<FoodInformation> findByProtein(BigDecimal protein);
    List<FoodInformation> findBySalt(BigDecimal salt);
}
