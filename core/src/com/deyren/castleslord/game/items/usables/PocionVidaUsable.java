/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.usables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.usablesabstractos.PocionVidaUsableAbstracta;
import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class PocionVidaUsable extends PocionVidaUsableAbstracta{

    public PocionVidaUsable(float x,float y, int cantidadDeCura) {
        super(new Sprite(Constantes.Imagenes.Items.POCION_VIDA_1,
                new Rectangle(x, y, Constantes.anchoDeLosItemsEnElEscenario, Constantes.altoDeLosItemsEnElEscenario)),cantidadDeCura);
    }

    
    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
      sprite.dibujar(batch, camara);
    }

    @Override
    public void usar(Personaje personaje) {
      personaje.CambiarVida(cantidadDeCura);
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
       sprite.dibujar(batch, camara,rotacion);
    }

    
    
    
    

}
