package com.example.academy.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import com.example.academy.entities.Student;
import com.example.academy.exceptions.StudentNotFoundException;
import com.example.academy.services.IStudentService;

@Controller
public class StudentController {
  
  private final IStudentService service;

  public StudentController(IStudentService service) {
    this.service = service;
  }

  @GetMapping({"/students", "/"})
  public String listarEstudiantes(Model model) {
    model.addAttribute("students", service.listAllStudents());
    return "students";
  }

  @GetMapping({"/students/new"})
  public String createStudentForm(Model model) {
    Student student = new Student();
    model.addAttribute("student", student);
    return "create-student";
  }

  @PostMapping("/students")
  public String saveStudent(@ModelAttribute("student") Student student) {
    service.saveStudent(student);
    return "redirect:/students";
  }

  @GetMapping("/students/edit/{id}")
  public String showFormEditStudent(@PathVariable Long id, Model model) {
    Optional<Student> optionalStudent = service.getStudentById(id);
    if (!optionalStudent.isPresent()) {
      throw new StudentNotFoundException("No se encontró un estudiante con id: " + id);
    }
    Student studentExists = optionalStudent.get();
    model.addAttribute("student", studentExists);
    return "edit_student";
  }

  @PostMapping("/students/{id}")
  public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
    Optional<Student> optionalStudent = service.getStudentById(id);
    System.out.println(optionalStudent.isPresent());
    if (!optionalStudent.isPresent()) {
      throw new StudentNotFoundException("No se encontró un estudiante con id: " + id);
    }
    Student studentExists = optionalStudent.get();
    // Actualizar el estudiante... 
    studentExists.setId(id);
    studentExists.setName(student.getName());
    studentExists.setLastname(student.getLastname());
    studentExists.setEmail(student.getEmail());

    service.updateStudent(studentExists);
    return "redirect:/students";
  }

  @GetMapping("/students-delete/{id}")
  public String eliminarEstudiante(@PathVariable Long id) {
    service.deleteStudentById(id);
    return "redirect:/students";
  }
}

