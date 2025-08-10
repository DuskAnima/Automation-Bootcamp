package com.example.steps;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.example.pages.FormularioPagoPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PageSteps {
  private WebDriver driver;
  //private String resultado;
  private FormularioPagoPage formularioPagoPage;

  @Before
  public void setUp() {
    System.out.println("Iniciando escenario de prueba....");
    WebDriverManager.firefoxdriver().clearDriverCache().setup();
    FirefoxOptions options = new FirefoxOptions();
    driver = new FirefoxDriver(options);
    driver.manage().window().maximize();
    formularioPagoPage = new FormularioPagoPage(driver);
  }

  @After
  public void tearDown() {
    if(driver != null) driver.quit();
    System.out.println("Finalizando escenario de prueba...");
  }

  @Given("que el usuario accede al formulario de pago")
  public void que_el_usuario_accede_al_formulario_de_pago() {
     // Navegar a la página del formulario de pago
    formularioPagoPage.navigateTo();
  }

  // (\\d+|-\\d+) sirve para capturar un numero positivo (\\d+), negativo (-\\d+)
  // (Tarjeta|Efectivo|Transferencia)? = Permite capturar cualquiera de estos 3 medios de pago o ninguno ( el ? lo hace opcional)
  @When("^ingresa el monto (\\d+|-\\d+) y selecciona el medio (Tarjeta|Efectivo|Transferencia)?$")
  public void ingresa_el_monto_y_selecciona_el_medio(double monto, String medio) {
    // Ingresar el monto
    formularioPagoPage.ingresarMonto(monto);
    // Seleccionar el medio de pago
    formularioPagoPage.seleccionarMedioPago(medio);
    // Pagar 
    formularioPagoPage.pagar();
  }

  @Then("el sistema debería mostrar el mensaje {string}")
  public void el_sistema_deberia_mostrar_el_mensaje(String mensajeEsperado) {
    String mensajeReal = formularioPagoPage.obtenerMensajeResultado();
    Assertions.assertTrue(mensajeReal.contains(mensajeEsperado), "EL mensaje real no contiene el esperado, Esperado: '" + mensajeEsperado + "', Real: '" + mensajeReal + "'");
  }



}
