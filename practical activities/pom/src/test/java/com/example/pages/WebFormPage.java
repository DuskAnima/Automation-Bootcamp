package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebFormPage {
    private WebDriver driver;
    // Localizadores 
    private By textInput = By.id("my-text-id");
    private By selectMenu = By.name("my-select");
    private By checkBox = By.id("my-check-1");
    private By radioButton = By.id("my-radio-2");
    private By dateInput = By.name("my-date");
    private By passwordInput = By.name("my-password");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By messageSuccess = By.id("message");


    // Constructor: recibe el webdriver para interactuar con la página
    public WebFormPage(WebDriver driver) {
            this.driver = driver;
    }
    
    // Encuentra y escribe en un textinput
    public void setTextInput(String text) {
        driver.findElement(textInput).clear();
        driver.findElement(textInput).sendKeys(text);
    }
    // Retorna string de textinput
    public String getTextInput() {
        return driver.findElement(textInput).getAttribute("value");
    }
    
    // Encuentra y selecciona una opción del dropdown 
    public void selectOption(String option) {
        new Select(driver.findElement(selectMenu)).selectByVisibleText(option);;
    }
    
    // Retorna string de la selección del dropdown
    public String getSelectMenu() {
        return new Select(driver.findElement(selectMenu)).getFirstSelectedOption().getText();
    }

    // Marca el checkbox si no está marcado
    public void checkCheckBox() {
        WebElement checkBoxElement =  driver.findElement(checkBox);
        if (!checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }
    }

    // Retorna true si el checkbox está marcado
    public boolean isCheckBoxChecked() {
        return driver.findElement(checkBox).isSelected();
    }

    // Marca el radiobutton si no está marcado
    public void selectRadioButton () {
        WebElement radioButtonElement = driver.findElement(radioButton); // Usando el localizador busca el elemento
        if (!radioButtonElement.isSelected()) radioButtonElement.click(); // Si no ha sido seleccionado, se selecciona
    }

    // Retorna true si el radiobutton está marcado
    public boolean isRadioButtonSelected() {
        return driver.findElement(radioButton).isSelected();
    }

    // Escribe una fecha en el campo correspondiente
    public void setDateInput(String date) {
        WebElement dateElement = driver.findElement(dateInput); // Localuza campo
        dateElement.clear(); // Limpia campo
        dateElement.sendKeys(date); // Escribe fecha
    }

    // Retorna string de fecha ingresada
    public String getDateInput() {
        return driver.findElement(dateInput).getAttribute("value");
    }
    
    // Encuentra y escribe en un textinput
    public void setPasswordInput(String password) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    // Retorna string de textinput
    public String getPasswordInput() {
        return driver.findElement(passwordInput).getAttribute("value");
    }

    // Hace click en el botón submit
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    // Obtiene mensáje de éxito tras envío de formulario
    public String getSuccessMessage() {
        return driver.findElement(messageSuccess).getText();
    }
}
