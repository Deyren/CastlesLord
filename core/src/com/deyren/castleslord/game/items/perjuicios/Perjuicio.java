/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.perjuicios;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deyren.castleslord.game.arrays.ArrayDePerjuicios.ListaDePerjuicios;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * @author Ruben
 */
public abstract class Perjuicio {

    protected final Personaje personajeDeEstePerjuicio;
    protected ListaDePerjuicios tipo;
    protected PerjuicioListener listener;
    protected int posicionEnElArray;//guardar la posicion en la que esta para luego mandarlo en el listener al array cuando termine

    public void setPerjuicioListener(PerjuicioListener l) {
        listener = l;
    }

    public ListaDePerjuicios getTipo() {
        return tipo;
    }

    public void setPosicionEnElArray(int posicionEnElArray) {
        this.posicionEnElArray = posicionEnElArray;
    }

    public Perjuicio(ListaDePerjuicios tipo, Personaje personajeDeEstePerjuicio) {
        this.tipo = tipo;
        this.personajeDeEstePerjuicio = personajeDeEstePerjuicio;

    }

    public abstract void aplicarPerjuicio();

    protected abstract void QuitarPerjuicio();

    public abstract void dibujar(SpriteBatch batch);

    public static interface PerjuicioListener {

        public void perjuicioTerminado(Perjuicio p, int posicionEnElAray);
    }

}
