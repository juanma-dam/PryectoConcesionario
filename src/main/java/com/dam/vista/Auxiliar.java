package com.dam.vista;

public class Auxiliar {

    public static boolean verificarDni(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }

        if(dni.toUpperCase().charAt(8) != dni.charAt(8)) {
            return false;
        }

        return Character.isLetter(dni.charAt(8));
    }

    public static boolean verificarMatricula(String matricula) {
        if (matricula == null || matricula.length() != 7) {
            return false;
        }

        String m = matricula.toUpperCase();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(m.charAt(i))) {
                return false;
            }
        }

        for (int i = 4; i < 7; i++) {
            char c = m.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}
