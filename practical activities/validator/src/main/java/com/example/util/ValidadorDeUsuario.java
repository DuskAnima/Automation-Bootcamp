package com.example.util;

import java.util.regex.Pattern;

import com.example.modelo.Usuario;

public class ValidadorDeUsuario {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public boolean esNombreValido(String nombre) {
        return nombre!= null && !nombre.trim().isEmpty() && nombre.length() >= 3;
    }

    public boolean esEmailValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean esMayorDeEdad(int edad) {
        return edad >= 18;
    }

    public boolean validadorUsuario(Usuario usuario) {
        return  esNombreValido(usuario.getNombre()) &&
                esEmailValido(usuario.getEmail()) &&
                esMayorDeEdad(usuario.getEdad());
    }


}
