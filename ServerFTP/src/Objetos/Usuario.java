/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Objetos.BaseDeDatos;
import java.io.File;
/**
 *
 * @author brend
 */
public class Usuario {
    private String nombre;
    private String contraseña;

    public Usuario() {
    }

    
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
     public void agregar(String nombre, String contrasena) throws SQLException {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        Statement sentencia = bd.conectar().createStatement();
        
        String parametro="";
            query = "INSERT INTO persona(nombre, " + "contrasena"  + ") VALUES ('" + nombre + "', " + "'" +  contrasena
                +"');";
 System.out.println(query);
        if (sentencia.executeUpdate(query) > 0) {
            System.out.println("El registro se insertó exitosamente.");
            
            File directorio=new File("Carpetas\\"+nombre);
            directorio.mkdir();
        } else {
            System.out.println("No se pudo insertar el registro.");
        }

        System.out.println(query);
        sentencia.close();
        bd.conexion.close();
    }
     
     public static boolean validar(String nombre, String contrasena) {
        String query = "";
         String contrasena1="";
        BaseDeDatos bd = new BaseDeDatos();
        try {
            query = "SELECT * FROM persona WHERE nombre = '" + nombre + "';";
            Statement sentencia = bd.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                String nombre1 = resultado.getString("nombre");
                contrasena1 = resultado.getString("contrasena");
                

                // Imprimir los resultados.
                System.out.format("%s, %s\n", nombre1, contrasena1);
               
            }

            sentencia.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         System.out.println("Contraseña: "+contrasena1);
        if(contrasena.equalsIgnoreCase(contrasena1) && contrasena1!=""){
                return true;
                }else{
                return false;
                }
    }
     
     public void obtener(String nombre, String contrasena) {
        String query = "";
        BaseDeDatos bd = new BaseDeDatos();
        try {
            query = "SELECT * FROM persona WHERE nombre = '" + nombre + "';";
            Statement sentencia = bd.conectar().createStatement();
            ResultSet resultado = sentencia.executeQuery(query);

            while (resultado.next()) {
                String nombre1 = resultado.getString("nombre");
                String contrasena1 = resultado.getString("contrasena");
                

                // Imprimir los resultados.
                System.out.format("%s, %s\n", nombre1, contrasena);
            }

            sentencia.close();
            bd.conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
