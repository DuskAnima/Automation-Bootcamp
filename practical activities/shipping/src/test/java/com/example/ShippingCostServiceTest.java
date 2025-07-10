package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName ("Test oara ShippingCostService")
public class ShippingCostServiceTest {
    private ShippingCostService shippingCostService;

    @BeforeEach
    void setUp() {
        shippingCostService = new ShippingCostService();
    }

    @Test
    @DisplayName("Arr <= 1KG, No Frágil ->Act, calcular -> Assert: $5 ")
    void paqueteLigeroNoFragil() {
        // Arrange. Preparando datos de entrada
        double peso = 0.5;
        boolean fragil = false;
        double esperado = 0.5;
        // Act: Ejecutar acción a probar
        double resultado = shippingCostService.calcularCosto(peso, fragil);
        assertEquals(esperado, resultado, "Hasta 1 kg sin fragilidad dene costar $5");
    }

    @Test
    @DisplayName("Arr <= 1KG, No Frágil ->Act, calcular -> Assert: $5 ")
    void paqueteExacto1KgFragil() {
        // Arrange. Preparando datos de entrada
        double peso = 1.0;
        boolean fragil = true;
        double esperado = 10.0;
        // Act: Ejecutar acción a probar
        double resultado = shippingCostService.calcularCosto(peso, fragil);
        assertEquals(esperado, resultado, "1 kg con fragilidad dene costar $10");
    }

}
