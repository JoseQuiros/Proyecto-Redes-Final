package GUI;


import clientftp.ClientFTP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author Jordan
 */

public class Registro extends JFrame implements ActionListener{
    private JTextField jtxtName;
    private JTextField jtxtContraseña;
    private JButton jbtnIngresar;
    
    private JLabel jlblName;
    private JLabel jlblContraseña;
    
    public Registro() {
     this.setTitle("Cliente");
     this.setResizable(false);
    }
    
    public void init(){
     this.setBounds(500, 100, 400, 325);
     this.setLayout(null);
     //Creacion
     this.jlblName= new JLabel("Nombre");
     this.jlblContraseña= new JLabel("Contraseña");
     this.jtxtName= new JTextField();
     this.jtxtContraseña = new JTextField();
     this.jbtnIngresar = new JButton("Ingresar");
  
     //
     
     //Colocacion
     this.jlblName.setBounds(10,100 ,100, 20);
     this.jlblContraseña.setBounds(10,150 ,120, 20);
     this.jtxtName.setBounds(120, 100, 120, 20);
     this.jtxtContraseña.setBounds(120, 150, 120, 20);
     this.jbtnIngresar.setBounds(40, 225, 110, 30);
     
     //
     
     //add
     
     this.jbtnIngresar.addActionListener(this);
     this.add(this.jlblName);
     this.add(this.jlblContraseña);
     this.add(this.jtxtName);
     this.add(this.jtxtContraseña);
     this.add(this.jbtnIngresar);
   
     //
     
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
       if(arg0.getSource()==this.jbtnIngresar){
           ClientFTP.getClient().getOut().println("iniciar sesion");
           ClientFTP.getClient().getOut().println(jtxtName.getText());
           ClientFTP.getClient().getOut().println(jtxtContraseña.getText());
       }
       
       
       
    }
    
    
    
}
