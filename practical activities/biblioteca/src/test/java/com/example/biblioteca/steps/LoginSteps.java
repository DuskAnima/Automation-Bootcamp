package com.example.biblioteca.steps;

import org.junit.jupiter.api.Assertions;

import com.example.biblioteca.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
  LoginPage loginPage;

  @Given("que el bibliotecario abre la página de login")
  public void que_el_bibliotecario_abre_la_página_de_login() {
    loginPage = new LoginPage();
    loginPage.abrir();
  }

  @When("ingresa credenciales válidas")
  public void ingresa_credenciales_válidas() {
    loginPage = new LoginPage();
    loginPage.login("admin", "secret");
  }

  @Then("accede al registro de préstamo")
  public void accede_al_registro_de_préstamo() {
    Assertions.assertTrue(loginPage.obtenerError().isEmpty(), "No debería haber un mensaje de error");
  }

  @When("ingresa credenciales inválidas")
  public void ingresa_credenciales_inválidas() {
    loginPage = new LoginPage();
    loginPage.login("userError", "faild");
  }

  @Then("ve un mensaje de error de login")
  public void ve_un_mensaje_de_error_de_login() {
    loginPage = new LoginPage();
    Assertions.assertTrue(loginPage.obtenerError().contains("inválidas"), "Debe mostrar el mensaje de credenciales inválidas");
    
  }
}
