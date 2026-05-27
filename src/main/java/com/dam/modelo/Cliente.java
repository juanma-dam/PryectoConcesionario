package com.dam.modelo;

import com.dam.modelo.ConexionBD;
import com.dam.vista.Auxiliar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CLASE CLIENTE<br>
 * Modelo de datos y métodos CRUD
 *
 * @author Iván Álvarez
 * @author Juan Manuel Sanabria Mamani
 * @author Alfonso Marín Navarro
 * @version 1.0
 * @since 27/05/2026
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String telefono;

    /**
     * Constructor básico
     */
    public Cliente() {
        dni = "";
        nombre = "";
        telefono = "";
    }

    /**
     * Constructor sobrecargado por clave primaria
     * @param dni String con el dni del alumno
     */
    public Cliente(String dni) {
        this.dni = dni;
        this.nombre = "";
        this.telefono = "";
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean existeCliente() throws Exception {
        String sql = "SELECT * FROM clientes where dni = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setString(1, dni);
            return pst.executeQuery().next();
        } catch (SQLException e) {
            throw new Exception("Error en existeCliente()!!", e);
        }
    }

    /**
     * Método de alta de un cliente en una base de datos<br>
     * Validaciones: {@link com.dam.vista.Auxiliar#verificarDni(String) DNI}
     * @throws Exception Control de errores
     */
    public void altaCliente() throws Exception {
        if (existeCliente()) {
            throw new Exception("El cliente ya existe!!");
        }
        if (!Auxiliar.verificarDni(dni)) {
            throw new Exception("El dni es incorrecto");
        }
        String sql = "INSERT INTO clientes VALUES (?, ?, ?)";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setString(1, dni);
            pst.setString(2, nombre);
            pst.setString(3, telefono);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en altaCliente()!!", e);
        }
    }

    /**
     * Método de baja de un cliente en una base de datos<br>
     * @throws Exception Control de errores
     */
    public void bajaCliente() throws Exception {
        if (!existeCliente()) {
            throw new Exception("El cliente no existe!!");
        }
        String sql = "DELETE FROM clientes WHERE dni = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setString(1, dni);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en bajaCliente()!!", e);
        }
    }

    /**
     * Método para obtener un listado de todos los clientes dados de alta<br>
     * @param clientes Lista donde añadir a los clientes de la base de datos
     * @throws Exception Control de errores
     */
    public static void listadoClientes(List<Cliente> clientes) throws Exception {
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new Exception("Error en listadoClientes()!!", e);
        }
    }
}
