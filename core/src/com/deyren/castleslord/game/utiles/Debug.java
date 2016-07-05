/*
 * @Ruben@
 */

package com.deyren.castleslord.game.utiles;

/**
 *
 * @author Ruben
 */
public class Debug {
    /**
     * Muestra un string en la consola mas el nombr de la clase en la que esta el String,
     * poner this, en segundo parametro, Asi al mostrar algo sabe donde esta escrito ese System.out.println
     * @param str
     * @param obj 
     */
    public static<T> void Print(Object str,T obj){
        System.out.println(str+"\tClase: "+obj.getClass().getSimpleName());
    }
}
