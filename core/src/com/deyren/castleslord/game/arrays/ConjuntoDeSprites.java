/*
 * @Ruben@
 */
package com.deyren.castleslord.game.arrays;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *Se usa para crear un array de este tipo y poder
 * meter tanto sprites como items
 * y asi poder mover todo lo que haya al mover la camara
 * @author Ruben
 */
public interface ConjuntoDeSprites {
      public void mover(float cantidad, boolean xOy);
     public void dibujar(SpriteBatch batch, OrthographicCamera camara);
}
