package com.example.biblioteca.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.biblioteca.hooks.DriverHolder;

public class LoginPage {
  private By userField = By.id("username");
  private By passwordField = By.id("password");
  private By loginButton = By.xpath("//form[@id='login-form']//button[@type='submit']");
  private By messageError = By.id("login-message");

  // Retorna el driver que se está utilizando
  private WebDriver getDriver() {
    return DriverHolder.get();
  }

  public void abrir() {
    getDriver().get("https://biblioteca-testing.netlify.app/");
  }

  public void login(String usuario, String password) {
    WebDriver driver = getDriver();
    driver.findElement(userField).sendKeys(usuario);
    driver.findElement(passwordField).sendKeys(password);
    driver.findElement(loginButton).click();
  }

  public String obtenerError() {
    List<WebElement> messageErrors = getDriver().findElements(messageError);
    return messageErrors.isEmpty() ?"": messageErrors.get(0).getText().trim();
  }

}
