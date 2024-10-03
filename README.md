![logo](/img.png)

###### *Simplify the world of books*

[booklify.org](https://www.booklify.org/)

# USER SERVICE

CRUD APIs for the management of the users.

## APIs

### Users `/api/users`
- **GET** `/get/{email}`
- **GET** `/get-all`
- **POST** `/add`
- **PUT** `/update/{id}`
- **DELETE** `/delete/{id}`
- **POST** `/login`

## Dependencies

> Spring Boot 3.2.0

> JDK corretto-21

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
