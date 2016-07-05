/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.mejoraabstracta;

import com.deyren.castleslord.game.arrays.ArrayDeMejoras.ListaDeMejoras;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * @author Ruben
 */
public abstract class Mejora {
    protected ListaDeMejoras tipo;
    protected int nivel=1;

    public int getNivel() {
        return nivel;
    }
    
    public ListaDeMejoras getTipo() {
        return tipo;
    }
    public Mejora(ListaDeMejoras tipo) {
       this.tipo=tipo;
    }
    
    /**
     * Ejecuta la mejora, cambiando los atributos del personaje que se le pasa.
     * Cada mejora implementa el suyo.
     * @param p 
     */
    public abstract void activar(Personaje p);
    
    @Override
    public String toString() {
        return tipo.name();
    }
        
   
    
    
}
