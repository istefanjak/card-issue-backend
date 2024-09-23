# Card Issue Project

This project consists of three Maven modules: `card-issue-api`, `card-issue-core`, and `card-issue-integration`. Each module has a specific responsibility and utilizes various technologies to achieve its purpose.

## Modules Overview

### 1. `card-issue-api`

**Purpose:**
The `card-issue-api` module is the main entry point for the application. It exposes REST APIs and serves as the primary interface for interacting with the system. It integrates with other modules to provide a comprehensive API service.

**Technologies Used:**
- **Spring Boot:** Provides the core framework for building the RESTful APIs.
- **Springdoc OpenAPI:** Used for generating and displaying API documentation in Swagger UI.
- **H2 Database:** An in-memory database used for development and testing.

**Dependencies:**
- `springdoc-openapi-starter-webmvc-ui`: For OpenAPI documentation.
- `spring-boot-starter-web`: Provides essential components for building web applications.
- `card-issue-core`: Core functionality and business logic.
- `card-issue-integration`: Integrations with external services.

### 2. `card-issue-core`

**Purpose:**
The `card-issue-core` module contains the core business logic and data access components of the application. It defines entities, repositories, and services that interact with the database and perform the main operations of the system.

**Technologies Used:**
- **Spring Boot Starter Data JPA:** Provides support for JPA (Java Persistence API) to manage database interactions.
- **Spring Boot Starter Validation:** Supports validation of entities and input data.

**Dependencies:**
- `spring-boot-starter-data-jpa`: For JPA-based data persistence.
- `spring-boot-starter-validation`: For data validation.

### 3. `card-issue-integration`

**Purpose:**
The `card-issue-integration` module handles interactions with external systems and services. It is responsible for integrating with APIs or other services that the application needs to communicate with.

**Technologies Used:**
- **Spring Boot Starter Web:** Provides support for building web applications, including RESTful web services and HTTP clients.

**Dependencies:**
- `spring-boot-starter-web`: For building web applications and RESTful services.

## Building and Running the Project

To build and run the project, follow these steps:

**From `card-issue-api` directory run:**

   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**Swagger URL**:

[http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)