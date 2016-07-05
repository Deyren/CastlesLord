/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.perjuicios;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deyren.castleslord.game.arrays.ArrayDePerjuicios;
import com.deyren.castleslord.game.personajes.Personaje;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruben
 */
public class Aturdido extends Perjuicio {

    private long tiempoAturdido;

    public Aturdido(Personaje personajeDeEstePerjuicio, long tiempoAturdido) {
        super(ArrayDePerjuicios.ListaDePerjuicios.Aturdido, personajeDeEstePerjuicio);
        this.tiempoAturdido = tiempoAturdido;
    }

    @Override
    public void aplicarPerjuicio() {
        if (!personajeDeEstePerjuicio.isAturdido()) {
            personajeDeEstePerjuicio.setAturdido(true);
            //Debug.Print("Aturdido", this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(tiempoAturdido);
                        Aturdido.this.QuitarPerjuicio();
                        listener.perjuicioTerminado(Aturdido.this, posicionEnElArray);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Aturdido.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }).start();
        }

    }

    @Override
    protected void QuitarPerjuicio() {
        if (personajeDeEstePerjuicio != null) {
            personajeDeEstePerjuicio.setAturdido(false);
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
