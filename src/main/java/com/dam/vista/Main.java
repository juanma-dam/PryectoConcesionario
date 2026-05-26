package com.dam.vista;


import com.dam.modelo.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
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
        List<Vehiculo.VehiculoListado> vehiculos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Venta.VentaListado> ventas = new ArrayList<>();

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
                    System.out.println("Que tipo de propulsion lleva el coche? (1:electrico, 2:hibrido, 3: gasolina): ");
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
                    System.out.println("Que tipo de coche es (1: nuevo 2: segunda mano): ");
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
                        System.out.println("Alta de vehiculo Incorrecta " + e.getMessage());
                    }
                }
                break;
                case 2: // Baja de Vehiculos
                {
                    Vehiculo vehiculo = new Vehiculo();

                    System.out.println("Introduce el id del coche: ");
                    vehiculo.setId(sc.nextInt());

                    try {
                        vehiculo.bajaVehiculo();
                        System.out.println("Baja de vehiculo Correcta");
                    } catch (Exception e) {
                        System.out.println("Baja de vehiculo Incorrecta " + e.getMessage());
                    }
                }
                break;
                case 3: // Alta de Clientes
                {
                    Cliente cliente = new Cliente();

                    System.out.println("Introduce el dni del cliente: ");
                    cliente.setDni(sc.next());
                    System.out.println("Introduce el nombre del cliente: ");
                    cliente.setNombre(sc.next());
                    System.out.println("Introduce el telefono del cliente: ");
                    cliente.setTelefono(sc.next());

                    try {
                        cliente.altaCliente();
                        System.out.println("Alta de cliente Correcta");
                    } catch (Exception e) {
                        System.out.println("Error en el alta de clientes " + e.getMessage());
                    }
                }
                break;
                case 4: // Baja de Clientes
                {
                    Cliente cliente = new Cliente();
                    System.out.println("Introduce el dni del cliente: ");
                    cliente.setDni(sc.next());

                    try {
                        cliente.bajaCliente();
                        System.out.println("Baja de cliente Correcta");
                    } catch (Exception e) {
                        System.out.println("Error en el baja de clientes " + e.getMessage());
                    }
                }
                break;
                case 5: // Nueva Venta
                {
                    Venta venta = new Venta();


                    System.out.println("Introduce el id de la venta: ");
                    venta.setId(sc.nextInt());
                    System.out.println("Introduce el id del vehiculo: ");
                    venta.setIdVehiculo(sc.nextInt());
                    try {
                        if (venta.obtenerMatricula()) {
                            System.out.println("Introduce la matricula del vehiculo: ");
                            String matricula = sc.next();
                            venta.modificarMatricula(matricula);
                        }
                    } catch (Exception e) {
                        System.out.println("Error en obtener la matricula del vehiculo " + e.getMessage());
                    }
                    System.out.println("Introduce el dni del cliente: ");
                    venta.setDniCliente(sc.next());
                    System.out.println("Introduce la direccion del cliente: ");
                    venta.setDireccion(sc.next());
                    System.out.println("Introduce el municipio del cliente: ");
                    venta.setMunicipio(sc.next());

                    try {
                        venta.altaVenta();
                        System.out.println("Alta de venta Correcta");
                    } catch (Exception e) {
                        System.out.println("Error en el alta de ventas " + e.getMessage());
                    }


                }
                break;
                case 6: // Listado de Vehiculos
                {

                    System.out.println("LISTADO DE VEHICULOS");
                    System.out.println("-------------------------");
                    System.out.println();
                    try {
                        Vehiculo.listadoVehiculos(vehiculos);
                        for (Vehiculo.VehiculoListado vehiculo : vehiculos) {

                            System.out.printf("ID: %d MARCA: %s MODELO: %s PRECIO: %.2f TIPO: %s MATRICULA: %s ANIO: %d AUTONOMIA: %d ELECTRIFICACION: %s COMBUSTION: %s VENDIDO: %s\n", vehiculo.getId(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getPrecio(),
                                    vehiculo.getTipo().toString(), (vehiculo.getMatricula() == null) ? "" : vehiculo.getMatricula(), vehiculo.getAnio(), (vehiculo.getAutonomia() == null) ? 0: vehiculo.getAutonomia(), (vehiculo.getElectrificacion() == null) ? "" : vehiculo.getElectrificacion(), (vehiculo.getCombustion() == null) ? "" : vehiculo.getCombustion(),
                                    (vehiculo.isVendido()) ? "Si" : "No");
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el listado de vehiculos " + e.getMessage());
                    }

                    vehiculos.clear();

                }
                break;
                case 7: // Listado de Clientes
                {
                    System.out.println("LISTADO DE CLIENTES");
                    System.out.println("-------------------------");
                    System.out.println();
                    try {
                        Cliente.listadoClientes(clientes);
                        for (Cliente cliente : clientes) {

                            System.out.printf("DNI: %s NOMBRE: %s TELEFONO: %s\n", cliente.getDni(), cliente.getNombre(), cliente.getTelefono());
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el listado de clientes " + e.getMessage());
                    }
                    clientes.clear();
                }
                break;
                case 8: // Registro de Ventas
                {
                    System.out.println("REGISTRO DE VENTAS");
                    System.out.println("-------------------------");
                    System.out.println();
                    try {
                        Venta.listadoVentas(ventas);
                        for (Venta.VentaListado venta : ventas) {
                            System.out.printf("ID: %d FECHA: %s IDVEHICULO: %d MARCA: %s MODELO: %s MATRICULA: %s PRECIO: %.2f DNI: %s NOMBRE: %s DIRECCION: %s MUNICIPIO: %s\n", venta.getId()
                            , venta.getFecha(), venta.getIdVehiculo(), venta.getMarca(), venta.getModelo(), venta.getMatricula(), venta.getPrecio(), venta.getDniCliente(), venta.getNombre(), venta.getDireccion(), venta.getMunicipio());
                        }
                    } catch (Exception e) {
                        System.out.println("Error en el registro de ventas " + e.getMessage());
                    }
                    ventas.clear();
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
