# Spring Authentication API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/spring%20security-%6DB33F.svg?style=for-the-badge&logo=spring%20security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![H2](https://img.shields.io/badge/h2-%23316192.svg?style=for-the-badge)

This project is an API built using **Java, Java Spring, H2 as the database, and Spring Security and JWT for 
authentication control.**

This project was built for learning purposes.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/EduardoOrthmann/spring-authentication.git
```

2. Install dependencies with Maven

3. Configure the database connection in the application.properties file

## Configuration

The following properties can be configured in the application.properties file:

```markdown
spring.datasource.url=jdbc:h2:mem:your_database_name
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

jwt.secret=${JWT_SECRET:yoursecret}
```

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
GET /products - Retrieve a list of all products.

GET /products/{id} - Retrieve a product by id.

POST /products - Register a new product (ADMIN access required).

PUT /products/{id} - Update a product (ADMIN access required).

DELETE /products/{id} - Delete a product (ADMIN access required).

POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Standard user role for logged-in users.
ADMIN -> Admin role for managing partners (registering new partners).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [H2](https://www.h2database.com/html/main.html) as the database. 
The database can be accessed at http://localhost:8080/h2-console. The default credentials are:

```markdown
username: sa
password: <empty>
```
