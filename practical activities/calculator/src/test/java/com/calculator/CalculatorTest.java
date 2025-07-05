package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        // Crea instancia de clase calculator

        // Probar sumas de 2+3, el resultado esperado es 5.
        assertEquals(5, calculator.add(2, 3), "La suma de 2 + 3 debe ser 5");
    }

    public void testSubtract() {
        // Crea instancia de clase calculator
        Calculator calculator = new Calculator();

        // Probar restas de 5-3, el resultado esperado es 2.
        assertEquals(2, calculator.subtract(5, 3), "La resta de 5 - 3 debe ser 2");
    }

}
