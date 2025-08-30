package com.example.rest.security.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserAccount {
  
  // Identificador primario autogenerado por la base de datos
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Nombre de usuario  único y obligatorio, limitado a 100 caracteres.
  @Column(unique = true, nullable = false, length = 100)
  private String username;

  // Hash de la contraseña (BCrypt). 
  @Column(nullable = false)
  private String password;

  // Flog de habilitación de la cuenta (true = activa, false = deshabilitado/bloqueado).
  @Column(nullable = false)
  private boolean enabled = true;

  // Version del token. Si se incrementa, incalida los refresh tokens existentes.
  @Column(nullable = false)
  private int tokenVersion = 0;

  // Colección de roles del usuario. Se persiste en tabla separada user_roles (user_id, roles).
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "roles", nullable = false, length = 30)
  private Set<Role> roles = new HashSet<>();

  // Getters y setters estándar

  public Long getId() {  return id;  }

  public void setId(Long id) {  this.id = id;  }

  public String getUsername() {  return username;  }

  public void setUsername(String username) {  this.username = username;  }

  public String getPassword() {  return password;  }

  public void setPassword(String password) {  this.password = password;  }

  public boolean isEnabled() {  return enabled;  }

  public void setEnabled(boolean enabled) {  this.enabled = enabled;  }

  public int getTokenVersion() {  return tokenVersion;  }

  public void setTokenVersion(int tokenVersion) {  this.tokenVersion = tokenVersion;  }

  public Set<Role> getRoles() {  return roles;  }

  public void setRoles(Set<Role> roles) {  this.roles = roles;  }

  /*
   * Incrementa la versión de token para invalidar refresh tokens previamente emitidos.
   * Útil para forzar cierre de sesión global del ususario (p. ej., al cambiar contraseña).
   */

  public void incrementTokenVersion() {
    this.tokenVersion++;
  }
}
