package com.anthony.food.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class FoodInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer foodId;
    private String foodName;
    private BigDecimal foodKcal;
    private BigDecimal foodFibre;

    public FoodInformation() {
    }

    public FoodInformation( String foodName, BigDecimal foodKcal, BigDecimal foodFibre) {

        this.foodName = foodName;
        this.foodKcal = foodKcal;
        this.foodFibre = foodFibre;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getFoodKcal() {
        return foodKcal;
    }

    public void setFoodKcal(BigDecimal foodKcal) {
        this.foodKcal = foodKcal;
    }

    public BigDecimal getFoodFibre() {
        return foodFibre;
    }

    public void setFoodFibre(BigDecimal foodFibre) {
        this.foodFibre = foodFibre;
    }

    @Override
    public String toString() {
        return "FoodInformation{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodKcal=" + foodKcal +
                ", foodFibre=" + foodFibre +
                '}';
    }
}
