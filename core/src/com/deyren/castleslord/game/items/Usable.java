/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 * Clase base de los objetos que al cogerlos es usan automaticamente Como
 * pociones o cosas que aumenten los atributos pero no se puedan meter en el
 * inventario.
 *
 * @author Ruben
 */
public abstract class Usable implements Item {

    protected String nombre;
    protected Sprite sprite;

    public Usable(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void usar(Personaje personaje);

    
       @Override
    public String toString() {
       return nombre;
    }
}
