package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Electrico extends Vehiculo{

    private int autonomia;

    public Electrico() {
        super();
        autonomia = 0;
    }

    public Electrico(int id) {
        super(id);
        this.autonomia = 0;
    }

    // Constructor para rellenar los campos
    public Electrico(int id, String marca, String modelo, double precio, int anio, int autonomia) {
        super(id, marca, modelo, precio, anio);
        this.autonomia = autonomia;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }


    @Override
    public void altaVehiculo() throws Exception {

        super.altaVehiculo();

        String sql = "INSERT INTO Vehiculos(id, marca, modelo, precio, tipo, matricula, anio, autonomia) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, marca);
            pst.setString(3, modelo);
            pst.setDouble(4, precio);
            pst.setString(5, tipo.toString());
            pst.setString(6, matricula);
            pst.setInt(7, anio);
            pst.setInt(8, autonomia);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en altaVehiculo()", e);
        }
    }

}
