
package co.edu.unicesar.sistemas.p2.practica2.vista;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import co.edu.unicesar.sistemas.p2.practica2.negocio.RegistroPublicacion;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class VistaRegistro extends JDialog {
    
    private JLabel lisbn, ltitulo, lautor, lanio, lcosto;
    private JTextField tisbn, ttitulo, tautor, tanio, tcosto;
    
    private JButton bGuardar, bCancelar, bNuevo, bBuscar, bEliminar;

    private JFormattedTextField tfcosto;

    private JPanel panelDatos, panelBotones;
    private Container contenedor;

    private RegistroPublicacion negocio;
    
    public VistaRegistro(JFrame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Registro de Publicaciones - V1");
        this.negocio = new RegistroPublicacion();
        this.iniciarComponentes();
        //this.setSize(600, 400);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    VistaRegistro(VistaPrincipal aThis, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void iniciarComponentes() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.panelDatos();
        this.panelBotones();
    }

    public void panelDatos() {
        this.panelDatos = new JPanel();
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelDatos.setLayout(new GridLayout(7, 2, 5, 5));

        this.lisbn = new JLabel("  ISBN: ");
        this.ltitulo = new JLabel("  Titulo: ");
        this.lautor = new JLabel("  Autor: ");
        this.lanio = new JLabel("  Año: ");
        this.lcosto = new JLabel("  Costo: ");

        this.ttitulo = new JTextField(null);
        this.tautor = new JTextField(null);
        this.tanio = new JTextField(null);
        this.tisbn = new JTextField(null);
        this.tisbn.setEnabled(false);
        this.tcosto = new JTextField(null);

        this.bGuardar = new JButton("Guardar");
        this.bGuardar.addActionListener(new EventoClickBotonGuardar());
        this.bGuardar.setEnabled(false);
        this.bCancelar = new JButton("Cancelar");
        this.bCancelar.setEnabled(false);

        this.panelDatos.add(this.lisbn);
        this.panelDatos.add(this.tisbn);

        this.panelDatos.add(this.ltitulo);
        this.panelDatos.add(this.ttitulo);

        this.panelDatos.add(this.lautor);
        this.panelDatos.add(this.tautor);

        this.panelDatos.add(this.lanio);
        this.panelDatos.add(this.tanio);

        this.panelDatos.add(this.lcosto);
        this.panelDatos.add(this.tcosto);

        this.panelDatos.add(this.bGuardar);
        this.panelDatos.add(this.bCancelar);

        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);

    }

    public void panelBotones() {
        this.panelBotones = new JPanel();

        this.bNuevo = new JButton("Nuevo");
        this.bNuevo.addActionListener(new EventoClickBotonNuevo());
        this.bBuscar = new JButton("Buscar");
        this.bBuscar.setEnabled(false);
        this.bBuscar.addActionListener(new EventoClickBotonBuscar());
        this.bEliminar = new JButton("Eliminar");
        this.bEliminar.setEnabled(false);
        this.bEliminar.addActionListener( new EventoClickBotonEliminar());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(this.bNuevo);
        panel.add(this.bBuscar);
        panel.add(this.bEliminar);

        this.panelBotones.add(panel);

        this.contenedor.add(this.panelBotones, BorderLayout.EAST);

    }

    public void activarComponentes() {
        this.tisbn.setEnabled(true);
        this.bGuardar.setEnabled(true);
        this.bCancelar.setEnabled(true);
        this.bBuscar.setEnabled(true);
        this.bEliminar.setEnabled(true);
        this.tisbn.grabFocus();
    }

    public Publicacion leerDatos() {
        String isbn = this.tisbn.getText();
        String titulo = this.ttitulo.getText();
        String autor = this.tautor.getText();
        String anio = this.tanio.getText();
        String costo = this.tcosto.getText();

        

        Publicacion p = new Publicacion(isbn, titulo, autor, anio, costo);
        return p;
    }

    public void eliminarMotocicleta() {

        String isbn = this.tisbn.getText();
        int confirmacion = JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
        if (confirmacion == 0) {
            try {
               this.negocio.eliminarPublicacion(isbn);
               this.mostrarMsg("Exito", "Elemento eliminado con exito", JOptionPane.INFORMATION_MESSAGE);
               this.limpiarComponentes();
            } catch (NullPointerException ex) {
                this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void mostrarMotocicleta() {

        try {
            String isbn = this.tisbn.getText();
            Publicacion p = this.negocio.buscarPublicacion(isbn);

            if (p == null) {
                throw new NullPointerException("La publicacion no se encuentra registrada");
            }

            this.tisbn.setText(isbn);
            String titulo = null;
            this.ttitulo.setText(titulo);
            String autor = null;
            this.tautor.setText(autor);
            String anio = null;
            this.tanio.setText(anio);
            String costo = null;
            this.tcosto.setText(costo);
           

        } catch (NullPointerException ex) {
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarComponentes() {
        this.tisbn.setText(null);
        this.tautor.setText(null);
        this.tanio.setText(null);
        this.tcosto.setText(null);
        this.tisbn.grabFocus();
    }

    public void mostrarMsg(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }

    public void guardar() throws ExcepcionAccesoDatos {

        try {
            Publicacion p = this.leerDatos();
            this.negocio.adicionarPublicacion(p);
            this.mostrarMsg("Exito", "Registro almacenado con exito", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarComponentes();
        } catch (NullPointerException ex) {
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);

        }

    }

    class EventoClickBotonNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            activarComponentes();
        }
    }

    class EventoClickBotonGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                guardar();
            } catch (ExcepcionAccesoDatos ex) {
                Logger.getLogger(VistaRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class EventoClickBotonBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            mostrarMotocicleta();
        }
    }

    class EventoClickBotonEliminar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            eliminarMotocicleta();
        }
    }

    class EventoClickComboMarca implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            ocultarComponentes();
        }

        private void ocultarComponentes() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
