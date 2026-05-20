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
        this.electrificacion = Electrificacion.ENCHUFABLE;
    }

    // Constructor para rellenar los campos

    public Hibrido(int id, String marca, String modelo, double precio, int anio) {
        super(id, marca, modelo, precio, anio);
        this.electrificacion = Electrificacion.ENCHUFABLE;
    }

    public Electrificacion getElectrificacion() {
        return electrificacion;
    }

    public void setElectrificacion(Electrificacion electrificacion) {
        this.electrificacion = electrificacion;
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
