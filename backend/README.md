# Simple Calendar Service Backend

## Prerequisites
You need the following installed and available in your $PATH:
- Java 11
- Apache Maven 3.8.7
- MySQL 8.0

## Setup MySQL Database
- Start MySQL server
- Update properties in `src/main/resources/application.properties`.
```properties
spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DB_NAME>?useUnicode=yes&characterEncoding=utf-8&createDatabaseIfNotExist=true
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
```
>Note: A database with the selected name will be created automatically.

## Building the application
Build with Maven:
```sh
mvn clean install
```

## Running the application
Use the Spring Boot Maven Plugin:
```sh
mvn spring-boot:run
```

You'll then be able to access the application at http://localhost:8080.
