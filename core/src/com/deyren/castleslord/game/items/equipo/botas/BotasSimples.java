/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.equipo.botas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class BotasSimples extends AbstractBotas{

    public BotasSimples(float x,float y) {
        super(new Sprite(new Texture("equipo/botasSimples.png"), 
                new Rectangle(x, y, Constantes.anchoDeLosItemsEnElEscenario, Constantes.altoDeLosItemsEnElEscenario)));      
           nombre="Botas simples";
          atributos=new AtributosDeEquipo();
          atributos.armadura=1;
          atributos.fuerza=2;
          atributos.inteligencia=1;
          atributos.manaMaximo=2;
          atributos.velocidadDeAtaque=0.4f;
          atributos.velocidadDeMovimiento=3.6f;
          atributos.vidaMaxima=2;
          mejora=null;
    }

   

    @Override
    public void dibujar(SpriteBatch batch,OrthographicCamera camara) {
        sprite.dibujar(batch,camara);
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
      sprite.dibujar(batch,camara,rotacion);
    }

}
