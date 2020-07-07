/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import clientftp.ClientFTP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author brend
 */
public class ConectaServidor extends JFrame implements ActionListener{
    private JTextField jtxtIp;

    private JButton jbtnConectar;
    
    private JLabel jlblIp;

    
    public ConectaServidor() {
     this.setTitle("Conectar con servidor");
     this.setResizable(false);
    }
    
    public void init(){
     this.setBounds(500, 100, 300, 260);
     this.setLayout(null);
     //Creacion
     this.jlblIp= new JLabel("IP: ");
     this.jtxtIp= new JTextField();
     this.jbtnConectar = new JButton("Conectar");
  
     //
     
     //Colocacion
     this.jlblIp.setBounds(10,100 ,100, 20);
    
     this.jtxtIp.setBounds(50, 100, 120, 20);
     
     this.jbtnConectar.setBounds(40, 150, 110, 30);
     
     //
     
     //add
     
     this.jbtnConectar.addActionListener(this);
     this.add(this.jlblIp);
     this.add(this.jtxtIp);
     this.add(this.jbtnConectar);
   
     //
     
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
       if(arg0.getSource()==this.jbtnConectar){
           
           if(!jtxtIp.getText().equalsIgnoreCase("")&&(ClientFTP.getClient().startConnection(jtxtIp.getText(), 5555))){
               ClientFTP.getClient().getOut().println("Hello");
               this.dispose();
           }else{
               JOptionPane.showMessageDialog(this, "Error al conectar");
           }
       }
       
       
   }
}
