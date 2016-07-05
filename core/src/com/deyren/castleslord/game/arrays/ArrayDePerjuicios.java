/*
 * @Ruben@
 */
package com.deyren.castleslord.game.arrays;

import com.deyren.castleslord.game.items.perjuicios.Aturdido;
import com.deyren.castleslord.game.items.perjuicios.Perjuicio;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * @author Ruben
 */
public class ArrayDePerjuicios implements Perjuicio.PerjuicioListener {

    public static enum ListaDePerjuicios {

        Aturdido
    }

    private final Perjuicio[] perjuicios;
    private Personaje personajeQueLoContiene;

    public ArrayDePerjuicios(Personaje personajeDeEstePerjuicio) {

        this.personajeQueLoContiene = personajeDeEstePerjuicio;
        this.perjuicios = new Perjuicio[5];

    }

    public void aturdir(long milisegundos) {
        for (int i = 0; i < perjuicios.length; i++) {
            if (perjuicios[i] == null) {
                Aturdido p = new Aturdido(personajeQueLoContiene,milisegundos);
                p.setPosicionEnElArray(i);
                p.setPerjuicioListener(this);
                perjuicios[i] = p;
                perjuicios[i].aplicarPerjuicio();
                break;
            }
        }
    }

    @Override
    public void perjuicioTerminado(Perjuicio p, int posicionEnElArray) {
        perjuicios[posicionEnElArray] = null;
    }

}
