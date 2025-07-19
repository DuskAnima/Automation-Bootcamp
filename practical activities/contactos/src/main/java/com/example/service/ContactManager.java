package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public void eliminarContactos(String email) {
        Iterator<Contact> iterator = contactos.iterator();
        while (iterator.hasNext()) {
            Contact contacto = iterator.next();
            if (contacto.getEmail().equals(email)) {
                iterator.remove();
                break;
            }
        }
    }

        /*         for(Contact contacto: contactos){
            if (contacto.getEmail().equals(email)) {
                contactos.remove(contacto);
                break;
            }
        }
    }
*/

    public List<Contact> buscarContactoPorNombre(String nombre) {
        List<Contact> resultadoBusqueda = new ArrayList<>();
        String filtro = nombre.toLowerCase();
        for (Contact contacto: contactos) {
            if (contacto.getNombre().toLowerCase().contains(filtro)) {
                resultadoBusqueda.add(contacto);
            }
        }
        return List.copyOf(resultadoBusqueda);
    }
    
}
