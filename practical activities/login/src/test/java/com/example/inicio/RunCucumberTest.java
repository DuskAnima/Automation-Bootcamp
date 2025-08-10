package com.example.inicio;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

// Clase con una suite de pruebas para ejecutar Cucumber
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Indica ruta de los archivos con extensiones .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.inicio.steps") // Indica donde están los steps definidos
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,summary,json:target/cucumber-reports.json") // Genera reporte json
public class RunCucumberTest {
    
}
