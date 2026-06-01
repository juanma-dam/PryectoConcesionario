package com.dam.modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {

    @BeforeEach
    void setUp() throws Exception {

        ConexionBD.abrirConexion();

        try(Statement st = ConexionBD.getConexionBD().createStatement()) {
            st.executeUpdate("DELETE FROM VENTAS");
            st.executeUpdate("DELETE FROM CLIENTES");
            st.executeUpdate("DELETE FROM VEHICULOS");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        ConexionBD.cerrarConexion();
    }

    @Test
    void existeVenta() throws Exception {
        //CASO 1: VENTA INEXISTENTE
        Venta venta = new Venta();
        venta.setId(1);

        assertFalse(venta.existeVenta());

        //CASO 2: VENTA EXISTE

        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("123456789");
        cliente.altaCliente();

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        venta.setId(1);
        venta.setIdVehiculo(1);
        venta.setDniCliente("11111111A");
        venta.setDireccion("Calle Mayor");
        venta.setMunicipio("Madrid");

        venta.altaVenta();

        assertTrue(venta.existeVenta());
    }

    @Test
    void altaVenta() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("123456789");
        cliente.altaCliente();

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        // CASO 1: VENTA CORRECTA

        Venta venta = new Venta();
        venta.setId(1);
        venta.setIdVehiculo(1);
        venta.setDniCliente("11111111A");
        venta.setDireccion("Calle Murcia");
        venta.setMunicipio("Madrid");

        assertDoesNotThrow(() -> venta.altaVenta());

        // CASO 2: VENTA INCORRECTA ID DUPLICADO

        Venta venta2 = new Venta();
        venta2.setId(1);
        venta2.setIdVehiculo(1);
        venta2.setDniCliente("11111111A");
        venta2.setDireccion("Calle Cartagena");
        venta2.setMunicipio("Madrid");

        assertThrows(Exception.class, () -> venta2.altaVenta());

        // CASO 3: NUEVA VENTA INCORRECTA, VEHICULO YA VENDIDO

        Venta venta3 = new Venta();
        venta3.setId(2);
        venta3.setIdVehiculo(1);
        venta3.setDniCliente("11111111A");
        venta3.setDireccion("Otra");
        venta3.setMunicipio("Madrid");

        assertThrows(Exception.class, () -> venta3.altaVenta());
    }

    @Test
    void listadoVentas() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("123456789");
        cliente.altaCliente();

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        Venta venta = new Venta();
        venta.setId(1);
        venta.setIdVehiculo(1);
        venta.setDniCliente("11111111A");
        venta.setDireccion("Calle Mayor");
        venta.setMunicipio("Madrid");
        venta.altaVenta();

        List<Venta.VentaListado> ventas = new ArrayList<>();

        Venta.listadoVentas(ventas);

        // CASO 1: lista no vacía

        assertFalse(ventas.isEmpty());

        // CASO 2: tamaño correcto

        assertEquals(1, ventas.size());

    }

    @Test
    void obtenerTipo() throws Exception {

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        Venta venta = new Venta();
        venta.setIdVehiculo(1);

        // CASO 1: vehículo nuevo

        assertTrue(venta.obtenerTipo());

        // CASO 2: vehículo segunda mano

        Gasolina gasolina = new Gasolina();
        gasolina.setId(2);
        gasolina.setMarca("Seat");
        gasolina.setModelo("Ibiza");
        gasolina.setPrecio(12000);
        gasolina.setAnio(2020);
        gasolina.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
        gasolina.setMatricula("5678DEF");
        gasolina.setCombustion(Gasolina.Combustion.GASOLINA);
        gasolina.altaVehiculo();

        venta.setIdVehiculo(2);

        assertFalse(venta.obtenerTipo());

    }

    @Test
    void modificarMatricula() throws Exception {

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        Venta venta = new Venta();
        venta.setIdVehiculo(1);

        // CASO 1: matrícula válida

        assertDoesNotThrow(() ->
                venta.modificarMatricula("9999ZZZ"));

        // CASO 2: matrícula inválida

        assertThrows(Exception.class, () ->
                venta.modificarMatricula("ABC"));

    }

    @Test
    void verificarEstado() throws Exception {

        Electrico vehiculo = new Electrico();
        vehiculo.setId(1);
        vehiculo.setMarca("Tesla");
        vehiculo.setModelo("Model3");
        vehiculo.setPrecio(35000);
        vehiculo.setAnio(2024);
        vehiculo.setAutonomia(500);
        vehiculo.setTipo(Vehiculo.Tipo.NUEVO);
        vehiculo.setMatricula("1234ABC");
        vehiculo.altaVehiculo();

        Venta venta = new Venta();
        venta.setIdVehiculo(1);

        // CASO 1: no vendido

        assertFalse(venta.verificarEstado());

        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("123456789");
        cliente.altaCliente();

        venta.setId(1);
        venta.setDniCliente("11111111A");
        venta.setDireccion("Calle Mayor");
        venta.setMunicipio("Madrid");

        venta.altaVenta();

        // CASO 2: vendido

        assertTrue(venta.verificarEstado());

    }
}