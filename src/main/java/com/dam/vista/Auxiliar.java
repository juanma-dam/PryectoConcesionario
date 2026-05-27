package com.dam.vista;

/**
 * CLASE AUXILIAR<br>
 * Métodos de apoyo al proyecto (validaciones)
 *
 * @author Iván Álvarez
 * @author Juan Manuel Sanabria Mamani
 * @author Alfonso Marín Navarro
 * @version 1.0
 * @since 27/05/2026
 */
public class Auxiliar {

    /**
     * Método de validación de un DNI
     * @param dni String a validar
     * @return Devuelve true si el DNI es correcto o false en otro caso
     */
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

    /**
     * Método de validación de una Matricula
     * @param matricula String a validar
     * @return Devuelve true si la matricula es correcto o false en otro caso
     */
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
