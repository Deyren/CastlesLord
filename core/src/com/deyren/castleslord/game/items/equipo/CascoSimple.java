/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.equipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.mejoras.CuraManaCadaGolpe;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class CascoSimple extends AbstractCasco {

    public CascoSimple(float x,float y) {
        super(new Sprite(Constantes.Imagenes.Equipo.CASCO_SIMPLE, 
                new Rectangle(x, y, Constantes.anchoDeLosItemsEnElEscenario, Constantes.altoDeLosItemsEnElEscenario)));
          nombre="Casco simple";
          atributos=new AtributosDeEquipo();
          atributos.armadura=1;
          atributos.fuerza=1;
          atributos.inteligencia=1;
          atributos.manaMaximo=1;
          atributos.velocidadDeAtaque=0.3f;
          atributos.velocidadDeMovimiento=0.00002f;
          atributos.vidaMaxima=3;
          mejora=new CuraManaCadaGolpe();
    }

    

    @Override
    public void dibujar(SpriteBatch batch,OrthographicCamera camara) {
       if(this.equipada){
            sprite.getRectangulo().x=PersonajeQueLoLleva.rectangulo.x+16;
            sprite.getRectangulo().y=PersonajeQueLoLleva.rectangulo.y;
            sprite.dibujar(batch,camara);
        }else{
            sprite.dibujar(batch,camara);
        }
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        if(this.equipada){
            sprite.getRectangulo().x=PersonajeQueLoLleva.rectangulo.x+16;
            sprite.getRectangulo().y=PersonajeQueLoLleva.rectangulo.y+PersonajeQueLoLleva.rectangulo.height-24;
            sprite.dibujar(batch,camara,0);
        }else{
            sprite.dibujar(batch,camara,rotacion);
        }
            
    }

  
    
}
