package com.example.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.BookDTO;
import com.example.rest.model.Book;
import com.example.rest.service.IBookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
  
  private final IBookService bookService;

  public BookController(IBookService bookService) { this.bookService = bookService; }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
    Book createBook = this.bookService.create(bookDTO); // Crea entidad
    return new ResponseEntity<>(createBook, HttpStatus.CREATED); // Retorna la entidad crreada y código 201 (codigo http para created)
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() { return ResponseEntity.ok(this.bookService.findAll()); }

  @PostMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
    return ResponseEntity.ok(this.bookService.update(id, bookDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookByID(@PathVariable Long id) {
    return bookService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  /*
   * @Valid 
   * Activa Bean Validation (Jakarta validator, Hibernate validator) sobre el objeto anotado.
   * Valida los campos marcados con anotaciones como @NotNull, @NotBlank, @Size, @Positive, etc., antes de 
   * entrar en la lógica
   */

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    this.bookService.delete(id);
    return ResponseEntity.noContent().build();
  }

}

// Localhost:8080/api/books -> POST, GET
// Localhost:8080/api/books/{id} -> GET, PUT, DELETE