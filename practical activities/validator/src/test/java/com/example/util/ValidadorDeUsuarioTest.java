package com.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class ValidadorDeUsuarioTest {
    private ValidadorDeUsuario validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorDeUsuario();
    }

    @Test
    void testNombreValido() {
        assertTrue(validador.esNombreValido("Sofía"));
        assertFalse(validador.esNombreValido(""));
        assertFalse(validador.esNombreValido(null));
        assertFalse(validador.esNombreValido("A"));
        assertNotNull(validador);
    }


}
