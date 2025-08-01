package com.example;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.example.contact.Contact;

public class ContactManagerTest {
    private ContactManager manager;

    @BeforeClass
    public void setUp() {
        manager = new ContactManager();
    }

    @AfterClass
    public void tearDown(){
        manager = null;
    }
    
    @BeforeMethod
    public void limpiarContactos() {
        manager.getContactos().clear();
    }

    @Test
    @Parameters({"nombre", "correo"})
    public void testAgregarContacto(String nombre, String correo) {
        Contact contacto = new Contact(nombre, correo);
        manager.agregarContacto(contacto);
        Assert.assertTrue(manager.getContactos().contains(contacto), "El contacto debe estar en la lista");
    }

    @Test
    @Parameters({"nombre"})
    public void testDesactivarContacto(String nombre) {
        Contact contacto = new Contact(nombre, "correo@ejemplo.com");
        manager.agregarContacto(contacto);
        boolean desactivar = manager.desactivarContacto(nombre);
        Assert.assertTrue(desactivar, "El contacto debe desactivarse");
        Assert.assertEquals(contacto.getEstado(), Contact.Estado.INACTIVO);
    }

    @Test public void testDesactivarContactoExistente() {
        boolean desativado = manager.desactivarContacto("no existe");
        Assert.assertFalse(desativado, "No debe desactivar contacto inexistente");
    }

    @Test 
    public void testFiltrarPorEstado() {
        Contact c1 = new Contact("c1", "ci1@mail.com");
        Contact c2 = new Contact("c2", "ci2@mail.com");
        manager.agregarContacto(c1);
        manager.agregarContacto(c2);
        manager.desactivarContacto("c1");
        List<Contact> activos = manager.filtrarPorEstado(Contact.Estado.ACTIVO);
        List<Contact> inactivos = manager.filtrarPorEstado(Contact.Estado.INACTIVO);
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(activos.contains(c2));
        sa.assertTrue(inactivos.contains(c1));
        sa.assertAll();
    }
}
