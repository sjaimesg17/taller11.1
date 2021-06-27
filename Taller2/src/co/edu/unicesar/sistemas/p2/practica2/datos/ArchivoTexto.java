/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ArchivoTexto implements IAccesoDatos {
    
    private File archivo;
    private FileWriter aEscritura;
    private Scanner aLectura;

    public ArchivoTexto(String name) {
        this.archivo = new File(name);
    }

    public ArchivoTexto() {
        this("libros.dat");
    }
    
    

    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        PrintWriter pw = null;
        try {
            this.aEscritura = new FileWriter(this.archivo, true);
            pw = new PrintWriter(this.aEscritura);
            pw.println(p.getInfo());
        } catch (IOException ex) {
            Logger.getLogger(ArchivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(pw!=null)
                pw.close();
            if(this.aEscritura!=null)
                try {
                    this.aEscritura.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchivoTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private Publicacion crearPublicacion(String linea){
        String datos[] = linea.split(";");
        Publicacion p = new Publicacion() {
            @Override
            public String getInfo() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        p.setIsbn(datos[1]);
        p.getAutor(datos[2]);
        p.getCosto(Double.parseDouble(datos[3]));
        p.getTitulo(datos[4]);
        p.getAnio(Integer.parseInt(datos[5]));
        return p;
    }
    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
        List<Publicacion> lista = new ArrayList();
        try {
            this.aLectura = new Scanner(this.archivo);
            while(this.aLectura.hasNext()){
                String linea = this.aLectura.nextLine();
                Publicacion p = this.crearPublicacion(linea);
                lista.add(p);
            }
            return lista;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(this.aLectura!=null)
                this.aLectura.close();
        }
        return null;
        
       
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        
        Publicacion encontrada = null;
        try{
        this.aLectura = new Scanner();
        while(this.aLectura.hasNext()){
            String linea = this.aLectura.nextLine();
            Publicacion p1 = this.crearPublicacion(linea);
            if(p1.getAnio().equalsIgnoreCase(anio)){
                encontrada = p1;
                break;
            } 
        }
        return encontrada;
        }catch(FileNotFoundException ex) {
            Logger.getLogger(ArchivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(this.aLectura!=null)
                this.aLectura.close();
        }
        return null;
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        int contador=0;
        try{
            this.aLectura = new Scanner(this.archivo);
            ArchivoTexto tmp = new ArchivoTexto("Temporal.dat");
            while(this.aLectura.hasNext()){
                Publicacion p2 = this.crearPublicacion(this.aLectura.nextLine());
                double costo = 0;
                if(p2.getCosto()>costo){
                    contador++;
                }
                else{
                    tmp.insertarPublicacion(p2);
                   
                }
            }
            this.aLectura.close();
            return contador;
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(ArchivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(this.aLectura!=null)
                this.aLectura.close();
        }
        
        return null;
    }
    
}
