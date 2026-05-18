package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Vehiculo {

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private double precio;

    public enum tipo {NUEVO, SEGUNDAMANO};

    public Vehiculo(int id) {
        this.id = id;
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

//    public boolean existeVehiculo() throws Exception {
//        String sql = "SELECT * from Vehiculos where id = ?";
//        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            throw new Exception("Error en existeVehiculo", e);
//        }
//    }
//
//    public void altaVehiculo() throws Exception {
//
//        if (existeVehiculo()) {
//            throw new Exception("El vehiculo ya existe");
//        }
//        String sql = "INSERT INTO Vehiculos VALUES(?, ?, ?, ?, ?)";
//        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
//            pst.setInt(1, id);
//            pst.setString(2, matricula);
//            pst.setString(3, marca);
//            pst.setString(4, modelo);
//            pst.setDouble(5, precio);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new Exception("Error en altaVehiculo()", e);
//        }
//    }
//
//    public void bajaVehiculo() throws Exception {
//
//        if (!existeVehiculo()) {
//            throw new Exception("El vehiculo no existe");
//        }
//        String sql = "DELERE FROM Vehiculos where id = ?";
//        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
//            pst.setInt(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new Exception("Error en bajaVehiculo()", e);
//        }
//    }
//
//    public static void listadoVehiculos(List<Vehiculo> vehiculos) throws Exception {
//        String sql = "SELECT * FROM Vehiculos ORDER BY id";
//        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
//            ResultSet rs = pst.executeQuery();
//            Vehiculo vehiculo;
//            while (rs.next()) {
//                vehiculo = new Vehiculo();
//                vehiculo.setMatricula(rs.getString("matricula"));
//                vehiculo.setMarca(rs.getString("marca"));
//                vehiculo.setModelo(rs.getString("modelo"));
//                vehiculo.setPrecio(rs.getDouble("precio"));
//            }
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new Exception("Error en bajaVehiculo()", e);
//        }
//    }

}