
package co.edu.unicesar.sistemas.p2.practica2.vista;

import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
public class VistaPrincipal extends JFrame implements ActionListener {
    
    private JMenuBar barraMenu;
    private JMenu menu;
    private JMenuItem menuRegistro, mConsulta;

    public VistaPrincipal() {
        this("Registro de Publicaciones - V 1.0");
    }

    public VistaPrincipal(String string) {
        super(string);
        this.iniciarComponentes();
        
    }
     public void iniciarComponentes(){
        this.iniciarBarraMenu();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
     }
     
     
     
     public void iniciarBarraMenu(){
         
         this.barraMenu = new JMenuBar();
         
         this.menu = new JMenu("Operaciones");
                  
         this.menuRegistro = new JMenuItem("Registro");
         this.menuRegistro.addActionListener(this);
         
         this.mConsulta = new JMenuItem("Consulta");
         this.mConsulta.addActionListener(this);
         
         this.menu.add(this.menuRegistro);
         this.menu.add(this.mConsulta);
         
         this.barraMenu.add(this.menu);
         
         this.setJMenuBar(this.barraMenu);
     }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==this.menuRegistro){
             VistaRegistro registro = new VistaRegistro(this, true);
       
        }
        
        if(ae.getSource()==this.mConsulta){
            try {
                VistaConsulta consulta = new VistaConsulta(this, true);
            } catch (ExcepcionAccesoDatos ex) {
                Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
