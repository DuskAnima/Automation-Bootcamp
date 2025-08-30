package com.example.academy.services;

import java.util.List;
import java.util.Optional;

import com.example.academy.entities.Student;

public interface IStudentService {
  public List<Student> listAllStudents();
  public Student saveStudent(Student student);
  public Optional<Student> getStudentById(Long id);
  public Student updateStudent(Student student);
  public void deleteStudentById(Long id);
}