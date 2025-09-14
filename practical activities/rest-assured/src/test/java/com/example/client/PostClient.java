package com.example.client;

import static io.restassured.RestAssured.given;

import com.example.config.Specs;
import com.example.model.Post;

import io.restassured.response.Response;

// Clase que agrupa métodos para llamar a los endpoints "posts"
public class PostClient {
  // Lista para posts
  public Response listPosts() {
    return given()
          .spec(Specs.request())
          .when()
          .get("/post")
          .then()
          .spec(Specs.ok200())
          .extract().response();
  }
    
  // Obtener el detalle de un post por id
  public Response getPost(int id) {
    return given()
          .spec(Specs.request())
          .when()
          .get("/post/{id}", id)
          .then()
          .spec(Specs.ok200())
          .extract().response();
  }
  // Crear un post
  public Response createPost(Post payload) {
    return given()
          .spec(Specs.request())
          .body(payload)
          .when()
          .post("/post")
          .then()
          .spec(Specs.created201())
          .extract().response();
  } 
  // Actualizar un post
  public Response updatePost(int id, Post payload) {
    return given()
          .spec(Specs.request())
          .body(payload)
          .when()
          .put("/post/{id}", id)
          .then()
          .spec(Specs.ok200())
          .extract().response();
  }
  // Eliminar un post
  public Response deletePost(int id) {
    return given()
          .spec(Specs.request())
          .when()
          .delete("/post{id}", id)
          .then()
          .spec(Specs.ok200())
          .extract().response();
  }
}
