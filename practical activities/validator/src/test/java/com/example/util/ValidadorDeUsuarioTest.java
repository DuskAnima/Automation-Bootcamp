package com.example.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;

import com.example.modelo.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ValidadorDeUsuarioTest {
    private ValidadorDeUsuario validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorDeUsuario();
    }


    /*JUnit*/
    @Test
    void testNombreValido() {
        assertTrue(validador.esNombreValido("Sofía"));
        assertFalse(validador.esNombreValido(""));
        assertFalse(validador.esNombreValido(null));
        assertFalse(validador.esNombreValido("A"));
        assertNotNull(validador);
    }

    /*Hamcrest*/
    @Test
    void testEmailValido() {
        assertThat(validador.esEmailValido("sofia@correo.com"), is(true));
        assertThat(validador.esEmailValido("sofia@correo"), is(false));
        assertThat(validador.esEmailValido("correo.com"), is(false));
        assertThat(validador.esEmailValido(null), is(false));
    }

    @Test
    void testMayorDeEdad() {
        int edad = 30;
        assumeTrue(edad > 0, "La edad debe ser positiva");
        assertTrue(validador.esMayorDeEdad(edad));
    }

    @ParameterizedTest
    @CsvSource({
        "Sofía, sofia@correo.com, 20, true",
        "Ana, ana@gmail.com, 15, false",
        ", anonimo@correo.com, 22, false",
        "Richard, richard.stallman@correo, 30, false"
    })
    void testValidarUsuario(String nombre, String correo, int edad, boolean esperado) {
        Usuario usuario = new Usuario(nombre, correo, edad);
        assertEquals(esperado, validador.validadorUsuario(usuario));
    }

    static Stream<Usuario> usuariosValidos() {
        return Stream.of(
            new Usuario("Sofia", "sofia@correo.com", 20),
            new Usuario("Richard", "richard@correo.com", 30)
        );
    }
    @ParameterizedTest
    @MethodSource("usuariosValidos") 
    void testUsuariosValidos(Usuario usuario) {
        assertThat(validador.validadorUsuario(usuario), is(true));
        assertThat(usuario.getNombre(), allOf(notNullValue(), not(blankString())));
        assertThat(usuario.getEmail(), containsString("@"));
    }

    @Test 
    void testValidarUsuarioAssumingThat() {
        Usuario usuario = new Usuario("Sofía", "sofia@correo.com", 20);
        assumingThat(usuario.getEdad() >= 18, 
        () -> assertTrue(validador.validadorUsuario(usuario), "El usuario debe ser válido si es mayor de edad"));
        
    }
}
