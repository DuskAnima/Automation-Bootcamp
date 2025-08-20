package com.example.biblioteca.hooks;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
  @Before
  public void setUp() {
    WebDriver driver = WebDriverManager.firefoxdriver().create();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    DriverHolder.set(driver);
    driver.get("https://biblioteca-testing.netlify.app/");
  }

  @After
  public void tearDown(Scenario scenario) {
    // Obtener el driver que se está usando
    WebDriver driver = DriverHolder.get();
    if(scenario.isFailed()) {
      byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(shot, "image/png", scenario.getName());
    }
    driver.quit(); // Cierra el navegador
    DriverHolder.remove(); // Libera el hilo, evita las fugas de memoria
  }
}
