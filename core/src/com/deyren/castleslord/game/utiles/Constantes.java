/*
 * @Ruben@
 */
package com.deyren.castleslord.game.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.io.File;

/**
 *
 * @author Ruben
 */
public class Constantes { /**
     * ancho por defecto para los personajes
     */
    public static final Float anchoDeSpriteDeEnemigo = 0.064f* Gdx.graphics.getWidth();
    /**
     * alto por defecto para los personajes
     */
    public static final Float altoDeSpriteDeEnemigo = 0.096f*Gdx.graphics.getHeight();
    //______________________________________________________________________________________________________________________//
    // Ancho y alto de los items visibles en el escenario
    public static final Integer anchoDeLosItemsEnElEscenario = 32;
    public static final Integer altoDeLosItemsEnElEscenario = 32;

    // Ancho y alto de las armas visibles en el escenario, para que no queden cuadradas
    public static final Integer anchoDeLasArmasEnElEscenario = 16;
    public static final Integer altoDeDeLasArmaEnElEscenario = 64;

    //________________________________________________________________________//
    
    public static enum Personajes{ Personaje1}
    
    public static abstract class Imagenes {

        public static abstract class Personajes {

            public static final TextureAtlas PERSONAJE_1 = new TextureAtlas("personaje" + File.separator + "pruebaAnim" + File.separator + "protagonista.pack");
        }

        public static abstract class Items {

            public static final Texture POCION_VIDA_1 = new Texture("usables/pocionVida.png");
        }

        public static abstract class Equipo {

            public static final Texture PECHO_SIMPLE = new Texture("equipo/pechoSimple.png");
            public static final Texture CASCO_SIMPLE = new Texture("equipo/cascoSimple.png");
            public static final Texture GUANTES_SIMPLES = new Texture("equipo/guantesSimples.png");

            public static abstract class Armas {

                public static final Texture ESPADA_SIMPLE = new Texture("equipo/armas/espadaSimple.png");
                public static final Texture MAZA_SIMPLE = new Texture("equipo/armas/mazaSimple.png");
                public static final Texture HACHA_SIMPLE = new Texture("equipo/armas/hachaSimple.png");
            }
        }
    }
}
