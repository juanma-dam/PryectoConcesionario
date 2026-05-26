package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Hibrido extends Vehiculo{

    public static enum Electrificacion {ENCHUFABLE, NO_ENCHUFABLE};

    public Electrificacion electrificacion;

    public Hibrido() {
        electrificacion = Electrificacion.ENCHUFABLE;
    }

    public Hibrido(int id) {
        super(id);
        this.electrificacion = null;
    }

    // Constructor para rellenar los campos

    public Hibrido(int id, String marca, String modelo, double precio, int anio) {
        super(id, marca, modelo, precio, anio);
        this.electrificacion = null;
    }

    public Electrificacion getElectrificacion() {
        return electrificacion;
    }

    public void setElectrificacion(Electrificacion electrificacion) {
        this.electrificacion = electrificacion;
    }

    @Override
    public void altaVehiculo() throws Exception {

        super.altaVehiculo();
        String sql = "INSERT INTO Vehiculos(id, marca, modelo, precio, tipo, matricula, anio, electrificacion, vendido) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, marca);
            pst.setString(3, modelo);
            pst.setDouble(4, precio);
            pst.setString(5, tipo.toString());
            pst.setString(6, matricula);
            pst.setInt(7, anio);
            pst.setString(8, electrificacion.toString());
            pst.setInt(9, 0);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en altaVehiculo()", e);
        }
    }

}
