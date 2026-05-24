package com.dam.modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoTest {

    @BeforeEach
    void setUp() throws Exception{
        ConexionBD.abrirConexion();
    }

    @AfterEach
    void tearDown() throws Exception{
        ConexionBD.cerrarConexion();
    }

    @Test
    void existeVehiculo() throws Exception{
        //CASO 1: VEHICULO QUE NO EXISTE DEVUELVE FALSE
        Electrico electrico = new Electrico();
        electrico.setId(1);
        electrico.setMarca("Toyota");
        electrico.setModelo("Corolla");
        electrico.setPrecio(25000);
        electrico.setAnio(2026);
        electrico.setAutonomia(500);
        electrico.setTipo(Vehiculo.Tipo.NUEVO);
        assertFalse(electrico.existeVehiculo());
        //CASO 2: VEHICULO DADO DE ALTA DEVUELVE TRUE
        electrico.altaVehiculo();
        assertEquals(true, electrico.existeVehiculo());
        //CASO 3: VEHICULO CON ID 0 DEVUELVE FALSE
        Electrico electrico1 = new Electrico();
        electrico1.setId(0);
        electrico1.setMarca("Toyota");
        electrico1.setModelo("Prius");
        electrico1.setPrecio(29000);
        electrico1.setAnio(2020);
        electrico1.setAutonomia(450);
        electrico1.setTipo(Vehiculo.Tipo.NUEVO);
        assertFalse(electrico1.existeVehiculo());
        //LIMPIEZA
        electrico.bajaVehiculo();
    }

    @Test
    void altaVehiculo() throws Exception{
        //CASO 1: ALTA CORRECTA
        Electrico electrico = new Electrico();
        electrico.setId(4);
        electrico.setMarca("Tesla");
        electrico.setModelo("Cybertruck");
        electrico.setPrecio(50000);
        electrico.setAnio(2022);
        electrico.setAutonomia(600);
        electrico.setTipo(Vehiculo.Tipo.NUEVO);
        assertDoesNotThrow(() -> electrico.altaVehiculo());
        //CASO 2: ALTA INCORRECTA PORQUE VEHICULO YA EXISTE
        electrico.setId(4);
        electrico.setMarca("Tesla");
        electrico.setModelo("Cybertruck");
        electrico.setPrecio(50000);
        electrico.setAnio(2022);
        electrico.setAutonomia(600);
        electrico.setTipo(Vehiculo.Tipo.NUEVO);
        assertThrows(Exception.class, () -> electrico.altaVehiculo());
        electrico.bajaVehiculo();
        //CASO 3: ALTA INCORRECTA PORQUE FALTA LA MARCA
        electrico.setId(5);
        electrico.setMarca(null);
        electrico.setModelo("Model 3");
        electrico.setPrecio(40000);
        electrico.setAnio(2020);
        electrico.setAutonomia(500);
        electrico.setTipo(Vehiculo.Tipo.NUEVO);
        assertThrows(Exception.class, () -> electrico.altaVehiculo());
    }

    @Test
    void bajaVehiculo() throws Exception{
    //CASO 1: BAJA CORRECTA
        Electrico electrico = new Electrico();
        electrico.setId(6);
        electrico.setMarca("Tesla");
        electrico.setModelo("Model 4");
        electrico.setPrecio(45000);
        electrico.setAnio(2022);
        electrico.setAutonomia(550);
        electrico.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
        electrico.altaVehiculo();
        assertDoesNotThrow(() -> electrico.bajaVehiculo());
        //CASO 2: BAJA INCORRECTA PORQUE NO EXISTE VEHICULO CON EL ID USADO
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(99);
        assertThrows(Exception.class, () -> vehiculo.bajaVehiculo());
        //CASO 3: BAJA INCORRECTA PORQUE EL VEHICULO YA FUE DADO DE BAJA
        Electrico electrico1 = new Electrico();
        electrico1.setId(6);
        electrico1.setMarca("Tesla");
        electrico1.setModelo("Model 4");
        electrico1.setPrecio(45000);
        electrico1.setAnio(2022);
        electrico1.setAutonomia(550);
        electrico1.setTipo(Vehiculo.Tipo.SEGUNDAMANO);
        electrico1.altaVehiculo();
        electrico1.bajaVehiculo();
        assertThrows(Exception.class, () -> electrico1.bajaVehiculo());

    }

    @Test
    void listadoVehiculos() throws Exception{
        // CASO 1: LISTA NO ESTA VACIA DESPUES DE UN ALTA DE VEHICULO
        Electrico electrico = new Electrico();
        electrico.setId(7);
        electrico.setMarca("Tesla");
        electrico.setModelo("Model 6");
        electrico.setPrecio(55000);
        electrico.setAnio(2026);
        electrico.setAutonomia(650);
        electrico.setTipo(Vehiculo.Tipo.NUEVO);
        electrico.altaVehiculo();
        List<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo.listadoVehiculos(vehiculos);
        assertEquals(false, vehiculos.isEmpty());
        //CASO 2: LISTA TIENE EL NUMERO CORRECTO DE VEHICULOS
        //Alta hibrido
        Hibrido hibrido = new Hibrido();
        hibrido.setId(8);
        hibrido.setMarca("Toyota");
        hibrido.setModelo("Yaris");
        hibrido.setPrecio(250000);
        hibrido.setAnio(2021);
        hibrido.setTipo(Vehiculo.Tipo.NUEVO);
        hibrido.setElectrificacion(Hibrido.Electrificacion.ENCHUFABLE);
        hibrido.altaVehiculo();
        //Alta gasolina
        Gasolina gasolina = new Gasolina();
        gasolina.setId(9);
        gasolina.setMarca("Seat");
        gasolina.setModelo("Ibiza");
        gasolina.setPrecio(15000);
        gasolina.setAnio(2020);
        gasolina.setTipo(Vehiculo.Tipo.NUEVO);
        gasolina.setCombustion(Gasolina.Combustion.GASOLINA);
        gasolina.altaVehiculo();
        vehiculos.clear();
        Vehiculo.listadoVehiculos(vehiculos);
        assertEquals(3, vehiculos.size());
        electrico.bajaVehiculo();
        hibrido.bajaVehiculo();
        gasolina.bajaVehiculo();
    }
}