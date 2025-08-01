package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BingSearchTest {
    private WebDriver driver;
    
    
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBingSearch() {
        driver.get("https://www.bing.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Donkey Kong");
        searchBox.submit();
        assertTrue(driver.getTitle().contains("Donkey Kong"));
        
    }
}
