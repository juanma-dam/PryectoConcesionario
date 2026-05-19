package com.dam.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Gasolina extends Vehiculo{

    public static enum Combustion {GASOLINA, DIESEL};

    private Combustion combustion;

    public Gasolina() {
        super();
        this.combustion = Combustion.GASOLINA;
    }

    public Gasolina(int id) {
        super(id);
    }

    public Combustion getCombustion() {
        return combustion;
    }

    public void setCombustion(Combustion combustion) {
        this.combustion = combustion;
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
