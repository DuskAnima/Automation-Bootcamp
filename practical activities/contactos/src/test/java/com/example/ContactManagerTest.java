package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.model.Contact;
import com.example.service.ContactManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ContactManagerTest {
    private ContactManager contactManager;

    @BeforeEach
    void setUp() {
        contactManager = new ContactManager();
    }


    @Test
    void  addContact_debeAgregarUnContactoALaLista () {
        Contact contacto = new Contact("Sofía", "12438174501", "sofia@correo.cl");
        contactManager.agregarContactos(contacto);

        assertThat(contactManager.obtenerContactos(), hasSize(1));
        assertThat(contactManager.obtenerContactos().get(0).getNombre(), is("Sofía"));
    }

    @Test
    void deleteContat_debeEliminarContactoALaLista () {
        Contact contacto = new Contact("Sofía", "12438174501", "sofia@correo.cl");
        contactManager.agregarContactos(contacto);
<<<<<<< HEAD
=======
        contactManager.eliminarContactos("sofia@correo.cl");
>>>>>>> 385238f09b3362c40e5c9dbcdf533b628f37f7aa
        
        assertThat(contactManager.obtenerContactos(), hasSize(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sofía", "Sonia", "Richard", "Margarita"})
    void buscarContactoPorNombre_debeRetornarContactosQueContienenLaCadena(String filtro) {
        contactManager.agregarContactos( new Contact("Sofía", "12438174501", "sofia@correo.cl"));
        contactManager.agregarContactos( new Contact("Sonia", "12438174501", "sonia@correo.cl"));
        contactManager.agregarContactos( new Contact("Richard", "12438174501", "richarda@correo.cl"));
        contactManager.agregarContactos( new Contact("Margarita", "12438174501", "margarita@correo.cl"));

        var resultado = contactManager.buscarContactoPorNombre(filtro);
/*
 assertThat(resultado, hasSize(2));
 assertThat(resultado.get(0).getNombre(), containsString("So"));
 assertThat(resultado.get(1).getNombre(), containsString("So"));
 */
        assertTrue(resultado.stream().anyMatch(contact -> contact.getNombre().contains(filtro)));

    }

}
