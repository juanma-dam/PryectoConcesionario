package com.dam.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * CLASE CONEXIONBD<br>
 * Métodos para el control de la base de datos
 *
 * @author Iván Álvarez
 * @author Juan Manuel Sanabria Mamani
 * @author Alfonso Marín Navarro
 * @version 1.0
 * @since 27/05/2026
 */
public class ConexionBD {

    private static Connection conexionBD;

    public ConexionBD() {
        conexionBD = null;
    }

    public static Connection getConexionBD() {
        return conexionBD;
    }

    private static void crearTablas() throws Exception {
        try (Statement st = conexionBD.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Vehiculos ("
                    + "id INT PRIMARY KEY,"
                    + "marca VARCHAR(15)NOT NULL,"
                    + "modelo VARCHAR(20)NOT NULL,"
                    + "precio DECIMAL(10,2),"
                    + "tipo VARCHAR(20) NOT NULL,"
                    + "matricula VARCHAR(10),"
                    + "anio INT(5),"
                    + "autonomia INT(10) NULL,"
                    + "electrificacion VARCHAR(25) NULL,"
                    + "combustion VARCHAR(25) NULL,"
                    + "vendido BOOLEAN NOT NULL,"
                    + "CONSTRAINT uk_matricula UNIQUE (matricula))";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Clientes ("
                    + "dni VARCHAR(10) PRIMARY KEY,"
                    + "nombre VARCHAR(20) NOT NULL,"
                    + "telefono VARCHAR(10),"
                    + "CONSTRAINT uk_dni UNIQUE (dni))";
            st.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Ventas ("
                    + "idVenta INT PRIMARY KEY,"
                    + "idVehiculo INT NOT NULL,"
                    + "dniCliente VARCHAR(10) NOT NULL,"
                    + "direccion VARCHAR(30) NOT NULL,"
                    + "municipio VARCHAR(25) NOT NULL,"
                    + "fecha DATE NOT NULL,"
                    + "CONSTRAINT fk_vehiculo_venta FOREIGN KEY (idVehiculo)"
                    + "REFERENCES Vehiculos (id),"
                    + "CONSTRAINT fk_cliente_venta FOREIGN KEY (dniCliente)"
                    + "REFERENCES Clientes (dni))";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new Exception("Error en crearTablas " + e.getMessage());
        }
    }

    /**
     * Método para abrir conexion con la base de datos
     * @throws Exception Control de errores
     */
    public static void abrirConexion() throws Exception {

        try (FileInputStream fis = new FileInputStream("dbproperties.txt")) {
            Properties props = new Properties();
            props.load(fis);
            conexionBD = DriverManager.getConnection(
                    props.getProperty("mysql.url"),
                    props.getProperty("mysql.username"),
                    props.getProperty("mysql.password"));
            crearTablas();
        } catch (IOException e) {
            throw new Exception("Error en abrirConexion()!! (Propiedades)");
        } catch (SQLException e) {
            throw new Exception("Error en abrirConexion()!!");
        }
    }

    /**
     * Método para cerrar conexion con la base de datos
     * @throws Exception Control de errores
     */
    public static void cerrarConexion() throws Exception {

        try {
            if (conexionBD != null) {
                conexionBD.close();
            }
        } catch (SQLException e) {
            throw new Exception("Error en cerrarConexion");
        }
    }
}
