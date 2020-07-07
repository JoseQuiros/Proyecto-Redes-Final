package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Jordan
 */
import GUI.VentanaClienteFTP;
import clientftp.ClientFTP;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.InlineView;

public class EchoClient {

    private DataOutputStream dos;
    private BufferedOutputStream bos;

    private String inputLineVentana;
    private Socket serverSocket;
    private PrintWriter out;
    private BufferedReader in;
    public static ReciveAndSendServer reciveFromServer;
    public static Sincronizar sincronizar;
    private int estado;

    private String absolutepath;
    private ArrayList listaEnviar;

    public boolean startConnection(String ip, int port) {
        try {
            absolutepath = "";
            listaEnviar = new ArrayList();
            serverSocket = new Socket(ip, port);
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            estado = 0;
            sincronizar = new Sincronizar();
            sincronizar.start();
            reciveFromServer = new ReciveAndSendServer();
            reciveFromServer.start();

            this.dos = new DataOutputStream(serverSocket.getOutputStream());
            this.bos = new BufferedOutputStream(serverSocket.getOutputStream());

            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            reciveFromServer.interrupt();
            serverSocket.close();
        } catch (IOException e) {

        }

    }

    public PrintWriter getOut() {
        return out;
    }

    public String getInputLineVentana() {
        return inputLineVentana;
    }

    public void setInputLineVentana(String inputLineVentana) {
        this.inputLineVentana = inputLineVentana;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public ArrayList getListaEnviar() {
        return listaEnviar;
    }

    public void setListaEnviar(ArrayList listaEnviar) {
        this.listaEnviar = listaEnviar;
    }

    public BufferedReader getIn() {
        return in;
    }

    public String getAbsolutepath() {
        return absolutepath;
    }

    public void setAbsolutepath(String absolutepath) {
        this.absolutepath = absolutepath;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public BufferedOutputStream getBos() {
        return bos;
    }

    public void setBos(BufferedOutputStream bos) {
        this.bos = bos;
    }

    public ReciveAndSendServer getReciveFromServer() {
        return reciveFromServer;
    }

    public boolean difCarpetas() {
        try {
            File aux = new File(absolutepath);
            String[] dirCarpetaCliente = aux.list();

            if (dirCarpetaCliente == null) {
                System.out.println("No hay ficheros en el directorio especificado");
            }

            String[] dirCarpetaServer = ClientFTP.getVentanaClienteFTP().getDirectorios();
            ArrayList listaServer = new ArrayList();
            for (int i = 0; i < dirCarpetaServer.length; i++) {
                listaServer.add(dirCarpetaServer[i]);
            }
            ArrayList listaCliente = new ArrayList();
            for (int i = 0; i < dirCarpetaCliente.length; i++) {
                listaCliente.add(dirCarpetaCliente[i]);
            }
            ArrayList lista = new ArrayList();
            for (int i = 0; i < listaCliente.size(); i++) {
                if (!listaServer.contains(listaCliente.get(i))) {
                    lista.add(absolutepath + "\\" + listaCliente.get(i));
                }
            }
            if (lista.size() != 0 && !listaEnviar.equals(lista)) {
                listaEnviar = lista;
                ClientFTP.getClient().getOut().println("sincronizar");

                return true;
            }

        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public void inicio() {
        String inputLine = "";
        try {
            if ((inputLine = ClientFTP.getClient().getIn().readLine()) != null) {

                if (inputLine.equalsIgnoreCase("Enviar nombre archivo")) {
                    if (ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) ClientFTP.getVentanaClienteFTP().jTableG.getModel();
                        String nombre = (String) model.getValueAt(ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow(), 0);
                        ClientFTP.getClient().getOut().println(nombre);
                        ClientFTP.getVentanaClienteFTP().descargarArchivo();
                    }
                }

                if (inputLine.equalsIgnoreCase("Enviar nombre archivo a eliminar")) {
                    if (ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) ClientFTP.getVentanaClienteFTP().jTableG.getModel();
                        String nombre = (String) model.getValueAt(ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow(), 0);
                        ClientFTP.getClient().getOut().println(nombre);
                        ClientFTP.getClient().difCarpetas();
                    }
                }

                if (inputLine.equalsIgnoreCase("inicio")) {
                    ClientFTP.getVentanaRegistro().init();
                    ClientFTP.getClient().getOut().println("Recibido inicio");
                }

                if (inputLine.equalsIgnoreCase("Enviar archivos sincro")) {

                    for (int i = 0; i < listaEnviar.size(); i++) {
                        inputLine = in.readLine();
                        if (inputLine.equalsIgnoreCase("Enviar")) {
                            enviarArchivoSincro(String.valueOf(listaEnviar.get(i)));
                        }
                    }
                }

                if (inputLine.equalsIgnoreCase("acceso")) {
                    ClientFTP.getVentanaClienteFTP().init();
                    ClientFTP.getClient().getOut().println("Recibido acceso");
                    ClientFTP.getVentanaRegistro().dispose();
                }
                if (inputLine.equalsIgnoreCase("Datos incorrectos")) {
                    JOptionPane.showMessageDialog(ClientFTP.getVentanaClienteFTP(), "Introduzca datos validos de usuario");
                }

                if (inputLine.equalsIgnoreCase("Enviar directorios")) {
                    ClientFTP.getClient().getOut().println("Recibido: Enviar directorios");
                    int tam = Integer.parseInt(ClientFTP.getClient().getIn().readLine());
                    ClientFTP.getClient().getOut().println("Recibido tam");
                    System.out.println("tamaño:  " + tam);
                    ClientFTP.getClient().getOut().println("tam recibido");

                    String[] directorios = new String[tam];
                    for (int j = 0; j < tam; j++) {
                        directorios[j] = ClientFTP.getClient().getIn().readLine();
                    }
                    for (int j = 0; j < directorios.length; j++) {
                        System.out.println("Directorios: " + directorios[j]);
                    }

                    ClientFTP.getVentanaClienteFTP().setDirectorios(directorios);
                }

            }

        } catch (IOException ex) {

        } catch (NumberFormatException ex) {

        }
    }

    public void enviarArchivoSincro(String nombreArchivo) {

        try {
            // Creamos el archivo que vamos a enviar
            System.out.println("Nombre del Archivo: " + nombreArchivo);
            File archivo = new File(nombreArchivo);

            // Obtenemos el tamaño del archivo
            int tamañoArchivo = (int) archivo.length();

            System.out.println("Tam cliente: " + tamañoArchivo);

            // Creamos el flujo de salida, este tipo de flujo nos permite 
            // hacer la escritura de diferentes tipos de datos tales como
            // Strings, boolean, caracteres y la familia de enteros, etc.
            System.out.println("Enviando Archivo: " + archivo.getName());
            // Enviamos el nombre del archivo 
            ClientFTP.getClient().getDos().writeUTF(archivo.getName());

            // Enviamos el tamaño del archivo
            ClientFTP.getClient().getDos().writeInt(tamañoArchivo);

            // Creamos flujo de entrada para realizar la lectura del archivo en bytes
            FileInputStream fis = new FileInputStream(nombreArchivo);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Creamos un array de tipo byte con el tamaño del archivo 
            byte[] buffer = new byte[tamañoArchivo];

            // Leemos el archivo y lo introducimos en el array de bytes 
            bis.read(buffer);

            // Realizamos el envio de los bytes que conforman el archivo
            for (int i = 0; i < buffer.length; i++) {
                ClientFTP.getClient().getBos().write(buffer[i]);
            }

            System.out.println("Archivo Enviado: " + archivo.getName());
            // Cerramos socket y flujos
            bis.close();
            ClientFTP.getClient().getBos().flush();
            ClientFTP.getClient().getDos().flush();

        } catch (Exception e) {
            System.err.println("Error no se envio el archivo");

        }

    }
}

class ReciveAndSendServer extends Thread {

    public ReciveAndSendServer() throws IOException {

    }

    @Override
    public void run() {
        while (true) {
            ClientFTP.getClient().inicio();
        }
    }

    public boolean enviarMensaje(String inputLine) {
        if (inputLine.equals("1")) {
            ClientFTP.getClient().getOut().printf("Enviar directorios");
            System.out.println("Entro 1");

            return true;
        }
        return false;
    }

}

class Sincronizar extends Thread {

    public Sincronizar() {
    }

    @Override
    public void run() {
        while (true) {
            System.getenv(ClientFTP.getClient().getAbsolutepath());

            if (ClientFTP.getClient().getAbsolutepath() != "") {
                if (ClientFTP.getClient().difCarpetas()) {
                    ClientFTP.getClient().getOut().println("" + ClientFTP.getClient().getListaEnviar().size());
                }
            }

        }
    }

}
