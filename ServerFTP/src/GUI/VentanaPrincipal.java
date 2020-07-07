package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jose
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    JInternalFrame ventanaInterna;

    JDesktopPane jdpPrincipal;

    JMenuBar jmbBarra;

    JMenu jMenu;

    JMenuItem jItemInicio;
    JMenuItem jItemRegistrarse;
   
    JMenuItem jItemSalir;
  
    Registrarse registrarseInternalF;

    public VentanaPrincipal() throws UnknownHostException {
        super();
        this.setLayout(null);
        this.setSize(400,410);
        this.setTitle(""+InetAddress.getLocalHost());
        init();
    }

    private void init() {

        jdpPrincipal = new JDesktopPane();

        jmbBarra = new JMenuBar();

        jMenu = new JMenu("Menú");

     
        jItemSalir = new JMenuItem("Salir");
 
        jItemRegistrarse = new JMenuItem("Registrarse");
        jItemSalir = new JMenuItem("Salir");


      

        jItemRegistrarse.addActionListener(this);
        this.jMenu.add(this.jItemRegistrarse);

        this.jmbBarra.add(this.jMenu);

        setJMenuBar(this.jmbBarra);

        this.jdpPrincipal.setSize(400,400);
        this.add(this.jdpPrincipal);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (this.jItemRegistrarse == ae.getSource()) {

            this.registrarseInternalF = new Registrarse();
                    this.registrarseInternalF.setBorder(null);//quitar borde menos el superior 
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.registrarseInternalF.getUI()).setNorthPane(null);
            this.registrarseInternalF.setVisible(true);
         
            this.registrarseInternalF.setClosable(true);

            this.jdpPrincipal.add(this.registrarseInternalF);
        }
        if (this.jItemSalir == ae.getSource()) {
            this.dispose();
        }

    }

}
