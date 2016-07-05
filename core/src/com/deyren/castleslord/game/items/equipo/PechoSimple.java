/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.equipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.items.Armadura;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.items.mejoras.ArmaduraDePinchos;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class PechoSimple extends AbstractPecho {
public PechoSimple(float x,float y) {
        super(new Sprite(Constantes.Imagenes.Equipo.PECHO_SIMPLE,
             new Rectangle(x, y, Constantes.anchoDeLosItemsEnElEscenario, Constantes.altoDeLosItemsEnElEscenario))); 
          nombre="Pecho simple";
          atributos=new Armadura.AtributosDeEquipo();
          atributos.armadura=3;
          atributos.fuerza=2;
          atributos.inteligencia=3;
          atributos.manaMaximo=1;
          atributos.velocidadDeAtaque=10.3f;
          atributos.velocidadDeMovimiento=0.0003f;
          atributos.vidaMaxima=100;
          mejora=new ArmaduraDePinchos(ArrayDeMejoras.ListaDeMejoras.ArmaduraPinchos);
    }

@Override
    public void dibujar(SpriteBatch batch,OrthographicCamera camara) {
       if(this.equipada){
            sprite.getRectangulo().x=PersonajeQueLoLleva.rectangulo.x+16;
            sprite.getRectangulo().y=PersonajeQueLoLleva.rectangulo.y+PersonajeQueLoLleva.rectangulo.height-60;
            sprite.dibujar(batch,camara,0);
        }else{
            sprite.dibujar(batch,camara);
        }
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        if(this.equipada){
            
            sprite.getRectangulo().x=PersonajeQueLoLleva.rectangulo.x+16;
            sprite.getRectangulo().y=PersonajeQueLoLleva.rectangulo.y+PersonajeQueLoLleva.rectangulo.height-60;
            sprite.dibujar(batch,camara,0);
        }else{
            sprite.dibujar(batch,camara,rotacion);
        }
            
    }
}
