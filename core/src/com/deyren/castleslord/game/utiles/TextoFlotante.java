/*
 * @Ruben@
 */
package com.deyren.castleslord.game.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Ruben
 */
public class TextoFlotante {

    private final BitmapFont font = new BitmapFont();
    private final String texto;
    private final Color color;
    private final float posX;
    private float posY;
    private float posYFinal;
    private final int posicionEnElArray;

    private TextoFlotanteListener listener;

    public void setTextoFlotanteListener(TextoFlotanteListener l) {
        listener = l;
    }

    /**
     * 
     * @param texto
     * @param color
     * @param posX
     * @param posY
     * @param distancia La distancia que recorre el texto hacia arriba
     * @param posicionEnElArray  un identificador por si el texto esta en un array. se le pasa la posicion en la que se a añadido al array, para que luego lo devuelva en el evento
     */
    public TextoFlotante(String texto, Color color, float posX, float posY, float distancia, int posicionEnElArray) {
        this.texto = texto;
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.posYFinal = posY + distancia;
        this.posicionEnElArray = posicionEnElArray;
    }

    public void dibujar(SpriteBatch batch) {
        if (posY < posYFinal) {
            font.setColor(color);
            font.draw(batch, texto, posX, posY++);
        } else {
            listener.terminado(posicionEnElArray);
        }

    }

    public static interface TextoFlotanteListener {
        public void terminado(int posicionEnElArray);
    }
}
