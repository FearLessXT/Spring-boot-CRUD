# Spring-boot-CRUD
Spring boot CRUD Operation
  # Spring Boot CRUD App
  A simple CRUD application built with Spring Boot.

  ## Technologies Used
  - Spring Boot 2.6.7
  - Spring Data JPA
  - H2 Database
  - Maven
  - Lombok

  ## Features
  - Create, Read, Update and Delete user details
  - Store data in an in-memory H2 database

  ## How to Run
  1. Install Maven on your system
  2. Clone this repository
  3. Go to the project directory and run `mvn spring-boot:run`
  4. The application will start on port `8080`
  5. You can access the application at `http://localhost:8080`

  ## Endpoints
  | Method | Endpoint | Description |
  |:--|:--|:--|
  | `GET` | `/api/v1/product/get` | Fetch all products |
  | `POST` | `/api/v1/product/add` | Create product | 
  | `PUT` | `/api/v1/product/update{id}` | Update product by ID |
  | `DELETE` | `/api/v1/product/delete/{id}` | Delete product by ID |
  ## Project Structure
  - `src/main/java/com/example/demo/product` - Contains Java files
       - `Product.java` - The Entity Class
       -  `productController.java` - The Controller Class
       - `DemoApplication.java` - Spring Boot starter class
  - `src/main/resources` - Contains application configuration files
  - `pom.xml` - Contains Maven dependencies and plugins
