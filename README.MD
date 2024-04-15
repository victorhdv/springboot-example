# Spring Boot 3 RESTful API Project
This repository contains a RESTful API built using the latest versions of Spring Boot 3, Spring Framework 6, and Java 17. It follows the principles of the Richardson Maturity Model to design and implement the API.

The main purpose is to practice implementing a simple CRUD for products.
## Technologies Used

- **Spring Boot 3**: A powerful framework for building stand-alone, production-grade Spring-based applications.
- **Spring Framework 6**: Provides comprehensive infrastructure support for developing Java applications.
- **Java 17**: The latest LTS version of Java, offering new features and enhancements.
- **RESTful API**: Implementing the principles of Representational State Transfer for scalable web services.
- **Richardson Maturity Model**: A model for assessing the maturity of an API's design.

## Project Structure

- `src/`: Contains the source code for the Spring Boot application.
- `pom.xml`: Maven project configuration file.
- `README.md`: This file, providing an overview of the project.

## Getting Started

To get started with the project, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/victorhdv/springboot-example.git
   
2. Use your IDE of choice to run the application

## API Endpoints
The API endpoints will be available at http://localhost:8080.

- GET /api/produtos: Retrieves a list of products.
- GET /api/produtos/{id}: Retrieves a specific product by ID.
- POST /api/produtos: Creates a new product.
- PUT /api/produtos/{id}: Updates an existing product.
- DELETE /api/produtos/{id}: Deletes a product.

## License

This project is licensed under the [MIT License](https://github.com/victorhdv/springboot-example/blob/master/LICENSE).
