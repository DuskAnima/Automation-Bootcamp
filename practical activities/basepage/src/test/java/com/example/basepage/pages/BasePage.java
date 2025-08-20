package com.example.basepage.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    
    protected static WebDriver driver; // Inicializa WebDriver y WebDriverWait para cada escenario.
    private static WebDriverWait wait; // Se debe llamar desde el hook @Before en la carpeta steps.

    public static void initDriver() {
        if (driver != null) driver.quit();
        WebDriverManager.chromedriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--windows-size=800, 600");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public BasePage() {
        // No hace nada por que 
        // Todo lo inicializamos en el initDriver()
        // Si se inicializa en el constructor cada step cerrará el navegador y no permitirá que
        // el proximo step abra el navegador
        // Es por eso que se inicializa con initDriver(), porque al ser un método estático que no
        // no pertenezca a ninguna instancia, ya que no pertenecerá a ninguna instancia,
        // independiente de cualquier instancia, por lo tanto se puede llamar las veces que sea
        // necesario.
        // Además se incluye en el BasePage porque es el que debe cargar con esta funcionalidad.
        // ya que debe contener generalidades heredables.
    }

    // Ir al sitio web
    public static void navigateTo(String url) {
        System.err.println(url);
        driver.get(url);
    }

    // Cerrar navegador
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    // Método private
   protected WebElement find(String type, String locator) {
    By by;
    switch (type.toLowerCase()) {
      case "id":
        by = By.id(locator);
        break;
      case "css":
        by = By.cssSelector(locator);
        break;
      case "xpath":
        by = By.xpath(locator);
        break;
      case "class":
        by = By.className(locator);
        break;
      case "name":
        by = By.name(locator);
        break;
      case "tag":
        by = By.tagName(locator);
        break;
      case "linktext":
        by = By.linkText(locator);
        break;
      default:
        throw new IllegalArgumentException("Tipo de locator no soportado: " + type);
    }
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

    public void writte(String type, String locator, String textToWritte) {
        find(type, locator).clear(); // Limpia campo de texto
        find(type, locator).sendKeys(textToWritte); // Escribe el texto declarado
    }

    public void clickElement(String type, String locator) {
      wait.until(ExpectedConditions.elementToBeClickable(find(type, locator))).click();
    }

    public String getInputValidationByIdBootstrap5(String inputId) {
      WebElement input = find("id", inputId);
      if(input.getAttribute("class").contains("is-invalid")) {
        // Selecciona todos los elementos <div> que son hermanos y están después del input
        // y filtra solo los elementos div que contengan la clase 'invalid-feedback'
        return input.findElement(By.xpath("following-sibling::div[contains(@class, 'invalid-feedback')]")).getText();
      }
      return "";
    }

}
