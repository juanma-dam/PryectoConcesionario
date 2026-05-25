package com.dam.modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venta {

    private int id;
    private int idVehiculo;
    private String dniCliente;
    private String direccion;
    private String municipio;
    private LocalDate fecha;

    public Venta() {
        this.id = 0;
        this.idVehiculo = 0;
        this.dniCliente = "";
        this.direccion = "";
        this.municipio = "";
        this.fecha = LocalDate.now();
    }

    public Venta(int id) {
        this.id = id;
        this.idVehiculo = 0;
        this.dniCliente = "";
        this.direccion = "";
        this.municipio = "";
        this.fecha = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getFecha() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean existeVenta() throws Exception{
        String sql = "select * from ventas where idVenta = ?";
        try(PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new Exception("Error en el existeVenta!!!");
        }
    }

    public void altaVenta() throws Exception{
       if(existeVenta()) {
           throw new Exception("Existe la venta con el id: " + id);
       }
       Vehiculo vehiculo = new Vehiculo();
       vehiculo.setId(idVehiculo);
       if (!vehiculo.existeVehiculo()){
           throw new Exception("No existe el vehiculo con el id: " + idVehiculo);
       }
       Cliente cliente = new Cliente();
       cliente.setDni(dniCliente);
       if (!cliente.existeCliente()){
           throw new Exception("No existe el cliente con el dni: " + dniCliente);
       }
        String sql = "insert into ventas values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setInt(2, idVehiculo);
            pst.setString(3, dniCliente);
            pst.setString(4, direccion);
            pst.setString(5, municipio);
            pst.setDate(6, Date.valueOf(fecha));

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en el altaVenta!!!");
        }
    }

    public static void listadoVentas(List<Venta> ventas) throws Exception{
        String sql = "select * from ventas ORDER BY idVenta";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
        ResultSet rs = pst.executeQuery();
        VentaListado venta;
        while(rs.next()) {
            venta = new VentaListado();

            venta.setId(rs.getInt("id"));
            venta.setIdVehiculo(rs.getInt("idVehiculo"));
            venta.setDniCliente(rs.getString("dniCliente"));
            venta.setDireccion(rs.getString("direccion"));
            venta.setMunicipio(rs.getString("municipio"));
            venta.setFecha(rs.getDate("fecha").toString());

            ventas.add(venta);
        }

        } catch (SQLException e) {
            throw new Exception("Error en el listadoVenta!!!");
        }
    }

    public Vehiculo getVehiculo(List<Vehiculo> vehiculos) throws Exception{

        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getId() == idVehiculo) {
                return vehiculo;
            }
        }
        return null;
    }

    public Cliente getCliente(List<Cliente> clientes) throws Exception{
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dniCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public static class VentaListado extends Venta {
        private String marca;
        private String modelo;
        private String nombre;
        private double precio;

        public VentaListado() {
            marca = "";
            modelo = "";
            nombre = "";
            precio = 0;
        }

        public VentaListado(int id) {
            super(id);
            this.marca = "";
            this.modelo = "";
            this.nombre = "";
            this.precio = 0;
        }
    }
}
