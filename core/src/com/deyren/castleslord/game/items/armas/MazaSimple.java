/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.armas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.items.armasabstracto.AbstractMaza;
import com.deyren.castleslord.game.items.mejoras.CuraVidaCadaGolpe;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.personajes.SpriteAnimado;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class MazaSimple extends AbstractMaza{

    public MazaSimple(float x,float y, ArrayDeEnemigos enemigos ) {
           super(new Sprite(Constantes.Imagenes.Equipo.Armas.MAZA_SIMPLE,
                   new Rectangle(x, y,Constantes.anchoDeLasArmasEnElEscenario, Constantes.altoDeDeLasArmaEnElEscenario)), enemigos);
        nombre = "Maza Simple";
        atributos = new AtributosDeEquipo();
        atributos.armadura = 4;
        atributos.fuerza = 3;
        atributos.inteligencia = 0;
        atributos.manaMaximo = 0;
        atributos.velocidadDeAtaque =- 0.06f;
        atributos.velocidadDeMovimiento = -0.06f;
        atributos.vidaMaxima = 0;
        danio=18;
        mejora = new CuraVidaCadaGolpe();
    }
    
 @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        if (equipada) {
            sprite.getRectangulo().y = PersonajeQueLoLleva.rectangulo.y;
            if (PersonajeQueLoLleva.getMirandoHacia() == SpriteAnimado.MirandoHacia.Derecha) {
                sprite.getRectangulo().x = PersonajeQueLoLleva.rectangulo.x + PersonajeQueLoLleva.rectangulo.width - 20;

                sprite.dibujar(batch, camara, -90);
            } else {
                sprite.getRectangulo().x = PersonajeQueLoLleva.rectangulo.x - 20;
                sprite.dibujar(batch, camara, 90);
            }

        } else {
            sprite.dibujar(batch, camara);
        }

    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        if (equipada) {
            sprite.getRectangulo().y = PersonajeQueLoLleva.rectangulo.y;
            if (PersonajeQueLoLleva.getMirandoHacia() == SpriteAnimado.MirandoHacia.Derecha) {
                sprite.getRectangulo().x = PersonajeQueLoLleva.rectangulo.x + PersonajeQueLoLleva.rectangulo.width - 20;
                sprite.dibujar(batch, camara, -90 - rotacion);
            } else {
                sprite.getRectangulo().x = PersonajeQueLoLleva.rectangulo.x + 10;
                sprite.dibujar(batch, camara, 90 + rotacion);
            }

        } else {
            sprite.dibujar(batch, camara);
        }

    }
    
    
    

}
