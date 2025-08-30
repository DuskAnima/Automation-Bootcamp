package com.example.rest.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.rest.model.Book;

// Cuando extendemos de CrudRepository, se le debe establecer la entidad y el tipo de la primary key = <Book, Long>
public interface IBookRepository extends CrudRepository<Book, Long>{
  
}

// Crud repository posee un ORM básico integrado, ejemplos:
// Select * from Books; -> findAll()
// Select * from Books where id = ? -> findById(id)