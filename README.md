          _____ _   _ _____   ____  _   _ _____ _____ 
         / ____| \ | |  __ \ / __ \| \ | |_   _|_   _|
        | |    |  \| | |__) | |  | |  \| | | |   | |  
        | |    | . ` |  ___/| |  | | . ` | | |   | |  
        | |____| |\  | |    | |__| | |\  |_| |_ _| |_ 
         \_____|_| \_|_|     \____/|_| \_|_____|_____|


# API-Spring

This is a sample project demonstrating how to build a RESTful API using the Spring Framework.

## Prerequisites

Before you can build and run this project, you will need to have the following software installed on your machine:

- Java 8 or later
- Maven
- PostgreSQL

## Getting Started

To get started, clone this repository to your local machine and navigate to the project directory:

```
git clone https://github.com/eduardo-borges-goncalves/api-spring.git
cd api-spring
```

Next, create a new PostgreSQL database and update the database connection properties in the `application.properties` file located in the `src/main/resources` directory.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
spring.datasource.username=myusername
spring.datasource.password=mypassword
```

Once you have configured the database connection, you can build and run the application using Maven:

```
mvn spring-boot:run
```

## API Documentation

The API provides the following endpoints:

- `GET /api/products`: Returns a list of all products.
- `GET /api/products/{id}`: Returns a single product by ID.
- `POST /api/products`: Creates a new product.
- `PUT /api/products/{id}`: Updates an existing product by ID.
- `DELETE /api/products/{id}`: Deletes a product by ID.

The `api-spring` project is a sample RESTful API built using the Spring Framework. It is designed to demonstrate best practices for building scalable and maintainable web applications.

### Architecture

The project follows a standard layered architecture with the following layers:

- **Presentation Layer**: This layer is responsible for handling HTTP requests and responses. It uses Spring Web MVC to implement the REST API endpoints.
- **Service Layer**: This layer contains the business logic of the application. It is responsible for validating input, processing data, and communicating with the data access layer.
- **Data Access Layer**: This layer is responsible for accessing and manipulating data. It uses Spring Data JPA to interact with the database.
- **Database Layer**: This layer is the actual database, which in this case is PostgreSQL.

The project also follows the Repository pattern, which abstracts the data access layer from the service layer, and the Dependency Injection pattern, which makes the code more modular and testable.

### Java Decisions

The project is written in Java and uses several open-source libraries and frameworks, including:

- **Spring Boot**: A popular framework for building web applications in Java.
- **Spring Data JPA**: A library that provides a high-level, object-oriented interface for interacting with databases.
- **Hibernate**: A powerful Object-Relational Mapping (ORM) library that makes it easy to map Java objects to database tables.
- **PostgreSQL**: A popular open-source relational database management system.

The project also uses several Java features and best practices, including:

- **Java 8 Streams**: A powerful feature that allows for functional-style programming in Java.
- **JUnit**: A popular testing framework for Java.
- **Lombok**: A library that reduces boilerplate code by generating getters, setters, and constructors at compile-time.

Overall, the `api-spring` project is a well-designed, maintainable, and scalable RESTful API built using modern Java technologies and best practices.

## Testing

To run the unit tests for this project, use the following command:

```
mvn test
```

## Contributing

If you find a bug or would like to contribute to this project, please open a new issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.
