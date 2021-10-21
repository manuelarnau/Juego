package Aad.manu.datos;

import Aad.manu.clases.Personaje;

import java.sql.*;
import java.util.ArrayList;

public class AccesoADatos {

    Connection conexion = null;
    String url = "jdbc:mysql://localhost:3306/juego";
    String user = "root";
    String passwd = "";

    private void crearConexion() {
        System.out.println("Se ha accedido a la base de datos");
        try {
            if (conexion == null) {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection(url, user, passwd);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void cerrarSentencia(Statement stmt, ResultSet res) {
        if (stmt != null)
            try {
                stmt.close();
                if (res != null) {

                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean addPersonaje(Personaje personaje) {
        crearConexion();
        String sentenciaSql = "INSERT INTO personajes (id,nombre,nivel,energia,puntos) VALUES (?,?,?,?,?)";
        PreparedStatement sentencia = null;
        boolean procesoOK = false;
        try {
            crearConexion();
            sentencia = conexion.prepareStatement(sentenciaSql);

            sentencia.setInt(1, personaje.getId());
            sentencia.setString(2, personaje.getNombre());
            sentencia.setInt(3, personaje.getNivel());
            sentencia.setInt(4, personaje.getEnergia());
            sentencia.setInt(5, personaje.getPuntos());
            sentencia.executeUpdate();


            procesoOK = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (sentencia != null)
                try {
                    sentencia.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return procesoOK;
    }

    public boolean modificarPersonaje(int antiguoPersonaje, Personaje nuevoPersonaje) {
        crearConexion();
        String sentenciaSql =
                "UPDATE personajes SET id = ?,nombre = ?,nivel = ?,energia = ?,puntos = ? " +
                        "WHERE id = " + antiguoPersonaje;
        PreparedStatement sentencia = null;
        boolean procesoOK = false;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, nuevoPersonaje.getId());
            sentencia.setString(2, nuevoPersonaje.getNombre());
            sentencia.setInt(3, nuevoPersonaje.getNivel());
            sentencia.setInt(4, nuevoPersonaje.getEnergia());
            sentencia.setInt(5, nuevoPersonaje.getPuntos());
            sentencia.executeUpdate();
            procesoOK = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            cerrarSentencia(sentencia, null);
        }
        return procesoOK;
    }

    public boolean borrarPersonaje(int idPersonaje) {
        crearConexion();
        boolean procesoOk = false;
        String sentenciaSql = "DELETE FROM personajes WHERE id = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setInt(1, idPersonaje);
            sentencia.executeUpdate();
            procesoOk = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            cerrarSentencia(sentencia, null);
        }
        return procesoOk;
    }

    public boolean borrarTodosPersonajes() {
        crearConexion();
        boolean procesoOk = false;
        String sentenciaSql = "call borrarTodos()";
        CallableStatement procedimiento = null;

        try {
            procedimiento = conexion.prepareCall(sentenciaSql);
            procedimiento.executeUpdate();
            procesoOk = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            cerrarSentencia(procedimiento, null);
        }
        return procesoOk;
    }


    public ArrayList<Personaje> listaPersonajes() {
        crearConexion();
        ArrayList<Personaje> personajes = new ArrayList<>();
        String sentenciaSql = "SELECT id,nombre,nivel,energia,puntos FROM personajes";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                personajes.add(new Personaje(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("nivel"),
                        resultado.getInt("energia"),
                        resultado.getInt("puntos")
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            cerrarSentencia(sentencia, resultado);
        }
        return personajes;

    }

    public Personaje obtenerPersonaje(int idPersonaje) {
        crearConexion();
        Personaje personaje = null;
        String sentenciaSql = "SELECT id,nombre,nivel,energia,puntos FROM personajes WHERE id = " + idPersonaje;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                personaje = new Personaje(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("nivel"),
                        resultado.getInt("energia"),
                        resultado.getInt("puntos")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            cerrarSentencia(sentencia, resultado);

        }
        return personaje;

    }
}