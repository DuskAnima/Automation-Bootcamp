package com.example.rest.security.dto;

import jakarta.validation.constraints.NotBlank;

// Los DTO no hacen lógica, solo transportan datos

public class LoginRequest {
  // Nombre de usuario a autenticar. Obligatorio.
  @NotBlank
  private String username;
  // Contraseña en texto plano (se validará y nunca se almacena así). Obligatorio.
  @NotBlank
  private String password;

  // Getters y Setters estándar requeridos para la deserialización JSON.
  public String getUsername() { return username;  }
  public void setUsername(String username) {  this.username = username;  }
  public String getPassword() { return password;  }
  public void setPassword(String password) {  this.password = password;  }

}
