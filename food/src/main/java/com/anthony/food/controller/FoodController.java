package com.anthony.food.controller;

import com.anthony.food.entity.FoodInformation;
import com.anthony.food.repository.FoodInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodInformationRepository repository;

    @GetMapping
    public List<FoodInformation> getAllFoods() {
        return (List<FoodInformation>) repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodInformation> getFoodById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FoodInformation addFood(@RequestBody FoodInformation food) {
        return repository.save(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodInformation> updateFood(@PathVariable Integer id, @RequestBody FoodInformation updatedFood) {
        return repository.findById(id).map(food -> {
            food.setFoodName(updatedFood.getFoodName());
            food.setFoodKcal(updatedFood.getFoodKcal());
            food.setFoodFibre(updatedFood.getFoodFibre());
            repository.save(food);
            return ResponseEntity.ok(food);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<FoodInformation> searchByName(@RequestParam String name) {
        return repository.findByFoodName(name);
    }
}