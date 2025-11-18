package com.anthony.food.controller;

import com.anthony.food.entity.FoodInformation;
import com.anthony.food.repository.FoodInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodInformationRepository repository;

    // Get all foods
    @GetMapping
    public List<FoodInformation> getAllFoods() {
        return (List<FoodInformation>) repository.findAll();
    }

    // Get food by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodInformation> getFoodById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add new food
    @PostMapping
    public ResponseEntity<?> addFood(@RequestBody FoodInformation food) {
        Map<String, String> errors = validateFood(food);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }
        FoodInformation savedFood = repository.save(food);
        return ResponseEntity.ok(savedFood);
    }

    // Update food
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @RequestBody FoodInformation updatedFood) {
        Map<String, String> errors = validateFood(updatedFood);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        return repository.findById(id).map(food -> {
            food.setFoodName(updatedFood.getFoodName());
            food.setFoodKcal(updatedFood.getFoodKcal());
            food.setFat(updatedFood.getFat());
            food.setSaturatedFat(updatedFood.getSaturatedFat());
            food.setCarbs(updatedFood.getCarbs());
            food.setSugars(updatedFood.getSugars());
            food.setFibre(updatedFood.getFibre());
            food.setProtein(updatedFood.getProtein());
            food.setSalt(updatedFood.getSalt());
            repository.save(food);
            return ResponseEntity.ok(food);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Helper method to validate food
    private Map<String, String> validateFood(FoodInformation food) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (food.getFoodName() == null || food.getFoodName().isBlank()) {
            errors.put("foodName", "Food name is required");
        }
        if (food.getFoodKcal() == null || food.getFoodKcal().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("foodKcal", "Calories must be zero or positive");
        }
        if (food.getFat() == null || food.getFat().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("fat", "Total fat must be zero or positive");
        }
        if (food.getSaturatedFat() == null || food.getSaturatedFat().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("saturatedFat", "Saturated fat must be zero or positive");
        }
        if (food.getCarbs() == null || food.getCarbs().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("carbs", "Carbohydrates must be zero or positive");
        }
        if (food.getSugars() == null || food.getSugars().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("sugars", "Sugars must be zero or positive");
        }
        if (food.getFibre() == null || food.getFibre().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("fibre", "Fibre must be zero or positive");
        }
        if (food.getProtein() == null || food.getProtein().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("protein", "Protein must be zero or positive");
        }
        if (food.getSalt() == null || food.getSalt().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("salt", "Salt must be zero or positive");
        }

        return errors;
    }


    // Delete food
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Search by name
    @GetMapping("/search")
    public List<FoodInformation> searchByName(@RequestParam String name) {
        return repository.findByFoodName(name);
    }

    // Optional: search by calories
    @GetMapping("/search/kcal")
    public List<FoodInformation> searchByKcal(@RequestParam BigDecimal kcal) {
        return repository.findByFoodKcal(kcal);
    }

    // Optional: search by fibre
    @GetMapping("/search/fibre")
    public List<FoodInformation> searchByFibre(@RequestParam BigDecimal fibre) {
        return repository.findByFibre(fibre);
    }
}
