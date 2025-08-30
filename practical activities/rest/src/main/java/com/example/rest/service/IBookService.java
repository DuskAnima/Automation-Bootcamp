package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import com.example.rest.dto.BookDTO;
import com.example.rest.model.Book;

public interface IBookService {
  List<Book> findAll();
  Optional<Book> findById(Long id);
  Book create(BookDTO bookDTO);
  Book update(Long id, BookDTO bookDTO);
  void delete(Long id);
}
