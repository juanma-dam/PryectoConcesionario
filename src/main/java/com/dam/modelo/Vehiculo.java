package com.dam.modelo;

import com.dam.vista.Auxiliar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CLASE VEHICULO<br>
 * Modelo de datos y métodos CRUD
 *
 * @author Iván Álvarez
 * @author Juan Manuel Sanabria Mamani
 * @author Alfonso Marín Navarro
 * @version 1.0
 * @since 27/05/2026
 */
public class Vehiculo {

    protected int id;
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected double precio;
    protected int anio;
    public Tipo tipo;

    public static enum Tipo {NUEVO, SEGUNDAMANO};

    public Vehiculo() {
        this.id = 0;
        this.matricula = null;
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
        this.anio = 0;
        this.tipo = Tipo.SEGUNDAMANO;
    }

    public Vehiculo(int id) {
        this.id = id;
        this.matricula = null;
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
        this.anio = 0;
        this.tipo = Tipo.SEGUNDAMANO;
   }

   // Constructor para rellenar los campos

    public Vehiculo(int id, String marca, String modelo, double precio, int anio) {
        this.id = id;
        this.matricula = null;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.anio = anio;
        this.tipo = Tipo.SEGUNDAMANO;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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

    public void altaVehiculo() throws Exception {

        if (existeVehiculo()) {
            throw new Exception("El vehiculo ya existe");
        }
        if (tipo == Tipo.SEGUNDAMANO) {
            if (!Auxiliar.verificarMatricula(matricula)) {
                throw new Exception("Matricula incorrecta");
            }
        }

    }

    public void bajaVehiculo() throws Exception {

        if (!existeVehiculo()) {
            throw new Exception("El vehiculo no existe");
        }
        String sql = "DELETE FROM Vehiculos where id = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en bajaVehiculo()", e);
        }
    }

    public static void listadoVehiculos(List<Vehiculo.VehiculoListado> vehiculos) throws Exception {
        String sql = "SELECT * FROM Vehiculos ORDER BY id";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            VehiculoListado vehiculo;
            while (rs.next()) {
                vehiculo = new VehiculoListado();
                vehiculo.setId(rs.getInt("id"));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setPrecio(rs.getDouble("precio"));
                vehiculo.setAnio(rs.getInt("anio"));
                if (rs.getString("electrificacion") != null) {
                    vehiculo.setElectrificacion(Hibrido.Electrificacion.valueOf(rs.getString("electrificacion")));
                }
                if (rs.getString("combustion") != null) {
                    vehiculo.setCombustion(Gasolina.Combustion.valueOf(rs.getString("combustion")));
                }
                vehiculo.setAutonomia(rs.getInt("autonomia"));
                vehiculo.setVendido(rs.getBoolean("vendido"));
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            throw new Exception("Error en bajaVehiculo()", e);
        }
    }

    /**
     * Clase interna para el listado de vehículos
     * {@inheritDoc}
     * @see com.dam.modelo.Vehiculo#listadoVehiculos(List)
     */
    public static class VehiculoListado extends Vehiculo {
        private Integer autonomia;
        private Gasolina.Combustion combustion;
        private Hibrido.Electrificacion electrificacion;
        private boolean vendido;

        public VehiculoListado() {
            super();
            autonomia = 0;
            electrificacion = null;
            combustion = null;
            vendido = false;

        }

        public VehiculoListado(int id) {
            super(id);
            this.autonomia = 0;
            this.electrificacion = null;
            this.combustion = null;
            this.vendido = false;
        }

        public Integer getAutonomia() {
            return autonomia;
        }

        public void setAutonomia(Integer autonomia) {
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

        public boolean isVendido() {
            return vendido;
        }

        public void setVendido(boolean vendido) {
            this.vendido = vendido;
        }
    }
}