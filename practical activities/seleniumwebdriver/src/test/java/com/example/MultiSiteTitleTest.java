package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiSiteTitleTest {

    private WebDriver driver;

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void validarTituloStackOverflow() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe"); // Cambia si es necesario

        driver = new ChromeDriver(options);
        driver.get("https://stackoverflow.com");

        assertTrue(driver.getTitle().contains("Stack Overflow"));
    }

    @Test
    void validarTituloGitHub() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe"); // Cambia si es necesario

        driver = new ChromeDriver(options);
        driver.get("https://github.com");

        assertTrue(driver.getTitle().contains("GitHub"));
    }

    @Test
    void validarTituloKibernum() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe"); // Cambia si es necesario

        driver = new ChromeDriver(options);
        driver.get("https://www.kibernumacademiadigital.com/");

        assertTrue(driver.getTitle().contains("Kibernum"));
    }
}
