package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.model.Contact;

public class ContactManager {
    private List<Contact> contactos = new ArrayList<>();

    public void agregarContactos(Contact contacto) {
        contactos.add(contacto);
    }

    public List<Contact> obtenerContactos() {
        return Collections.unmodifiableList(contactos);
    }
}
