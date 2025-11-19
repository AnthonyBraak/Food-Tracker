# Food Information API

## Overview

This is a Spring Boot application that provides a RESTful API for managing food nutritional information. It allows users to create, read, update, and delete food items, as well as search by name or nutritional values.

The application uses JPA for persistence with a MySQL database and includes validation to ensure all nutritional values are positive.

![Frontend with React](https://github.com/AnthonyBraak/Food-Tracker/blob/main/images/frontend.png?raw=true)

## Technologies

### Backend

- Java 25

- Spring Boot 4.0

- Spring Data JPA

- Hibernate

- MySQL

- Maven

### Frontend

- React

- TypeScript

- Vite

## Features

- CRUD Operations: Create, read, update, and delete food items.

- Validation: Ensures required fields are provided and numerical values are positive.

- Error Handling: Returns meaningful error messages for validation errors and when attempting to update or delete non-existent items.

- Search: Find foods by name.

## API Endpoints

### Get All Foods

`GET /api/foods`

Returns a list of all food items.

Tested with POSTMAN:

![CRUD get with POSTMAN](https://github.com/AnthonyBraak/Food-Tracker/blob/main/images/CRUD-get.png?raw=true)

### Get Food by ID

`GET /api/foods/{id}`

Returns the food item with the specified ID.

Visible in browser:

![API in browser](https://github.com/AnthonyBraak/Food-Tracker/blob/main/images/api-json.png?raw=true)

### Add New Food

`POST /api/foods`

Request Body Example:

```
{
"foodName": "Avocado",
"foodKcal": 322,
"fat": 13,
"saturatedFat": 2.1,
"carbs": 17,
"sugars": 0.7,
"fibre": 6.7,
"protein": 4,
"salt": 0
}
```

Returns the created food item.
Validation errors are returned with detailed messages.

Tested with POSTMAN:

![CRUD post with POSTMAN](https://github.com/AnthonyBraak/Food-Tracker/blob/main/images/CRUD-post.png?raw=true)

Invalid post with POSTMAN:

![Invalid CRUD post with POSTMAN](https://github.com/AnthonyBraak/Food-Tracker/blob/main/images/CRUD-invalid.png?raw=true)

### Update Food

`PUT /api/foods/{id}`

Request Body: Same format as Add New Food.
Returns the updated food item or a 404 error if the ID does not exist.

### Delete Food

`DELETE /api/foods/{id}`

Deletes the food item with the specified ID. Returns 404 if the ID does not exist.

### Search Foods by Name

`GET /api/foods/search?name={name}`

Returns a list of food items matching the specified name.

## Data Model

FoodInformation Entity Fields:

`foodId`: Long (auto-generated)

`foodName`: String

`foodKcal`: BigDecimal

`fat`: BigDecimal

`saturatedFat`: BigDecimal

`carbs`: BigDecimal

`sugars`: BigDecimal

`fibre`: BigDecimal

`protein`: BigDecimal

`salt`: BigDecimal

All numerical fields are validated to be zero or positive.

## Setup and Run

Clone the repository:

```
git clone <repository-url>
cd <repository-folder>
```

Configure MySQL:
Update `src/main/resources/application.properties` with your MySQL connection details:

```
spring.datasource.url=jdbc:mysql://localhost:3306/food_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Create the database in MySQL if it does not exist:

```
CREATE DATABASE food_db;
```

Build the application:

```
mvn clean install
```

Run the application:

```
mvn spring-boot:run
```

Access the API:
The API is available at http://localhost:8080/api/foods.

## Notes

- The application requires a running MySQL database.

- Input validation ensures all required fields are provided and numerical values are non-negative. Errors are returned as JSON with descriptive messages.

- The application automatically updates the database schema using Hibernate.
