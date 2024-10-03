![logo](/img.png)

###### *Simplify the world of books*

[booklify.org](https://www.booklify.org/)

# USER SERVICE

CRUD APIs for the management of the users.

> Spring Boot 3.2.0

> JDK corretto-21

## Docker

For build the image: `docker buildx build -t booklify-user-service:latest .`

## APIs

[Swagger](http://booklify-user-service/swagger-ui/index.hmtl)


### Users `/api/users`
- **GET** `/get/{email}`
- **GET** `/get-all`
- **POST** `/add`
- **PUT** `/update/{id}`
- **DELETE** `/delete/{id}`
- **POST** `/login`

## Dependencies
- starter-web
- starter-test
- starter-actuator
- starter-data-jpa
- starter-hateoas
- starter-validation
- postgresql
- h2
- springdoc-openapi-starter-webmvc-ui
- lombok
- commons-collections4
- mapstruct
- mapstruct-processor
- spring-cloud-starter-netflix-eureka-client
- spring-boot-starter-security
- jjwt
- springdoc-openapi-security
