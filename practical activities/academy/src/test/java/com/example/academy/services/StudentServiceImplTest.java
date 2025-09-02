package com.example.academy.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.academy.entities.Student;
import com.example.academy.repositories.IStudentRepository;

public class StudentServiceImplTest {
  private StudentServiceImpl studentService;

  @Mock
  private IStudentRepository studentRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    studentService = new StudentServiceImpl(studentRepository);
  }

  @Test
  public void shouldSaveStudent() {
    Student student = new Student("Ryan", "Gosling", "ryan@java.com");
    // any() significa que acepta cualquier instancia de Student como argumento
    // thenReturn() significa que cuando se llame a save(), el mock debe retornar la variable student. 
    when(studentRepository.save(any(Student.class))).thenReturn(student);
    // Se llama al método que queremos probar
    studentService.saveStudent(student);
    // Verifica si la llamada fue hecha con los datos correctos.
    verify(studentRepository).save(student);
  }

  @Test
  public void shouldFindStudentById() {
    Student student = new Student("Ryan", "Gosling", "ryan@java.com");
    student.setId(1L); // Se settea el id porque no estamos usando la DB para que se genere una PK (1L porque es "Long")
    when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
    studentService.getStudentById(1L);
    
    verify(studentRepository).findById(1L);
  }

  @Test
  public void shouldListAllStudents() {
    Student student = new Student("Ryan", "Gosling", "ryan@java.com");
    Student student2 = new Student("James", "Gosling", "james@java.com");

    when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student2));
    studentService.listAllStudents();
    verify(studentRepository).findAll();
  }

}
