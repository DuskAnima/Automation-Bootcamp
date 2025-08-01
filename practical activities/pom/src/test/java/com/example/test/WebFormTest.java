package com.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.pages.WebFormPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebFormTest {
    private WebDriver driver;
    private WebFormPage webFormPage;
    private final String URL = "https://www.selenium.dev/selenium/web/web-form";
    

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup(); // Se configura el driver solo una vez
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions(); // Abre el navegador
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        
        driver = new ChromeDriver(options);
        driver.get(URL);
        webFormPage = new WebFormPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testTextInput() {
        String inputText = "Hello, Selenium!";
        webFormPage.setTextInput(inputText); // Interactúa con el campo de texto
        assertEquals(inputText, webFormPage.getTextInput());
    }

    @Test
    void testSelectMenu() {
        webFormPage.selectOption("Two");
        assertEquals("Two", webFormPage.getSelectMenu());
    }

    @Test
    void testCheckBox() {
        webFormPage.checkCheckBox();
        assertTrue(webFormPage.isCheckBoxChecked());
    }

    @Test
    void testRadioButton() {
        webFormPage.selectRadioButton();
        assertTrue(webFormPage.isRadioButtonSelected());
    }

    @Test
    void testDateInput() {
        webFormPage.setDateInput("2023-10-01");
        assertEquals("2023-10-01", webFormPage.getDateInput());
    }

    @Test
    void testPasswordInput() {
        String password = "myPassword";
        webFormPage.setPasswordInput(password); // Interactúa con el campo de texto
        assertEquals(password, webFormPage.getPasswordInput());
    }

    @Test
    void testSubmitFormSuccess() {
        webFormPage.setTextInput("Hello, Selenium!");
        webFormPage.selectOption("Two");
        webFormPage.checkCheckBox();
        webFormPage.selectRadioButton();
        webFormPage.setDateInput("2023-10-01");
        webFormPage.setTextInput("myPassword");
        webFormPage.clickSubmitButton();

        assertTrue(webFormPage.getSuccessMessage().contains("Received!"), "No es el mensaje de éxito esperado");
    }
}
