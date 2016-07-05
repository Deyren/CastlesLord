/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Ruben
 */
public interface Item {
    public void dibujar(SpriteBatch batch, OrthographicCamera camara);
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion);
}
