package com.example.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookDTO {
  @NotBlank(message = "Title is mandatory")
  @Size(max = 100)
  private String title;

  @NotBlank(message = "Author is mandatory")
  @Size(max = 100)
  private String author;

  @NotBlank(message = "ISBN is mandatory")
  @Size(max = 20)
  private String isbn;

  public String getTitle() { return title; }
  
  public void setTitle(String title) { this.title = title; }

  public String getAuthor() { return author; }

  public void setAuthor(String author) { this.author = author; }

  public String getIsbn() { return isbn; }

  public void setIsbn(String isbn) { this.isbn = isbn; }
}

/*
 * Un DTO (Data Transfer Object) es una caja a medida que se usa para enviar o recibir datos entre 
 * la API y el cliente.
 * No siempre se requiere mandar o recibir toda la información de una entidad porque:
 * 
 * - No siempre necesitas todos los datos (Ahorras peso en la respuesta)
 * - Proteges información sensible (Te aseguras de no mandar campos que no quieres mostrar, por ejemplo:
 * contraseñas, IDs internos, etc.)
 * 
 * En este caso este DTO Maneja casi todos los campos, pero en casos concretos uno crearía un DTO a medida de lo
 * que busca retornar, omitiendo campos, getters y setters especificos. Ahora estamos omitiendo el ID. 
 */
