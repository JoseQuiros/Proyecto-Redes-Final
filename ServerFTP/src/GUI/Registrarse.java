/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Objetos.Usuario;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jose
 */
public class Registrarse extends JInternalFrame implements ActionListener {

    JTextField jtfUsuario;
    JTextField jtfContrasena;

    boolean a = true;
private String rutaArchivo;
    JLabel jlUsuario;
    JLabel jlContraseña;
    JLabel jlMensajes;
    JLabel jlImagen;
    JFileChooser file;
    JButton jbtnRegistrarse;
    JButton jbtnLimpiar;
    JButton jbtnMostrarContraseña;
    JButton jbtnElegirImagen;
    JButton jbtnCancelar;
    JPanel jPImagen;
    JDesktopPane jdesktopAvatar;
    Dimension size;
    private JInternalFrame jInternalFrameAvatar = new JInternalFrame();

    public Registrarse() {
        super("Registrar cuenta");
        this.jlUsuario = new JLabel("Usuario:");
  
        this.jlContraseña = new JLabel("Contraseña:");
        this.jlUsuario = new JLabel("Usuario:");
        this.jlImagen = new JLabel("");
        this.jlMensajes = new JLabel("");
        this.jtfUsuario = new JTextField();
        this.jtfContrasena = new JTextField();
        this.jbtnRegistrarse = new JButton("Registrar");
        this.jbtnCancelar = new JButton("Cancelar");
        this.jbtnMostrarContraseña = new JButton("Mostrar");
    

      
        this.setSize(400,400);
        size = super.getToolkit().getScreenSize();
        this.setLayout(null);
        
        
        init();

    }//Constructor

    private void init() {


        this.jlUsuario.setBounds(100, 150, 100, 50);
        this.add(this.jlUsuario);
        this.jlContraseña.setBounds( 100, 200, 100, 50);
        this.add(this.jlContraseña);

        this.jbtnRegistrarse.setBounds(100, 300, 100, 30);
        this.jbtnRegistrarse.addActionListener(this);

        this.jbtnCancelar.setBounds(200, 300, 100, 30);
        this.jbtnCancelar.addActionListener(this);
        this.add(this.jbtnRegistrarse);
        this.add(this.jbtnCancelar);
       

        this.jtfUsuario.setBounds(180, 160, 100, 25);
        this.jtfContrasena.setBounds( 180, 210, 100, 25);
        this.add(this.jtfUsuario);
        this.add(this.jtfContrasena);

        this.file = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.jbtnRegistrarse) {
          
            try {
                Usuario usuario= new Usuario();
                usuario.agregar(jtfUsuario.getText(),this.jtfContrasena.getText());
                
/*
                JugadorBusiness jugadorBusiness = new JugadorBusiness();
                String contra = Utility.MyUtility.obtenerContraseniaCifrada(this.jtfContrasena.getText(), Utility.MyUtility.SHA256);
                Jugador jugador = new Jugador(jtfUsuario.getText(), contra, 0, this.rutaArchivo);

                Jugador jugadorValidar = jugadorBusiness.findPlayer(jugador);*/

              /*  if (jugadorValidar == null) {
                  /*  if (jugadorBusiness.addEndRecord(jugador)) {*/
                        JOptionPane.showMessageDialog(null, "Registro exitoso");
                        this.jtfUsuario.setText("");
                        this.jtfContrasena.setText("");
                    
                  /*  } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                    }*/
              /*  } else {
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre, por favor ingrese uno nuevo");
                }*/
            } catch (Exception ae) {
            }
         
        }

        if (e.getSource() == this.jbtnCancelar) {
            this.dispose();
        }
        if (e.getSource() == this.jbtnLimpiar) {
            this.jtfUsuario.setText("");
            this.jtfContrasena.setText("");
        }

      
    }
}


