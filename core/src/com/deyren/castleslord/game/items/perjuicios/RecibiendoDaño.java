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
public class RecibiendoDaño extends Perjuicio {

    private int cantidadDeVida, tick;
    private long duracion;

    /**
     *
     * @param tipo
     * @param personajeDeEstePerjuicio
     * @param cantidadDeVida Cantidad total de vida a quitar
     * @param duracion
     * @param tick tiempo que espera cada vez que quita vida
     */
    public RecibiendoDaño(ArrayDePerjuicios.ListaDePerjuicios tipo, Personaje personajeDeEstePerjuicio, int cantidadDeVida, long duracion, int tick) {
        super(tipo, personajeDeEstePerjuicio);
        if (duracion < tick) {
            throw new IllegalArgumentException("La duracion no puede ser menor que los ticks");
        }
        this.duracion = duracion;
        this.tick = tick;
        this.cantidadDeVida = cantidadDeVida / tick;
    }

    @Override
    public void aplicarPerjuicio() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                duracion += System.currentTimeMillis();//suma el tiempo actual a lo que dura
                while (System.currentTimeMillis() < duracion) {//mientra sea menor...
                    if (personajeDeEstePerjuicio != null) {
                        personajeDeEstePerjuicio.CambiarVida(cantidadDeVida);
                        try {
                            Thread.sleep(tick);//espera hasta el siguiente tick
                        } catch (InterruptedException ex) {
                            Logger.getLogger(RecibiendoDaño.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{//si el personaje es null, puede terminar el bucle
                          break;
                    }
                }               
                listener.perjuicioTerminado(RecibiendoDaño.this, posicionEnElArray);
            }
        }).start();
    }
    

    //de momento no necesita implementar esto
    @Override
    protected void QuitarPerjuicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
