package com.example.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.pages.FormPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FormTest {
    private WebDriver driver;
    private FormPage formPage;
    
    @BeforeAll
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        driver = new ChromeDriver(options);
        System.setProperty("file.encoding", "UTF-8");
    }

    @BeforeEach
    void setUpTest() {
        driver.manage().window().maximize();
        driver.get("https://selenium-bootcamp.netlify.app/");
        
        formPage = new FormPage(driver); 
    }

    @AfterEach
    void tearDown() {
        if(driver != null) driver.quit();
    }


    @Test
    void testFormFilling() {
    formPage.setFirstName("Sofia");
    formPage.setLastName("Perez");
    formPage.setUserEmail("sofia@correo.com");
    formPage.selectGender("Femenino");
    formPage.setMobile("1234567890");
    // formPage.selectHobby("Lectura");
    // formPage.selectHobby("Música");
    formPage.setAddress("Calle Falsa 123");
    formPage.selectState("Región Metropolitana");
    formPage.selectCity("Santiago");
    formPage.submitForm();

    String modalContent = formPage.getModalBodyContent();
    assertTrue(modalContent.contains("Sofia"));
    assertTrue(modalContent.contains("Perez"));
    assertTrue(modalContent.contains("sofia@correo.com"));
    assertTrue(modalContent.contains("Femenino"));
    assertTrue(modalContent.contains("1234567890"));
    // assertTrue(modalContent.contains("Lectura"));
    // assertTrue(modalContent.contains("Música"));
    assertTrue(modalContent.contains("Calle Falsa 123"));
    assertTrue(modalContent.contains("Región Metropolitana"));
    assertTrue(modalContent.contains("Santiago"));

    }

}
