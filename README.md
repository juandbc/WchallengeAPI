# WchallengeAPI
Simple API REST based on Java, Spring Boot, Spring MongoDB and  Spring Fox (Swagger API docs)

This project use the following Spring projects:
* [Spring Data MongoDB]()
* [Spring Framework (Spring MVC - Spring WebFlux)](https://spring.io/projects/spring-framework)
* [Embedded Mongo](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo)

REST API Endpoints

All inputs and outputs use JSON format.

To open Swagger (interactive) API documentation, navigate your browser to [YOUR-URL]/swagger-ui-custom.html

```
/users
  GET / - List of users
  GET /{id} - View user
  GET /{id}/albums - View user albums
  GET /albums/{id} - List of users by a specific permission over an album
  GET /{id}/photos - View user photos
  GET /{id}/comments - View user comments
  POST / - Add user
  PUT /{id} - Update user

/posts
  GET / - List of posts
  GET /{id} - View post
  POST / - Add post
  PUT /{id} - Update post

/comments
  GET / - List of comments
  GET /{id} - View comment
  POST / - Add comment
  PUT /{id} - Update comment


/photos
  GET / - List of photos
  GET /{id} - View photo
  POST / - Add photo
  PUT /{id} - Update photo

/albums
  GET /- List of albums
  GET /{id} - View album
  GET /{id}/permissions - List of album permissions
  POST / - Add album
  PUT /{id} - Update album
  GET /permissions - List of albums permissions
  POST /permissions - Add album permissions
  PUT /permissions/{id} - Updates album permissions
```
