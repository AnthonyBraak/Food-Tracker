package com.anthony.food.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
public class FoodInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodId;

    @NotBlank(message = "Food name is required")
    private String foodName;

    @NotNull @PositiveOrZero(message = "Calories must be zero or positive")
    private BigDecimal foodKcal;

    @NotNull @PositiveOrZero(message = "Total fat must be zero or positive")
    private BigDecimal fat;

    @NotNull @PositiveOrZero(message = "Saturated fat must be zero or positive")
    private BigDecimal saturatedFat;

    @NotNull @PositiveOrZero(message = "Carbohydrates must be zero or positive")
    private BigDecimal carbs;

    @NotNull @PositiveOrZero(message = "Sugars must be zero or positive")
    private BigDecimal sugars;

    @NotNull @PositiveOrZero(message = "Fibre must be zero or positive")
    private BigDecimal fibre;

    @NotNull @PositiveOrZero(message = "Protein must be zero or positive")
    private BigDecimal protein;

    @NotNull @PositiveOrZero(message = "Salt must be zero or positive")
    private BigDecimal salt;

    public FoodInformation() {}

    public FoodInformation(String foodName, BigDecimal foodKcal, BigDecimal fat, BigDecimal saturatedFat,
                           BigDecimal carbs, BigDecimal sugars, BigDecimal fibre, BigDecimal protein, BigDecimal salt) {
        this.foodName = foodName;
        this.foodKcal = foodKcal;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.carbs = carbs;
        this.sugars = sugars;
        this.fibre = fibre;
        this.protein = protein;
        this.salt = salt;
    }

    // Getters and setters
    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public BigDecimal getFoodKcal() { return foodKcal; }
    public void setFoodKcal(BigDecimal foodKcal) { this.foodKcal = foodKcal; }

    public BigDecimal getFat() { return fat; }
    public void setFat(BigDecimal fat) { this.fat = fat; }

    public BigDecimal getSaturatedFat() { return saturatedFat; }
    public void setSaturatedFat(BigDecimal saturatedFat) { this.saturatedFat = saturatedFat; }

    public BigDecimal getCarbs() { return carbs; }
    public void setCarbs(BigDecimal carbs) { this.carbs = carbs; }

    public BigDecimal getSugars() { return sugars; }
    public void setSugars(BigDecimal sugars) { this.sugars = sugars; }

    public BigDecimal getFibre() { return fibre; }
    public void setFibre(BigDecimal fibre) { this.fibre = fibre; }

    public BigDecimal getProtein() { return protein; }
    public void setProtein(BigDecimal protein) { this.protein = protein; }

    public BigDecimal getSalt() { return salt; }
    public void setSalt(BigDecimal salt) { this.salt = salt; }

    @Override
    public String toString() {
        return "FoodInformation{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodKcal=" + foodKcal +
                ", fat=" + fat +
                ", saturatedFat=" + saturatedFat +
                ", carbs=" + carbs +
                ", sugars=" + sugars +
                ", fibre=" + fibre +
                ", protein=" + protein +
                ", salt=" + salt +
                '}';
    }
}
