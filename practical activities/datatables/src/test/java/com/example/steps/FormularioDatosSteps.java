package com.example.steps;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.example.pages.FormularioDatosPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FormularioDatosSteps {
    private WebDriver driver;
    private FormularioDatosPage formularioDatosPage;

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formularioDatosPage = new FormularioDatosPage(driver);
    }

    @After
    public void tearDown() {
        if(driver != null) driver.quit();
    }

    @Given("que el usuario accede al formulario de datos")
    public void que_el_usuario_accede_al_formulario_de_datos() {
        formularioDatosPage.abrir();
    }

    @When("completa los siguientes datos:")
    public void completa_los_siguientes_datos(DataTable dataTable) {
        Map<String, String> datos = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : datos.entrySet()) {
            System.out.println("Campo: " + entry.getKey() + "- Valor: " + entry.getValue());
            switch (entry.getKey()) {
                case "nombre":
                    formularioDatosPage.ingresarNombre(entry.getValue());
                    break;
                case "apellido":
                    formularioDatosPage.ingresaApellido(entry.getValue());
                    break;
                case "email":
                    formularioDatosPage.ingresaEmail(entry.getValue());
                    break;
                case "edad":
                    formularioDatosPage.ingresaEdad(entry.getValue());
                    break;
                default:
                    System.out.println("Dato no manejado");
                    break;
            }
        }
        formularioDatosPage.enviarFormulario();
    }
    @Then("los datos ingresados deben mostrarse correctamente")
    public void los_datos_ingresados_deben_mostrarse_correctamente() {
        System.out.println("Nombre mostrado: " + formularioDatosPage.obtenerNombreMostrado());
        System.out.println("Apellido mostrado: " + formularioDatosPage.obtenerApellidoMostrado());
        System.out.println("Edad mostrada: " + formularioDatosPage.obtenerEdadMostrada());
    }
}
