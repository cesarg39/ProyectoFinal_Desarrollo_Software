/*
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  Universidad Ean (Bogotá - Colombia)
  Unidad de Estudio: Desarrollo de Software
  <p>
  Proyecto Exposición Canina
  Fecha: Marzo de 2021
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.exposicionCanina.mundo;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


/**
 * Es la clase que se encarga de manejar, organizar, cargar y salvar los perros. <br>
 */
public class ExposicionPerros {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Este objeto representa la base de datos con los perros
     */
    private Dao<Perro, String> perros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de perros vacío.
     */
    public ExposicionPerros(Dao<Perro, String> tablaPerros) {
        perros = tablaPerros;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de perros. La lista que se retorna no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     */
    public Dao<Perro, String> darPerros() {
        return this.perros;
    }

    /**
     * Busca un perro según su nombre y retorna el objeto Perro que tiene ese nombre. Retorna null si no encuentra
     * el perro con el nombre dado.
     */
    public Perro buscarPerro(String nombre) {
        Perro busc = null;
        try {
            busc = perros.queryForId(nombre);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (busc != null){
            return busc;
        }
        else{
            return null;
        }

        // TODO: Busca un perro según su nombre y retorna el objeto Perro que tiene ese nombre. Retorna null si no encuentra el perro

    }

    /**
     * Agrega un nuevo perro a la exposición. <br>
     * <b>post: </b> El perro fue agregado a la exposición si no existe otro perro con el mismo nombre.
     */
    public boolean agregarPerro(String nombreP, String razaP, String imagenP, int puntosP, int edadP) {
        Perro perroBuscado = buscarPerro(nombreP);
        boolean agregado = false;
        if (perroBuscado == null) {
            Perro nuevoPerro = new Perro(nombreP, razaP, imagenP, puntosP, edadP);
            try {
                perros.create(nuevoPerro);
                agregado = true;
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return agregado;
    }

    /**
     * Busca el perro que tenga el mayor puntaje en la exposición.
     */
    public Perro buscarPerroMayorPuntaje() {
        Perro pMayorPuntaje = null;
        int MayorPuntaje = 0;
        for (Perro p: perros){
            if (p.darPuntos() > MayorPuntaje){
                MayorPuntaje = p.darPuntos();
                pMayorPuntaje = p;
            }


        }



        // TODO: Busca el perro que tenga el mayor puntaje en la exposición.

        return pMayorPuntaje;
    }

    /**
     * Busca el perro que tenga el menor puntaje en la exposición.
     */
    public Perro buscarPerroMenorPuntaje() {
        Perro pMenorPuntaje = null;

        int MenorPuntaje = 9999;
        for (Perro p: perros) {
            if (p.darPuntos() < MenorPuntaje) {
                MenorPuntaje = p.darPuntos();
                pMenorPuntaje = p;
            }
        }
        // TODO: Busca el perro que tenga el mayor puntaje en la exposición.

        return pMenorPuntaje;
    }

    /**
     * Busca el perro que tenga la mayor edad.
     */
    public Perro buscarPerroMayorEdad() {
        Perro pMayorEdad = null;

        int MayorEdad = 0;
        for (Perro p: perros){
            if (p.darEdad() > MayorEdad){
                MayorEdad = p.darEdad();
                pMayorEdad = p;
            }


        }

        // TODO: Busca el perro que tenga la mayor edad.

        return pMayorEdad;
    }


    /**
     * Obtiene la cantidad de perros que pertenencen a la raza dada que se recibe como parámetro
     */
    public int contarPerrosRaza(String razaPerro) {
        int cont = 0;
        for (Perro p: perros){
            if (p.darRaza().equals(razaPerro) ){
                cont = cont + 1;

            }


        }

        // TODO: Obtiene la cantidad de perros que pertenencen a la raza dada que se recibe como parámetro

        return cont;
    }

    /**
     * Permite eliminar de la base de datos el perro que tiene el nombre dado
     */
    public void eliminarPerro(String nombreDelPerro) throws SQLException {

        try {
            Perro eliminar = buscarPerro(nombreDelPerro);
            perros.delete(eliminar);

        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        // TODO: elimina de la base de datos el perro que tiene el nombre dado
    }

    /**
     * Permite cambiarle los puntos asociados al perro con el nombre dado
     */
    public void cambiarPuntosPerro(String nombrePerro, int nuevosPuntos) throws SQLException {
        try {
            Perro modificar = buscarPerro(nombrePerro);
            modificar.cambiarPuntos(nuevosPuntos);
            perros.update(modificar);


        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        // TODO: cambia los puntos asociados al perro con el nombre dado
    }

    /**
     * Su misión es encontrar el primer perro en la base de datos que tenga una edad superior o igual a
     * la edad que se pasa como parámetro
     */
    public Perro buscarPerroPorEdad(int edad) {

        for (Perro p: perros){
            if (p.darEdad()>= edad){
                return p;


            }
        }
                ;

        // TODO: encuentra el primer perro que tenga una edad superior o igual a la edad que se pasa como parámetro
        return null;
    }
}