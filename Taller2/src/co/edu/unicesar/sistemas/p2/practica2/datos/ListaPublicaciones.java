
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public abstract class ListaPublicaciones implements IAccesoDatos{
    private List<Publicacion> lista;

    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        this.lista.add(p);
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
        return this.lista;
    }

    public Publicacion buscarPublicacion(String autor) throws ExcepcionAccesoDatos {
        for(Publicacion p: this.lista){
            if(p.getAutor().equalsIgnoreCase(autor)){
                return p;           
            }
        }
        return null;
    }

    public int eliminarPublicacion(Double costo) throws ExcepcionAccesoDatos {
        Iterator<Publicacion> i = this.lista.iterator();
        int contador=0;
        while(i.hasNext()){
            Publicacion p = (Publicacion)i;
            if(p.getCosto()>costo){
                i.remove();
                contador ++;
            }
            
        }
        return contador;      
    }

    void insertarPublicacion(ListaPublicaciones lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Publicacion buscarPublicaciones(String autor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Publicacion eliminarPublicaciones(Double costo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int eliminarPublicaciones(int costo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
