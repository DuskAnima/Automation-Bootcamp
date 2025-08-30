package com.example.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
public class Book {
  @Id  // Autoincremental de 1 en 1. Si tienes un algoritmo generador de números únicos, aquí habría que integrarlo 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title is mandatory")  // Aquí nos podemos permitir establecer validaciones
  @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
  private String title;

  @NotBlank(message = "Author is mandatory")
  @Size(min = 1, max = 100)
  private String author;

  @NotBlank(message = "ISBN is mandatory")
  @Size(min = 1, max = 20)
  private String isbn;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getIsbn() {
    return isbn;
  }
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

}