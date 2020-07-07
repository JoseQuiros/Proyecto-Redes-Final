/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDatos {
   String URL = "jdbc:mysql://163.178.107.10/"; // Ubicación de la BD.
    String BD = "PROYECTO1REDES"; // Nombre de la BD.
    String USER = "laboratorios";
    String PASSWORD = "UCRSA.118";
    String a="?useSSL=false";
    public Connection conexion = null;

    @SuppressWarnings("finally")
    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + BD+a, USER, PASSWORD);
            if (conexion != null) {
                System.out.println("¡Conexión Exitosa!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return conexion;
        }
    }
}