/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.consumible;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.consumibleabstracto.AbstractPocion;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class PocionVida extends AbstractPocion{

    public PocionVida(float x,float y) {  
        super(new Sprite(Constantes.Imagenes.Items.POCION_VIDA_1, new Rectangle(x,y,Constantes.anchoDeLosItemsEnElEscenario,Constantes.altoDeLosItemsEnElEscenario)));
    }

    @Override
    public void usar() {
     listener.usado(this);
    }

    @Override
    public void dibujar(SpriteBatch batch,OrthographicCamera camara) {
     sprite.dibujar(batch,camara);
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
     sprite.dibujar(batch, camara,rotacion);
    }

}
