# quarkus-micro-sandbox

Simple Quarkus microservices application. Provides the possibility to save information from Json requests about the book (title, author, genre, etc) to Database. It also generates ISBN number for each book and stores it in the Database. Application support ```M2M``` authentification between microservices.
It contains four modules:

## common
Module with common Json DTO classes

## rest-auth
Module for JWT token generation and validation.  It reads on startup all possible services secret from the file book.key - this secret is important for JWT token validation and generation.
Module ```rest-book``` sends request to ```rest-auth``` to generate JWT token for m2m authentication.
Module ```rest-number``` sends request to ```rest-auth``` for m2m token validation.
When ```rest-book``` needs to generate a new JWT token, it sends its own secret and receives it in response. When ```rest-number``` is needed to validate the token, it sends JWT token and module pars it and validate the secret with possible values from the file.

Example of POST request for token generation (endpoint ```http://localhost:8703/api/token```) :
```
{
  "key": "rKcrK7dVrmbBN3bjpdBgp1gdTcnf4SxCNYRT2agKnog=",
  "service_name": "book_service"
}
```

Example of POST request for token validation (endpoint ```http://localhost:8703/api/token/validation```) :
```
{
    "service_name": "book_service",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJzZXJ2aWNlX2tleSI6InJLY3JLN2RWcm1iQk4zYmpwZEJncDFnZFRjbmY0U3hDTllSVDJhZ0tub2c9Iiwic2VydmljZV9uYW1lIjoiYm9va19zZXJ2aWNlIiwiaXNzIjoiaHR0cHM6Ly9leGFtcGxlLmNvbS9pc3N1ZXIiLCJpYXQiOjE2ODIyNTkwMTF9.l37mq2PszUkV1JgQvb7rUsW0w69yJ3WuxWJOVr3aAPo"
}
```

## rest-number
Generates ISBN numbers by request. Allows requests with Authentification header. Response will be sent only after JWT token validation
To receive ISBN numbers, perform GET requests to endpoint ```http://localhost:8701/api/numbers``` (JWT token is needed)

## rest-book
Allow to create book record in the Postgres database with all input information and ISBN that generates by  ```rest-number``` service. Each request to ```rest-number``` should be signed by unique JWT token
Example of POST request for book record creation to endpoint ```http://localhost:8702/api/books```:
```
{
    "author": "Antonio Goncalves",
    "genre": "IT",
    "title": "Building Microservices with Quarkus",
    "year_of_publication": 2021
}
```
Example of response after book creation:
```
{
    "author": "Antonio Goncalves",
    "generation_date": "2023-04-24T15:01:55.425303Z",
    "genre": "IT",
    "isbn_13": "13-78637689",
    "title": "Building Microservices with Quarkus",
    "year_of_publication": 2021
}
```
An application can be run in three modes - development, testing, and production
## Running the application in testing mode
You can execute Quarkus tests for several Panache entities.
Navigate to the root folder and perform:
```
mvn test
```
> **_NOTE:_**  Quarkus will use H2 in-memory database for create all needful tables 

## Running the application in dev mode

You can run your application in dev mode which enables live coding using.
Navigate to ```root``` application folder and perform:
```
mvn clean install
```
Perform the next maven command for each application service:
```
mvn quarkus:dev
```
> **_NOTE:_**  Quarkus will create database container with Postgresql DB and a container for the application

## Create a native executable and start in production mode
You can create a native executable using.
Navigate to the root application folder and perform:
```
mvn clean install
```
Perform next command for each service:
```
mvn package -Dquarkus.container-image.build=true -Dquarkus.package.type=native -Dquarkus.native.container-build=true  -Dquarkus.container-image.tag=native -DskipTests
```
Update values of image parameters in ```docker_compose.yml``` file with generated names from previous step

Start docker-compose, execute command in ```root``` application folder:
```
docker-compose up -d
```
