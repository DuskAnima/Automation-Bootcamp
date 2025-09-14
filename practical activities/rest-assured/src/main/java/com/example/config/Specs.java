package com.example.config; // Paquete para clases de configuración usadas por las pruebas

import io.restassured.builder.RequestSpecBuilder; // Constructor para RequestSpecification
import io.restassured.builder.ResponseSpecBuilder; // Constructor para ResponseSpecification
import io.restassured.filter.log.LogDetail; // Enum para controlar el nivel de logging
import io.restassured.http.ContentType; // Enum con tipos de contenido (JSON, XML, etc.)
import io.restassured.specification.RequestSpecification; // Interfaz que representa la sepc de request
import io.restassured.specification.ResponseSpecification; // Interfaz que representa la sepc de response

import static io.restassured.RestAssured.config; // Configuración global de Rest Assured
import static io.restassured.config.HttpClientConfig.httpClientConfig; // Configuración del cliente HTTP

/*
 * Clase que centraliza las especificaciones reutilizables para las pruebas.
 * 
 * Aquí definimos una RequestSpecification con valores comunes (baseUri, content-type,
 * logging y timeouts) y dos ResponseSpecifications para validar códigos 200 y 201.
 */
public class Specs {
  // Spec reutilizable para las peticiones 
  private static final RequestSpecification REQUEST_SPEC;
  // Spec que espera 200 OK
  private static final ResponseSpecification RESPONSE_SPEC_200;
  // Spec que espera 201 created
  private static final ResponseSpecification RESPONSE_SPEC_201;

  // Bloque estático: Se ejecuta una vez cuando la clase se carga en memoria
  static {
    // Obtenemos el timeout desde Env (en milisegundos)
    int timeout = Env.timeoutMs();

    // Construimos el RequestSpecification usando el builder 
    REQUEST_SPEC = new RequestSpecBuilder()
          .setBaseUri(Env.baseUrl()) // Base URI tomada de Env.baseUr()
          .setContentType(ContentType.JSON) // Enviamos JSON en el body
          .setAccept(ContentType.JSON) // Esperamos recobor JSON
          .log(LogDetail.URI) // Registra la URI en los logs (útil para depurar)
          .build() // Construye la RequestSpecification
          .config(config().httpClient(httpClientConfig() // Configuración del cliente HTTP
                  .setParam("http.connection.timeout", timeout) // Timeout de conexión
                  .setParam("http.socket.timeout", timeout) // Timeout de socket (lectura)
                  .setParam("http.connection-manager.timeout", timeout))); // Timeout de connection manager
  
    // Construimos la ResponseSpecification que espera 200 OK
    RESPONSE_SPEC_200 = new ResponseSpecBuilder()
          .expectStatusCode(200) // Validación del código de estado
          .build();
  
    // Construimos la ReponseSpecifications que espera 201
    RESPONSE_SPEC_201 = new ResponseSpecBuilder()
          .expectStatusCode(201) // Validación del código de estado
          .build();
  }

  // Constructor privado para evitar instanciación
  private Specs() {}

  // Métodos públicos para obtener las specs desde otras clases (por ejemplo, PostClient)
  public static RequestSpecification request() { return REQUEST_SPEC; }
  public static ResponseSpecification ok200() { return RESPONSE_SPEC_200; }
  public static ResponseSpecification created201() { return RESPONSE_SPEC_201; }
}
