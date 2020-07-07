/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverftp;

import GUI.VentanaPrincipal;
import Logica.EchoMultiServer;
import Logica.Encriptado;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author brend
 */
public class ServerFTP {

    private static EchoMultiServer server;
    private static Encriptado encriptado;
    private static String claveEncriptacion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        claveEncriptacion = "secreto!";
        encriptado = new Encriptado();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
        server = new EchoMultiServer();
        server.start(5555);

    }

    public static Encriptado getEncriptado() {
        return encriptado;
    }

    public static void setEncriptado(Encriptado encriptado) {
        ServerFTP.encriptado = encriptado;
    }

    public static String getClaveEncriptacion() {
        return claveEncriptacion;
    }

    public static void setClaveEncriptacion(String claveEncriptacion) {
        ServerFTP.claveEncriptacion = claveEncriptacion;
    }

    public static EchoMultiServer getServer() {
        return server;
    }

    public static void setServer(EchoMultiServer server) {
        ServerFTP.server = server;
    }

}
