package com.dam.modelo;

import com.dam.vista.Auxiliar;

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
       if (verificarEstado()) {
           throw new Exception("El coche ha sido vendido");
       }
        String sql = "insert into ventas values (?, ?, ?, ?, ?, ?)";
       String sql2 = "update vehiculos set vendido = ? where id = ?";
        try(PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql); PreparedStatement pst2 = ConexionBD.getConexionBD().prepareStatement(sql2)) {
            ConexionBD.getConexionBD().setAutoCommit(false);
            pst.setInt(1, id);
            pst.setInt(2, idVehiculo);
            pst.setString(3, dniCliente);
            pst.setString(4, direccion);
            pst.setString(5, municipio);
            pst.setDate(6, Date.valueOf(fecha));

            pst.executeUpdate();

            pst2.setInt(1, 1);
            pst2.setInt(2, idVehiculo);
            pst2.executeUpdate();
            ConexionBD.getConexionBD().commit();
        } catch (SQLException e) {
            ConexionBD.getConexionBD().rollback();
            throw new Exception("Error en el altaVenta!!!");
        } finally {
            ConexionBD.getConexionBD().setAutoCommit(true);
        }
    }

    public static void listadoVentas(List<Venta.VentaListado> ventas) throws Exception{
        String sql = "select idVenta, idVehiculo, dniCliente, direccion, municipio, fecha, marca, modelo, matricula, precio, nombre from vehiculos v join ventas on id = idVehiculo join clientes on dni = dniCliente ORDER BY idVenta";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
        ResultSet rs = pst.executeQuery();
        VentaListado venta;
        while(rs.next()) {
            venta = new VentaListado();

            venta.setId(rs.getInt("idVenta"));
            venta.setIdVehiculo(rs.getInt("idVehiculo"));
            venta.setDniCliente(rs.getString("dniCliente"));
            venta.setDireccion(rs.getString("direccion"));
            venta.setMunicipio(rs.getString("municipio"));
            venta.setFecha(rs.getDate("fecha").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            venta.setMarca(rs.getString("marca"));
            venta.setModelo(rs.getString("modelo"));
            venta.setMatricula(rs.getString("matricula"));
            venta.setPrecio(rs.getDouble("precio"));
            venta.setNombre(rs.getString("nombre"));

            ventas.add(venta);
        }

        } catch (SQLException e) {
            throw new Exception("Error en el listadoVenta!!!");
        }
    }

    public boolean obtenerMatricula() throws Exception{

        String sql = "select tipo from vehiculos where id = ?";
        String tipo;
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {

            pst.setInt(1, idVehiculo);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tipo = rs.getString("tipo");
                if (tipo.equals(Vehiculo.Tipo.NUEVO.toString())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error en el getMatricula!!!");
        }

        return false;
    }

    public void modificarMatricula(String matricula) throws Exception {
        if (!Auxiliar.verificarMatricula(matricula)) {
            throw new Exception("Matricula incorrecta!!!");
        }
        String sql = "update vehiculos set matricula = ? where id = ?";
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setString(1, matricula);
            pst.setInt(2, idVehiculo);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en el modificarMatricula!!!");
        }
    }

    public boolean verificarEstado() throws Exception {
        String sql = "select vendido from vehiculos where id = ?";
        int vendido;
        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
            pst.setInt(1, idVehiculo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                vendido = rs.getInt("vendido");
                if (vendido == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new Exception("Error en el verificarEstado!!!");
        }
        return false;
    }

    public static class VentaListado extends Venta {
        private String marca;
        private String modelo;
        private String matricula;
        private String nombre;
        private double precio;

        public VentaListado() {
            super();
            marca = "";
            modelo = "";
            nombre = "";
            precio = 0;
            matricula = "";
        }

        public VentaListado(int id) {
            super(id);
            this.marca = "";
            this.modelo = "";
            this.nombre = "";
            this.precio = 0;
            this.matricula = "";
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

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
    }
}
