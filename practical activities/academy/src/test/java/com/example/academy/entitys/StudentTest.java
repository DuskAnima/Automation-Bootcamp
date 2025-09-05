package com.example.academy.entitys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.academy.entities.Student;

public class StudentTest {
  private Student student;

  @BeforeEach
  public void setUp() {
    student = new Student("Juan", "Elinga", "juan@email.com");
  }

  @Test
  public void shouldSetName() {
    student.setName("Ricardo");
    assertEquals("Ricardo", student.getName());
  }

  @Test
  public void shouldSetLastname() {
    student.setLastname("Milos");
    assertEquals("Milos", student.getLastname());
  }

  @Test
  public void shouldSetEmail() {
    student.setEmail("rmilos@gachi.com");
    assertEquals("rmilos@gachi.com", student.getEmail());
  }

}
