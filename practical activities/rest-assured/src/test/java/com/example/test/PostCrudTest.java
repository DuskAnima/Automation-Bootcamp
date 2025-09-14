package com.example.test;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.example.client.PostsClient;
import com.example.model.Post;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

// Indicar el orden de ejecusión segun @Order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostCrudTest {
  
  private static PostsClient client;
  private static int createdId;
  private static final int USER_ID = 888;

  @BeforeAll
  static void setUp() {
    client = new PostsClient();
  }

  @Test
  @DisplayName("GET /posts - lista de todos los posts")
  void listPosts_ok() {
    Response response = client.listPosts();
    JsonPath json = response.jsonPath();
    List<Map<String, Object>> posts = json.getList("$");
    Assertions.assertThat(posts).as("La lista de posts no debe ser vacia").isNotEmpty();
    Assertions.assertThat(response.getHeader("Content-Type")).contains("application/json");
  }

  @Test
  @DisplayName("GET /post/{id} - obtiene un post existente")
  void getPost_ok() {
    Response response = client.getPost(1);
    JsonPath json = response.jsonPath();
    Assertions.assertThat(json.getInt("id")).isEqualTo(1);
    Assertions.assertThat(json.getString("title")).isNotBlank();
  }

  @Test
  @DisplayName("POST /post -crear un post")
  void createPost_created() {
    String title = "Aprendiendo tésting con Java";
    String body = "Rest assurance para automatización";
    Post payload = new Post(USER_ID, title, body);

    Response response = client.createPost(payload);
    JsonPath json = response.jsonPath();
    createdId = json.getInt("id");
    Assertions.assertThat(json.getString("title")).isEqualTo(title);
    Assertions.assertThat(json.getString("body")).isEqualTo(body);
    Assertions.assertThat(json.getInt("userId")).isEqualTo(USER_ID);
  }

  @Test
  @DisplayName("PUT /post/{id} -actualizar un post")
  void updatePost_ok() {
    int idToUpdate = 1;

    String title = "Aprendiendo tésting con Java junto a los Javeros";
    String body = "Rest assurance para automatización. Tengo sueño, ngl";
    Post updatePost = new Post(USER_ID, title, body);

    Response response = client.updatedPost(idToUpdate, updatePost);
    JsonPath json = response.jsonPath();

    Assertions.assertThat(json.getString("title")).isEqualTo(title);
    Assertions.assertThat(json.getString("body")).isEqualTo(body);
    Assertions.assertThat(json.getInt("userId")).isEqualTo(USER_ID);
  }

  @Test
  @DisplayName("PUT /post/{id} -elimina un post")
  void deletePost_ok() {
    int idToDelete = (createdId > 0) ? createdId: 1;

    Response response = client.deletePost(idToDelete);

    int status = response.statusCode();

    Assertions.assertThat(response.statusCode()).isIn(200, 204);
  }
}
