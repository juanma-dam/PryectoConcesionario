package com.dam.modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @BeforeEach
    void setUp() throws Exception {
    ConexionBD.abrirConexion();
    }
    @AfterEach
    void tearDown() throws Exception {
        ConexionBD.cerrarConexion();
    }
    @Test
    void existeCliente() throws Exception{
        Cliente cliente = new Cliente();
        //CASO 1: COMPRUEBA DNI DE UN CLIENTE QUE NO EXISTE PARA DAR FALSE EN EXISTENCIA
        cliente.setDni("11111111A");
        assertFalse(cliente.existeCliente());
        //CASO 2: COMPRUEBA QUE NO HAY CLIENTE CON DNI VACIO EN LA BD
        cliente.setDni("");
        assertFalse(cliente.existeCliente());
        //CASO 3: COMPRUEBA QUE UN CLIENTE DADO DE ALTA EXISTE EN LA BD
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("111111111");
        cliente.altaCliente();
        assertEquals(true, cliente.existeCliente());
        //LIMPIAR LA TABLA DE LA BD
        cliente.bajaCliente();

    }
    @Test
    void altaCliente() throws Exception{
        //CASO 1: ALTA CORRECTA
        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("111111111");
        cliente.altaCliente();
        assertEquals(true, cliente.existeCliente());
        //CASO 2: ALTA INCORRECTA POR CLIENTE YA EXISTENTE
        cliente.setDni("11111111A");
        cliente.setNombre("Pepe");
        cliente.setTelefono("111111111");
        assertThrows(Exception.class, () -> cliente.altaCliente());
        //CASO 3: ALTA INCORRECTA POR FALTA DE RELLENAR EL NUMERO
        Cliente cliente1 = new Cliente();
        cliente1.setDni("12345678A");
        cliente1.setTelefono("123456789");
        assertThrows(Exception.class, () -> cliente1.altaCliente());
        //LIMPIEZA TABLA
        cliente.bajaCliente();
    }

    @Test
    void bajaCliente() throws Exception{
        //CASO 1: BAJA CORRECTA
        Cliente cliente = new Cliente();
        cliente.setDni("11111111A");
        cliente.setNombre("Manolo");
        cliente.setTelefono("111111111");
        cliente.altaCliente();
        assertDoesNotThrow(() -> cliente.bajaCliente());
        //CASO 2: BAJA INCORRECTA PORQUE EL CLIENTE NO EXISTE
        Cliente cliente1 = new Cliente();
        cliente1.setDni("00000000Z");
        assertThrows(Exception.class, () -> cliente1.bajaCliente());
        //CASO 3: BAJA INCORRECTA POR DNI VACIO
        Cliente cliente2 = new Cliente();
        cliente2.setDni("");
        assertThrows(Exception.class, () -> cliente2.bajaCliente());
    }

    @Test
    void listadoClientes() throws Exception {
        //CASO 1: LISTA NO ESTA VACIA AL DAR DE ALTA UN CLIENTE
        Cliente cliente = new Cliente();
        cliente.setDni("22222222B");
        cliente.setNombre("Pepa");
        cliente.setTelefono("987654321");
        cliente.altaCliente();
        List<Cliente> clientes = new ArrayList<>();
        Cliente.listadoClientes(clientes);
        assertEquals(false, clientes.isEmpty());
        clientes.clear();
        //CASO 2: LISTA TIENE EL NUMERO CORRECTO DE CLIENTES
        Cliente cliente2 = new Cliente();
        cliente2.setDni("33333333C");
        cliente2.setNombre("Ivan");
        cliente2.setTelefono("333333333");
        cliente2.altaCliente();
        Cliente.listadoClientes(clientes);
        assertEquals(2, clientes.size());
        cliente.bajaCliente();
        cliente2.bajaCliente();

    }

}