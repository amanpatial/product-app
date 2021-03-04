## Spring Boot Project
Spring Boot (version 2.0.0) maven based application with REST CRUD API end points connecting through PostgreSQL.

### Tools and Technologies used
```
Spring Boot - 2.0.0.RELEASE
JDK - 1.8
Spring Framework - 5.0.4 RELEASE
Hibernate - 5.0.1.Final
JPA - 2.0.0.RELEASE
Maven - 3.6.3
IDE - Eclipse or Intellij or STS or Visual code studio
PostgreSQL - 42.2.5
pgAdmin4 
git - 2.25.0
```

### Pre-requisites and build steps
```
Make sure that your operating system had JDK 1.8, Maven 3.x and PostgreSQL database installed
Set up Postgresql database engine
Set up pgAdmin database client
create database product

Set up IDE such as Eclipse or Intellij or STS or Visual code studio

java -version
mvn -v
git --version

clone the code repository
cd /go/to/project/code/root/directory
mvn clean install
mvn clean install -DskipTests -Dpmd.skip=true  (optional to avoid the test package building)
java -jar ./target/product-service-0.0.1-SNAPSHOT.jar
java -jar ./target/product-service-0.0.1-SNAPSHOT.jar --spring.config.location=./src/main/resources/application.properties
```

## APIs

### Create a product resource
```
POST /api/v1/products
Accept: application/json
Content-Type: application/json

{
  "name": "shirt",
  "price": 14,
  "description": "shirt"
}

RESPONSE: HTTP 201 (Created)
Location: http://localhost:8080/api/v1/products
```

### Retrieve a list of products
```
GET /api/v1/products

RESPONSE: HTTP 200
Content: list of products in json format
[
    {
        "id": 1,
        "name": "shirt",
        "price": 3,
        "description": "shirt",
        "views": 6
    },
    {
        "id": 2,
        "name": "Cap",
        "price": 14,
        "description": "Cap with fur",
        "views": 1
    },
    {
        "id": 3,
        "name": "Jeans",
        "price": 14,
        "description": "Jeans with shades",
        "views": 0
    }
]    
```

### Retrieve a product
```
GET /api/v1/products/1

RESPONSE: HTTP 200
http://localhost:8080/api/v1/products/1
Content: product information in json format

{
    "id": 1,
    "name": "shirt",
    "price": 3,
    "description": "shirt",
    "views": 6
}
```

### Update a product
```
PUT /api/v1/products/1
Accept: application/json
Content-Type: application/json

{
  "name": "Shirt with round neck",
  "price": 7,
  "description": "Shirt with round neck"
}

RESPONSE: HTTP 200
Location: http://localhost:8080/api/v1/products/1
```

### Retrieve a product by name
```
GET /api/v1/products/filter?name=???

RESPONSE: HTTP 200
http://localhost:8080/api/v1/products/filter?name=shirt
Content: product information in json format

[
    {
        "id": 1,
        "name": "shirt",
        "price": 3,
        "description": "shirt",
        "views": 6
    }
]
```

### Retrieve a product by its view count
```
GET /api/v1/products/popular/1

RESPONSE: HTTP 200
http://localhost:8080/api/v1/products/popular/1
Content: most viewed product information in json format

[
    {
        "id": 1,
        "name": "shirt",
        "price": 3,
        "description": "shirt",
        "views": 6
    }
]
```

### Delete a product resource
```
DELETE /api/v1/products/1
Accept: application/json
Content-Type: application/json

RESPONSE: HTTP 200
Location: http://localhost:8080/api/v1/products/1
```