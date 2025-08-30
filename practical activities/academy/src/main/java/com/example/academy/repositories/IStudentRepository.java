package com.example.academy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.academy.entities.Student;

public interface IStudentRepository extends CrudRepository<Student, Long> {
  
}
