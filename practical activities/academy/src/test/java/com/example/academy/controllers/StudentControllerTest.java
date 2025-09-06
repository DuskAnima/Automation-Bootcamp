package com.example.academy.controllers;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.academy.entities.Student;
import com.example.academy.services.IStudentService;

@AutoConfigureMockMvc(addFilters = false) // Desactiva filtros de seguridad para las pruebas
@WebMvcTest(controllers = StudentControllerRest.class)
//TODO: Crear un manejador de excepciones globales
public class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc; // Crea un mock para simulación de peticiones http

  @MockBean
  private IStudentService studentService;

  @Test
  public void listShouldRenderViewWithStudents() throws Exception {
    // Simular que el servicio retorna una lista de estudiantes
    when(studentService.listAllStudents()).thenReturn(Arrays.asList(
    makeStudent(1L, "Chofi", "Maraña", "cho@email.com"),
    makeStudent(2L, "Juan", "Du", "doe@email.com")
    ));

    mockMvc.perform(get("/api/students")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].id").value(1L))
      .andExpect(jsonPath("$[0].name").value("Chofi"))
      .andExpect(jsonPath("$[0].email").value("cho@email.com"));
  }

  @Test
  void getById_shouldReturnOkWithStudentJson() throws Exception { // Verifica obtener por ide existente
    // Prepara el mock: al pedir id 10, devolver un estudiante
    when(studentService.getStudentById(10L)) // Cuando el servicio busque id 10
        .thenReturn(Optional.of(makeStudent(10L, "Grace", "Hopper", "grace@example.com")));
        // Devolver optional con parametros

    // Ejecuta GET /api/students/10 y valida el JSON
    mockMvc.perform(get("/api/students/{id}", 10L) // Construye la URL con path variable 10
        .accept(MediaType.APPLICATION_JSON)) // Aceptar JSON
        .andExpect(status().isOk()) // 200 OK
        .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // application/json
        .andExpect(jsonPath("$.id").value(10L)) // id == 10
        .andExpect(jsonPath("$.lastname").value("Hopper")); // apellido == Hopper
  }


  private Student makeStudent(Long id, String name, String lastname, String email) {
    Student student = new Student(name, lastname, email);
    student.setId(id);
    return student;
  }
}
