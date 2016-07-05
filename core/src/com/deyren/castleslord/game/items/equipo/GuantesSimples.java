/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.equipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.mejoras.Aturdir;
import com.deyren.castleslord.game.personajes.Sprite;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public class GuantesSimples extends AbstractGuantes {

    public GuantesSimples(float x, float y) {
        super(new Sprite(Constantes.Imagenes.Equipo.GUANTES_SIMPLES,
                new Rectangle(x, y, Constantes.anchoDeLosItemsEnElEscenario, Constantes.altoDeLosItemsEnElEscenario)));

        nombre = "Guantes simples";
        atributos = new AtributosDeEquipo();
        atributos.armadura = 4;
        atributos.fuerza = 2;
        atributos.inteligencia = 1;
        atributos.manaMaximo = 1;
        atributos.velocidadDeAtaque = 0.8f;
        atributos.velocidadDeMovimiento = 0.0002f;
        atributos.vidaMaxima = 1;
        mejora = new Aturdir();
    }
 

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        sprite.dibujar(batch, camara);
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        sprite.dibujar(batch, camara, rotacion);
    }

}
