package com.example.academy.exceptions;

public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException(String message){
    super(message);
  }
}