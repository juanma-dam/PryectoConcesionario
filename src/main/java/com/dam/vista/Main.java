package com.dam.vista;


import java.util.Scanner;

public class Main {

    public static void mostrarMenu() {

        System.out.println("MENU");
        System.out.println("--------");
        System.out.println();
        System.out.println("1. Alta de Vehículos");
        System.out.println("2. Baja de Vehículos");
        System.out.println("3. Alta de Clientes");
        System.out.println("4. Baja de Clientes");
        System.out.println("5. Nueva Venta");
        System.out.println("6. Listado de Vehículos");
        System.out.println("7. Listado de Clientes");
        System.out.println("8. Registro de Ventas");
        System.out.println("0. Salir");
        System.out.println();
        System.out.println("Opcion: ");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            mostrarMenu();
            op = sc.nextInt();

            switch (op) {
                case 1: // Alta de Vehiculos
                {

                }
                break;
                case 2: // Baja de Vehiculos
                {

                }
                break;
                case 3: // Alta de Clientes
                {

                }
                break;
                case 4: // Baja de Clientes
                {

                }
                break;
                case 5: // Nueva Venta
                {

                }
                break;
                case 6: // Listado de Vehiculos
                {

                }
                break;
                case 7: // Listado de Clientes
                {

                }
                break;
                case 8: // Registro de Ventas
                {

                }
                break;
                case 0: // Salir
                {

                }
                break;
                default:
            }
        } while (op != 0);
    }
}
