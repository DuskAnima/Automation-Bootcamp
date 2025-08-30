package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rest.dto.BookDTO;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.model.Book;
import com.example.rest.repository.IBookRepository;


@Service
public class BookServiceImpl implements IBookService {

  private final IBookRepository bookRepository;

  public BookServiceImpl(IBookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Book> findAll() {
    return (List<Book>) this.bookRepository.findAll(); // SELECT * FROM Book;
  }

  @Override
  public Optional<Book> findById(Long id) {
    return this.bookRepository.findById(id); // SELECT * FROM Book WHERE id = ?;
  }

  @Override
  @Transactional
  public Book create(BookDTO bookDTO) {
    Book book = new Book();
    book.setTitle(bookDTO.getTitle());
    book.setAuthor(bookDTO.getAuthor());
    book.setIsbn(bookDTO.getIsbn());
    return this.bookRepository.save(book); // INSERT INTO Book (title, author, sibn) values (?, ?, ?)
  }

  @Override
  @Transactional
  public Book update(Long id, BookDTO bookDTO) {
    Book existingBook = this.bookRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("Book not found: " + id));
    existingBook.setTitle(bookDTO.getTitle());
    existingBook.setAuthor(bookDTO.getAuthor());
    existingBook.setIsbn(bookDTO.getIsbn());
    return this.bookRepository.save(existingBook); // UPDATE Book SET title = ?, author = ?, isbn = ? where id = ?;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Book existingBook = this.bookRepository.findById(id)
        .orElseThrow( () -> new ResourceNotFoundException("Book not found: " + id));
    this.bookRepository.delete(existingBook);
  }
  
}
