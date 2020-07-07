/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logica.EchoClient;
import clientftp.ClientFTP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brend
 */
public class VentanaClienteFTP extends JFrame implements ActionListener {

    private JButton jbtnEnviar;
    public static JTable jTableG;
    private JButton jbtnDescargar;
    private JButton jbtnEliminar;
    private PanelTabla paneltabla;
    private String[] directorios;
    private JButton jbtnSincronizar;
    private JLabel carpetaActual;

    public boolean flag;

    public VentanaClienteFTP() {
        this.setTitle("ClienteFTP");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void init() {
        this.setBounds(500, 100, 400, 380);
        this.setLayout(null);
        //Creacion
        this.jbtnEnviar = new JButton("Enviar");
        this.jbtnDescargar = new JButton("Descargar");
        this.jbtnSincronizar = new JButton("Sincronizar");
        this.jbtnEliminar = new JButton("X");
        this.paneltabla = new PanelTabla();
        this.carpetaActual = new JLabel("");

        //Colocacion
        this.jbtnEnviar.setBounds(30, 260, 110, 30);
        this.jbtnDescargar.setBounds(140, 260, 110, 30);
        this.jbtnSincronizar.setBounds(250, 260, 110, 30);
        this.jbtnEliminar.setBounds(170, 300, 50, 30);
        this.carpetaActual.setBounds(10, 210, 360, 40);
        JScrollPane JL = new JScrollPane(carpetaActual);
        JL.setBounds(10, 215, 360, 40);
        JL.setVisible(true);

        //
        this.jbtnDescargar.addActionListener(this);
        this.jbtnEnviar.addActionListener(this);
        this.jbtnSincronizar.addActionListener(this);
        this.jbtnEliminar.addActionListener(this);
        this.add(JL);
        this.add(this.jbtnDescargar);
        this.add(this.jbtnEnviar);
        this.add(this.jbtnSincronizar);
        this.add(this.jbtnEliminar);
        this.add(paneltabla);

        setVisible(true);
    }

    public JButton getJbtnEnviar() {
        return jbtnEnviar;
    }

    public void setJbtnEnviar(JButton jbtnEnviar) {
        this.jbtnEnviar = jbtnEnviar;
    }

    public JButton getJbtnDescargar() {
        return jbtnDescargar;
    }

    public void setJbtnDescargar(JButton jbtnDescargar) {
        this.jbtnDescargar = jbtnDescargar;
    }

    public JButton getJbtnSincronizar() {
        return jbtnSincronizar;
    }

    public void setJbtnSincronizar(JButton jbtnSincronizar) {
        this.jbtnSincronizar = jbtnSincronizar;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.jbtnEnviar) {
            try {
                ClientFTP.getClient().getOut().println("recibir");

                if (enviarMensaje()) {

                }

            } catch (IOException ex) {
                Logger.getLogger(VentanaClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (arg0.getSource() == this.jbtnDescargar) {
            if (ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow() != -1) {
                ClientFTP.getClient().getOut().println("descargar");
            } else {
                System.err.println("No hay ningun archivo seleccionado");

            }
        }
        if (arg0.getSource() == this.jbtnSincronizar) {

            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this directory: "
                        + chooser.getSelectedFile().getAbsolutePath());
            }
            if (chooser.getSelectedFile().getAbsolutePath() == null) {

            }
            this.carpetaActual.setText(chooser.getSelectedFile().getAbsolutePath());
            ClientFTP.getClient().setAbsolutepath(chooser.getSelectedFile().getAbsolutePath());

        }
        if (arg0.getSource() == this.jbtnEliminar) {
            if (ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow() != -1) {
                ClientFTP.getClient().getOut().println("eliminar");
            } else {
                System.err.println("No hay ningun archivo seleccionado");

            }
        }

    }

    public boolean enviarMensaje() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this directory: "
                    + chooser.getSelectedFile().getAbsolutePath());
        }

        if (enviarArchivo(chooser.getSelectedFile().getAbsolutePath())) {
            return true;
        } else {
            return false;
        }

    }

    public boolean enviarArchivo(String nombreArchivo) {

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

            String[] auxSTR = new String[buffer.length];
            byte[] buffer2 = new byte[tamañoArchivo];
            // Realizamos el envio de los bytes que conforman el archivo
            for (int i = 0; i < auxSTR.length; i++) {
                auxSTR[i] = String.valueOf(buffer[i]);
            
                buffer2[i] = (byte) Byte.parseByte(ClientFTP.getEncriptado().encriptar(auxSTR[i], ClientFTP.getClaveEncriptacion()));
                ClientFTP.getClient().getBos().write(buffer[i]);
            }

            System.out.println("Archivo Enviado: " + archivo.getName());
            // Cerramos socket y flujos
            bis.close();
            ClientFTP.getClient().getBos().flush();
            ClientFTP.getClient().getDos().flush();

            return true;

        } catch (Exception e) {
            System.err.println("Error no se envio el archivo" + e);
            return false;
        }

    }

    public String[] getDirectorios() {
        return directorios;
    }

    public void setDirectorios(String[] directorios) {
        this.directorios = directorios;

    }

    public boolean descargarArchivo() {

        while (true) {

            try {
                // Creamos el socket que atendera el servidor
                // Creamos flujo de entrada para leer los datos que envia el cliente 

                DataInputStream dis = new DataInputStream(ClientFTP.getClient().getServerSocket().getInputStream());
                // Obtenemos el nombre del archivo

                String nombreArchivo = dis.readUTF().toString();

                // Obtenemos el tamaño del archivo
                int tam = (int) dis.readInt();

                System.out.println("Tam: " + tam);
                System.out.println("Recibiendo archivo: " + nombreArchivo);

                // Creamos flujo de salida, este flujo nos sirve para 
                // indicar donde guardaremos el archivo
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
                int returnVal = chooser.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this directory: "
                            + chooser.getSelectedFile().getAbsolutePath());
                }

                FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath() + "\\" + nombreArchivo);
                BufferedOutputStream out = new BufferedOutputStream(fos);
                BufferedInputStream in = new BufferedInputStream(ClientFTP.getClient().getServerSocket().getInputStream());

                // Creamos el array de bytes para leer los datos del archivo
                byte[] buffer = new byte[tam];

                // Obtenemos el archivo mediante la lectura de bytes enviados
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) in.read();
                }

                // Escribimos el archivo 
                out.write(buffer);
                out.flush();
                fos.close();

                System.out.println("Archivo Recibido: " + nombreArchivo);
                return true;
            } catch (Exception e) {
                System.err.println("Error: " + e.toString());
                return false;
            }
        }
    }

    class PanelTabla extends JPanel {

        JScrollPane scroll;
        JTable jTable;
        int init = 1;
        String[] direcRep;

        public PanelTabla() {
            this.setLayout(null);
            this.setBounds(10, 10, 360, 200);

            initJtable();
            this.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getDirectorios() != null && getDirectorios() != direcRep) {
                setUpTableData(directorios);
                direcRep = getDirectorios();
            }
            repaint();
        }

        public void initJtable() {

            String[] colName = {"Nombre archivo"};
            if (jTable == null) {
                jTable = new JTable() {
                    @Override
                    public boolean isCellEditable(int nRow, int nCol) {
                        return false;
                    }
                };
            }

            DefaultTableModel contactTableModel = (DefaultTableModel) jTable
                    .getModel();
            contactTableModel.setColumnIdentifiers(colName);

            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            jTable.setBounds(0, 0, 359, 200);

            scroll = new JScrollPane(jTable);
            scroll.setBounds(0, 0, 359, 200);
            scroll.setVisible(true);
            this.add(scroll);

        }

        public void setUpTableData(String[] directorio) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            eliminar();

            for (int i = 0; i < directorio.length; i++) {
                String[] data = new String[1];

                data[0] = directorio[i];

                tableModel.addRow(data);

            }
            tableModel.fireTableDataChanged();
            jTable.setModel(tableModel);
            jTableG = jTable;
            /**/

        }

        public void eliminar() {
            DefaultTableModel tb = (DefaultTableModel) jTable.getModel();
            int a = jTable.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                tb.removeRow(tb.getRowCount() - 1);
            }
            //cargaTicket();
        }

        public JTable getjTable() {
            return jTable;
        }

        public void setjTable(JTable jTable) {
            this.jTable = jTable;
        }

    }

}
