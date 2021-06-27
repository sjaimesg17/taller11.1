
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public abstract class ArchivoObjetos implements IAccesoDatos {
    private File archivo;
    private FileInputStream aLectura;
    private FileOutputStream aEscritura;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ArchivoObjetos(String name) {
        this.archivo = new File(name);
    }

    public ArchivoObjetos() {
        this("PublicacionesLibrosObjetos.obj");
    }
    
    private void guardar(ListaPublicaciones lista) throws IOException{
        this.oos = null;
        try{
            this.aEscritura = new FileOutputStream(this.archivo, false);
            this.oos = new ObjectOutputStream(this.aEscritura);
            this.oos.writeObject(lista);
        }catch(IOException ioe){
            throw ioe;
        }
        finally{
            if(this.oos!=null)
                this.oos.close();
            
            if(this.aEscritura!=null)
                this.aEscritura.close();
        
        }
    }
   
    private ListaPublicaciones leer() throws IOException{
        
        ListaPublicaciones lista = null;
        if(this.archivo.exists()){
            
            this.ois = null;
            try{
                this.aLectura = new FileInputStream(this.archivo);
                this.ois = new ObjectInputStream(this.aLectura);
                lista = (ListaPublicaciones) this.ois.readObject();
                return lista;
            }catch(IOException ioe){
                throw ioe;
            }catch(ClassNotFoundException ex){
                throw new IOException("La clase lista pucblicaciones no existe");
            }
            finally{
            if(this.ois!=null)
                this.ois.close();
            
            if(this.aLectura!=null)
                this.aLectura.close();
        }
        }
        else{
            lista = new ListaPublicaciones() {
                @Override
                public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            return lista;
        }
        
    } 

    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        ListaPublicaciones lista = null;
        try {
            lista = this.leer();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        lista.insertarPublicacion(lista);
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
        ListaPublicaciones lista = null;
        try {
            lista = this.leer();
        } catch (IOException ex) {
            Logger.getLogger(ArchivoObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista.leerPublicaciones();
    }

    public Publicacion buscarPublicacion(String autor) throws ExcepcionAccesoDatos, IOException {
        ListaPublicaciones lista = this.leer();
        return lista.buscarPublicaciones(autor);
    }

    public int eliminarPublicacion(int costo) throws ExcepcionAccesoDatos, IOException {
        ListaPublicaciones lista = this.leer();
        int cont= lista.eliminarPublicaciones(costo);
        this.guardar(lista);
        return cont;
    }
}
