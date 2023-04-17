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

## Testing

To run the unit tests for this project, use the following command:

```
mvn test
```

## Contributing

If you find a bug or would like to contribute to this project, please open a new issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.
