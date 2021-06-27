/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.util.List;

/**
 *
 * @author jairo
 * Implementar metodos utilizando colecciones de datos, clase List y ArrayList
 */
public class ListAccesoDatoss implements IAccesoDatos {

    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
       // implemntar 
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
        return null;
        //Implementar
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
       return null; 
       //implementar
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        
        return null;
        //implementar
    }
    
}
