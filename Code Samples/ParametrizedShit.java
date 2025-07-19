package cl.kibernumacademy.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import cl.kibernumacademy.services.ProductManager;

public class ProductManagerTest {
    private ProductManager manager; // servicio
    public static int counter;
    @BeforeEach
    void StartSetUp() {
        System.out.println("Inicio de Test Unitario " + ++counter);
        manager = new ProductManager(); 
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin del Test Unitario");
    }

    ///////// Pruebas de Agregación \\\\\\\\\
    /// 
    @DisplayName("Prueba de agregación de productos")
    @Test 
    void shouldAddProductToAList() {
        assertThat(manager.getList(), iterableWithSize(0)); // Validación de lista vacía.
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0); // Producto agregado por medio del manager
        var product =  manager.getList().get(0); // Acceso a la tarea agregada desde la lista.

        assertNotNull(product, "El producto no puede ser nulo"); // Verifica que la tarea creada está en lista.
        assertThat(manager.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista.
        assertThat(product, allOf(      // Verifica asignación de atributos del producto.
            hasProperty("name", is("Comida etapa 1")),
            hasProperty("description", is("de 1 hasta 6 meses")),
            hasProperty("price", is(19990.0))
        ));
    }
    
    @DisplayName("Prueba de asignación de precios. Casos Borde Válidos")
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 9999999.99, 0.0001})
    void shouldAddProductToListWhenPriceIsValid(double validPrice) {
        ProductManager manager = new ProductManager();
        manager.addProduct("Producto válido", "Descripción válida", validPrice);
        assertThat(manager.getList(), hasSize(1));
        assertThat(manager.getList().get(0), hasProperty("price", is(validPrice)));
    }
    
    @DisplayName("Prueba de asignación de precios inválidos")
    @ParameterizedTest
    @MethodSource("invalidProductData")
    void shouldThrowWhenInvalidInputs(String name, String description, double price) {
        ProductManager manager = new ProductManager();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addProduct(name, description, price);
        });
    }

    static Stream<Arguments> invalidProductData() {
        return Stream.of(
            Arguments.of("", "Descripción", 1000.0), // Prueba de cadenas vacías
            Arguments.of("Nombre", "", 1000.0),
            Arguments.of(null, "Descripción", 1000.0), // Prueba de null
            Arguments.of("Nombre", null, 1000.0),
            Arguments.of("Nombre", "Descripción", 0.0), // Prueva de precios inválidos
            Arguments.of("Nombre", "Descripción", 0.9999),
            Arguments.of("Nombre", "Descripción", -10.0)
        );
    }

    ///////// Pruebas de modificación \\\\\\\\\

    @DisplayName("Prueba de modificación de productos")
    @ParameterizedTest
    @CsvSource ({
        "name, Leche para lactantes",
        "description, Recomendada para recién nacidos",
        "price, 20990.0"
    })
    void shouldUpdateProduct(String test, String newValue) {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0);
        var product = manager.getList().get(0);

        // Verifica la no nulidad del producto
        assertNotNull(product, "El producto no puede ser nulo");

        // Verifica la integridad de los cambios de datos correspondientes
        assumingThat(test.equals("name"), () -> {
            manager.updateName(product.getId(), newValue);
            assertThat(product, hasProperty("name", is(newValue))); 
        });
        assumingThat(test.equals("description"), () -> {
            manager.updateDescription(product.getId(), newValue);
            assertThat(product, hasProperty("description", is(newValue)));
        });
        assumingThat(test.equals("price"), () -> {
            manager.updatePrice(product.getId(), Double.parseDouble(newValue));
            assertThat(product, hasProperty("price", is(Double.parseDouble(newValue))));
        }); // Este es un ejemplo un poco hardcoded, pero sirve para demostrar el uso de assumingThat()
    }

    @DisplayName("Prueba de modificación inválida de productos")
    @ParameterizedTest
    @MethodSource("invalidUpdateData")
    void shouldThrowWhenUpdatingWithInvalidValues(String field, Object newValue) {
        ProductManager manager = new ProductManager();
        manager.addProduct("Nombre original", "Descripción original", 1000.0);
        int id = manager.getList().get(0).getId();

        assertThrows(IllegalArgumentException.class, () -> {
            switch (field) {
                case "name" -> manager.updateName(id, (String) newValue);
                case "description" -> manager.updateDescription(id, (String) newValue);
                case "price" -> manager.updatePrice(id, (Double) newValue);
            }
        });
    }

    static Stream<Arguments> invalidUpdateData() {
    return Stream.of(
        Arguments.of("name", null),
        Arguments.of("name", ""),
        Arguments.of("description", null),
        Arguments.of("description", ""),
        Arguments.of("price", -100.0),
        Arguments.of("price", 0.0),
        Arguments.of("price", 0.9999)
    );
}

    ///////// Pruebas de Eliminación \\\\\\\\\

    @DisplayName("Prueba de eliminación de productos")
    @Test
    void shouldDeleteProductById() {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0);
        assertThat(manager.getList(), iterableWithSize(1)); // Validación de lista con 1 objeto.
        int productId = manager.getList().get(0).getId();
        
        // Verifica la eliminación del producto
        manager.deleteProduct(productId);
        assertThat(manager.getList(), iterableWithSize(0));
    }
}
