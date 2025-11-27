-- Drop table if it already exists
DROP TABLE IF EXISTS food_information;

-- Create table based on FoodInformation entity
CREATE TABLE food_information (
    food_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    food_name VARCHAR(255) NOT NULL,
    food_kcal DECIMAL(19,2) NOT NULL,
    fat DECIMAL(19,2) NOT NULL,
    saturated_fat DECIMAL(19,2) NOT NULL,
    carbs DECIMAL(19,2) NOT NULL,
    sugars DECIMAL(19,2) NOT NULL,
    fibre DECIMAL(19,2) NOT NULL,
    protein DECIMAL(19,2) NOT NULL,
    salt DECIMAL(19,2) NOT NULL
);

-- Insert seed data
INSERT INTO food_information 
(food_name, food_kcal, fat, saturated_fat, carbs, sugars, fibre, protein, salt)
VALUES
('Avocado',                    160, 14.7, 2.1, 8.5, 0.7, 6.7, 2.0, 0.01),
('Kidney Beans',               127, 0.5,  0.1, 22.8, 0.3, 6.4, 8.7, 0.01),
('Tomatoes',                   18,  0.2,  0.0, 3.9,  2.6, 1.2, 0.9, 0.01),
('Lentils (boiled)',           116, 0.4, 0.1, 20.1, 1.8, 7.9, 9.0, 0.01),
('Black Beans (boiled)',       132, 0.5, 0.1, 23.7, 0.3, 8.7, 8.9, 0.01),
('Chickpeas (boiled)',         164, 2.6, 0.3, 27.4, 4.8, 7.6, 8.9, 0.01),
('Oats',                       389, 6.9, 1.2, 66.3, 1.2, 10.6, 16.9, 0.01),
('Chia Seeds',                 486, 30.7, 3.3, 42.1, 0.0, 34.4, 16.5, 0.01),
('Chicken Breast (cooked)',    165, 3.6, 1.0, 0,    0,   0,   31.0, 0.07),
('Eggs (whole)',               155, 11.0, 3.3, 1.1, 1.1, 0,   13.0, 0.12),
('Greek Yogurt (non-fat)',     59,  0.4, 0.1, 3.6,  3.2, 0,   10.0, 0.05),
('Tofu (firm)',                144, 8.0, 1.0, 3.9,  0.7, 2.3, 17.0, 0.01),
('Salmon',                     208, 13.0, 3.1, 0,    0,   0,   20.0, 0.06),
('Almonds',                    579, 49.9, 3.8, 21.6, 4.4, 12.5, 21.1, 0.01),
('Quinoa (cooked)',            120, 1.9, 0.2, 21.3, 0.9, 2.8,  4.4,  0.01),
('Edamame (boiled)',           121, 5.2, 0.7, 8.9,  2.2, 5.2,  11.9, 0.01),
('Peas (boiled)',              81,  0.4, 0.1, 14.5, 5.7, 5.1,  5.4,  0.01),
('Flaxseeds',                  534, 42.0, 3.7, 28.9, 1.5, 27.3, 18.3, 0.01);
