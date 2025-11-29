# imc-rest-springboot
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

RESTful API built with Java Spring Boot for user management and BMI calculation using PostgreSQL and Docker.

This project was developed for educational purposes, focusing on applying software development best practices, containerization,
and clean architecture. It provides a full CRUD for user management and a specific logic for calculating Body Mass Index (IMC/BMI)
with  classification.

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Error Handling](#error-handling)
- [API](#api)


## Background

The main motivation for this repository is to practice and demonstrate proficiency in backend development using the Spring Ecosystem. It ensures a consistent development environment through Docker containerization for the database, avoiding local installation issues.

It addresses the need for a simple, reliable API to manage user data and perform health-related calculations (BMI) following the REST architectural style.

## Install

To run this project, you need the following installed:
* Java JDK 17+
* Maven
* Docker & Docker Compose



### Database Setup

The project uses a `docker-compose.yml` file to start the PostgreSQL database. The host mapped port is **5433**.

```bash
# In the project root, run:
 docker-compose up -d

```
>   **Note:** The credentials configured in the container are:
>   * **User:** `imc_user`
>   * **Password:** `imc_pass`
>   * **Database:** `crudusers`
>   * **Port:** `5433`

## Usage

With the database running, you can start the application via Maven wrapper:
```Bash
./mvnw spring-boot:run
```
The application will be accessible at `http://localhost:8080`.

### CLI (Curl Examples)

You can interact with the API using `curl`. Here is an example of creating a user:

```bash
curl -X POST http://localhost:8080/api/users \
   -H "Content-Type: application/json" \
   -d '{"name": "John Doe", "age": 30, "weight": 80.0, "height": 1.80}'
```

## Project Structure

The project follows the Spring Boot layered architecture:

  * `Controller`: Entry layer (REST endpoints).
  * `Service`: Business logic (IMC calculation, validations).
  * `Repo`: Database communication interfaces.
  * `Models`: Database entities.
  * `DTO`: Data Transfer Objects (e.g., `ImcRequest`).
  * `Exception`: Custom error handling classes.

## Error Handling

The API features a **Global Exception Handler** (`RestControllerAdvice`).

  * **ResourceNotFoundException:** Returns HTTP 404 when a user is not found.
  * **Generic Exception:** Returns HTTP 500 for unexpected server errors.

## API

For detailed documentation and interactive testing, this project uses Swagger/OpenAPI.

  * **Swagger UI:** `http://localhost:8080/swagger-ui.html`

### Endpoints Overview

#### Users (`/api/users`)

| Method | Route | Description |
| :--- | :--- | :--- |
| `GET` | `/api/users/all` | Returns a list of all users. |
| `GET` | `/api/users/{id}` | Retrieves a specific user by ID. |
| `POST` | `/api/users` | Creates a new user. |
| `PUT` | `/api/users/{id}` | Updates an existing user's data. |
| `DELETE` | `/api/users/{id}` | Removes a user from the database. |

#### IMC Calculation (`/api/users/calculateImc`)

| Method | Route | Description |
| :--- | :--- | :--- |
| `POST` | `/api/users/calculateImc` | Calculates the IMC based on JSON body (weight/height). |
