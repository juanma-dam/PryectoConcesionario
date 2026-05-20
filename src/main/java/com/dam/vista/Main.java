package com.dam.vista;


import com.dam.modelo.*;

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

        try {
            System.out.println("Cargando Base de datos...");
            ConexionBD.abrirConexion();
            System.out.println("Base de datos abierta correctamente.");
        } catch (Exception e) {
            System.out.println("Error al abrir la base de datos " + e.getMessage());
        }

        do {
            mostrarMenu();
            op = sc.nextInt();

            switch (op) {
                case 1: // Alta de Vehiculos
                {
                    int opcion;
                    int id_aux;
                    String marca_aux;
                    String modelo_aux;
                    int precio_aux;
                    int tipo_aux;
                    int anio_aux;
                    int prop_aux;
                    Vehiculo vehiculo = null;
                    System.out.println("Que tipo de propulsion lleva el coche? (1:electrico, 2:hibrido, 3: gasolina");
                    opcion = sc.nextInt();
                    System.out.println("Introduce el id: ");
                    id_aux = sc.nextInt();
                    System.out.println("Introduce la marca del vehículo: ");
                    marca_aux = sc.next();
                    System.out.println("Introduce el modelo del vehículo: ");
                    modelo_aux = sc.next();
                    System.out.println("Introduce el precio del vehículo: ");
                    precio_aux = sc.nextInt();
                    System.out.println("Introduce el año: ");
                    anio_aux = sc.nextInt();
                    System.out.println("Que tipo de coche es (1: nuevo 2: segunda mano");
                    tipo_aux = sc.nextInt();
                    switch (opcion) {
                        case 1:
                        {
                            System.out.println("Introduce la autonomia del coche: ");
                             vehiculo = new Electrico(id_aux, marca_aux, modelo_aux, precio_aux, anio_aux, sc.nextInt());

                            if (tipo_aux == 1) {
                                vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
                            } else {
                                vehiculo.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
                                System.out.println("Introduce la matricula del coche: ");
                                vehiculo.setMatricula(sc.next());
                            }
                        }
                        break;
                        case 2:
                        {
                            vehiculo = new Hibrido(id_aux, marca_aux, modelo_aux, precio_aux, anio_aux);
                            System.out.println("Que hibridacion lleva el coche?(1: ENCHUFABLE 2: NO ENCHUFABLE): ");
                            prop_aux = sc.nextInt();

                            if (prop_aux == 1) {
                                ((Hibrido)vehiculo).setElectrificacion(Hibrido.Electrificacion.ENCHUFABLE);
                            } else {
                                ((Hibrido)vehiculo).setElectrificacion(Hibrido.Electrificacion.NO_ENCHUFABLE);
                            }

                            if (tipo_aux == 1) {
                                vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
                            } else {
                                vehiculo.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
                                System.out.println("Introduce la matricula del coche: ");
                                vehiculo.setMatricula(sc.next());
                            }
                        }
                        break;
                        case 3:
                        {
                            vehiculo = new Gasolina(id_aux, marca_aux, modelo_aux, precio_aux, anio_aux);
                            System.out.println("Que combustión lleva el coche?(1: GASOLINA 2: DIESEL): ");
                            prop_aux = sc.nextInt();
                            if (prop_aux == 1) {
                                ((Gasolina)vehiculo).setCombustion(Gasolina.Combustion.GASOLINA);
                            } else {
                                ((Gasolina)vehiculo).setCombustion(Gasolina.Combustion.DIESEL);
                            }

                            if (tipo_aux == 1) {
                                vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
                            } else {
                                vehiculo.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
                                System.out.println("Introduce la matricula del coche: ");
                                vehiculo.setMatricula(sc.next());
                            }
                        }
                        break;
                    }

                    try {
                        vehiculo.altaVehiculo();
                        System.out.println("Alta de vehiculo Correcta");
                    } catch (Exception e) {
                        System.out.println("Alta de vehiculo Incorrecta");
                    }
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
                    try {
                        ConexionBD.cerrarConexion();
                        System.out.println("Base de datos cerrada correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al cerrar la base de datos " + e.getMessage());
                    }
                }
                break;
                default:
            }
        } while (op != 0);
    }
}
