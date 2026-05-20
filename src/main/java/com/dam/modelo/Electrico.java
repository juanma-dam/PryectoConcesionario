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

    public boolean existeVehiculo() throws Exception {
        return super.existeVehiculo();
    }

    public void altaVehiculo() throws Exception {

        super.altaVehiculo();
    }

    public void bajaVehiculo() throws Exception {

        super.bajaVehiculo();
    }
}
