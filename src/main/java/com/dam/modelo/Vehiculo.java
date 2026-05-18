package com.dam.modelo;

public abstract class Vehiculo {

    protected int id;
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected double precio;

    public static enum tipo {NUEVO, SEGUNDAMANO};

    public Vehiculo() {
        this.id = 0;
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
    }

    public Vehiculo(int id) {
        this.id = id;
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.precio = 0.0;
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

    public abstract boolean existeVehiculo() throws Exception;

    public abstract void altaVehiculo() throws Exception;

    public abstract void bajaVehiculo() throws Exception;

//    public static void listadoVehiculos(List<Vehiculo> vehiculos) throws Exception {
//        String sql = "SELECT * FROM Vehiculos ORDER BY id";
//        try (PreparedStatement pst = ConexionBD.getConexionBD().prepareStatement(sql)) {
//            ResultSet rs = pst.executeQuery();
//            Vehiculo vehiculo;
//            while (rs.next()) {
//                vehiculo = new Vehiculo();
//                vehiculo.setMatricula(rs.getString("matricula"));
//                vehiculo.setMarca(rs.getString("marca"));
//                vehiculo.setModelo(rs.getString("modelo"));
//                vehiculo.setPrecio(rs.getDouble("precio"));
//            }
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            throw new Exception("Error en bajaVehiculo()", e);
//        }
//    }

}