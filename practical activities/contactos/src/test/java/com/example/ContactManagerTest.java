package com.example;

import org.junit.jupiter.api.Test;

import com.example.model.Contact;
import com.example.service.ContactManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ContactManagerTest {
    
    @Test
    void  addContact_debeAgregarUnContactoALaLista () {

        ContactManager contactManager = new ContactManager();
        Contact contacto = new Contact("Sofía", "12438174501", "sofia@correo.cl");
        contactManager.agregarContactos(contacto);

        assertThat(contactManager.obtenerContactos(), hasSize(1));
        assertThat(contactManager.obtenerContactos().get(0).getNombre(), is("Sofía"));
    }

    @Test
    void deleteContat_debeEliminarContactoALaLista () {

        ContactManager contactManager = new ContactManager();
        Contact contacto = new Contact("Sofía", "12438174501", "sofia@correo.cl");
        contactManager.agregarContactos(contacto);
        
        assertThat(contactManager.obtenerContactos(), hasSize(0));
    }
}
