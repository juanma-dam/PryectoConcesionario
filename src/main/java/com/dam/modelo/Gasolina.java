package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * CLASE GASOLINA<br>
 * Para vehículos de Combustion
 * {@inheritDoc}
 * @see com.dam.modelo.Vehiculo Clase vehículo
 */
public class Gasolina extends Vehiculo{

    public static enum Combustion {GASOLINA, DIESEL};

    public Combustion combustion;

    public Gasolina() {
        super();
        this.combustion = null;
    }

    // Constructor para rellenar los campos
    public Gasolina(int id, String marca, String modelo, double precio, int anio) {
        super(id, marca, modelo, precio, anio);
        this.combustion = null;
    }

    public Gasolina(int id) {
        super(id);
        this.combustion = null;
    }

    public Combustion getCombustion() {
        return combustion;
    }

    public void setCombustion(Combustion combustion) {
        this.combustion = combustion;
    }

    @Override
    public void altaVehiculo() throws Exception {

        super.altaVehiculo();
        String sql = "INSERT INTO Vehiculos(id, marca, modelo, precio, tipo, matricula, anio, combustion, vendido) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, marca);
            pst.setString(3, modelo);
            pst.setDouble(4, precio);
            pst.setString(5, tipo.toString());
            pst.setString(6, matricula);
            pst.setInt(7, anio);
            pst.setString(8, combustion.toString());
            pst.setInt(9, 0);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en altaVehiculo()", e);
        }
    }

}
