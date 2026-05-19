package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Vehiculo {

    protected int id;
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected double precio;
    protected int anio;

    public static enum tipo {NUEVO, SEGUNDAMANO};

    public Vehiculo() {
        this.id = 0;
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
        this.anio = 0;
    }

    public Vehiculo(int id) {
        this.id = id;
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
        this.anio = 0;
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

    public boolean existeVehiculo() throws Exception {
        String sql = "SELECT * from Vehiculos where id = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new Exception("Error en existeVehiculo", e);
        }
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void altaVehiculo() throws Exception {

        if (existeVehiculo()) {
            throw new Exception("El vehiculo ya existe");
        }
        String sql = "INSERT INTO Vehiculos VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, matricula);
            pst.setString(3, marca);
            pst.setString(4, modelo);
            pst.setDouble(5, precio);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en altaVehiculo()", e);
        }
    }

    public void bajaVehiculo() throws Exception {

        if (!existeVehiculo()) {
            throw new Exception("El vehiculo no existe");
        }
        String sql = "DELERE FROM Vehiculos where id = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en bajaVehiculo()", e);
        }
    }

    public static void listadoVehiculos(List<Vehiculo> vehiculos) throws Exception {
        String sql = "SELECT * FROM Vehiculos ORDER BY id";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            VehiculoListado vehiculo;
            while (rs.next()) {
                vehiculo = new VehiculoListado();
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setPrecio(rs.getDouble("precio"));
                vehiculo.setAnio(rs.getInt("anio"));
                vehiculo.setElectrificacion(Hibrido.Electrificacion.valueOf(rs.getString("electrificacion")));
                vehiculo.setCombustion(Gasolina.Combustion.valueOf(rs.getString("combustion")));
                vehiculos.add(vehiculo);
            }
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en bajaVehiculo()", e);
        }
    }

    public static class VehiculoListado extends Vehiculo {
        private int autonomia;
        private Gasolina.Combustion combustion;
        private Hibrido.Electrificacion electrificacion;

        public VehiculoListado() {
            super();
            autonomia = 0;
            electrificacion = Hibrido.Electrificacion.ENCHUFABLE;
            combustion = Gasolina.Combustion.GASOLINA;
        }

        public VehiculoListado(int id) {
            super(id);
            this.autonomia = 0;
            this.electrificacion = Hibrido.Electrificacion.ENCHUFABLE;
            this.combustion = Gasolina.Combustion.GASOLINA;
        }

        public int getAutonomia() {
            return autonomia;
        }

        public void setAutonomia(int autonomia) {
            this.autonomia = autonomia;
        }

        public Gasolina.Combustion getCombustion() {
            return combustion;
        }

        public void setCombustion(Gasolina.Combustion combustion) {
            this.combustion = combustion;
        }

        public Hibrido.Electrificacion getElectrificacion() {
            return electrificacion;
        }

        public void setElectrificacion(Hibrido.Electrificacion electrificacion) {
            this.electrificacion = electrificacion;
        }
    }
}