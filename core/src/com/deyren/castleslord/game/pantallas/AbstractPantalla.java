/*
 * @Ruben@
 */

package com.deyren.castleslord.game.pantallas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deyren.castleslord.game.Mapa;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.arrays.ArrayDeItems;
import com.deyren.castleslord.game.arrays.ArrayDeUsables;
import com.deyren.castleslord.game.arrays.ConjuntoDeSprites;

/**
 *Representa una pantalla de juego, Un escenario con todo.
 * @author Ruben
 */
public abstract class AbstractPantalla {
    protected Mapa mapa;
    protected ArrayDeEnemigos enemigos;
    protected ArrayDeItems items;//Items que hay por la pantalla
    protected ArrayDeUsables usables;
    //protected ConjuntoDeSprites todosLosSprites[];
    protected OrthographicCamera camara;

    public AbstractPantalla(Mapa mapa, ArrayDeEnemigos enemigos, ArrayDeItems items,ArrayDeUsables usables, OrthographicCamera camara) {
        this.mapa = mapa;
        this.enemigos = enemigos;
        this.items = items;
        this.usables=usables;
        //this.todosLosSprites = todosLosSprites;
        this.camara = camara;
    }
   
    
    
    public abstract void actualizar();
    public abstract void dibujarMapa(SpriteBatch batch);
    public abstract void dibujarObjetos(SpriteBatch batch,OrthographicCamera camara);
    public abstract void dibujarFueraDelBatch(OrthographicCamera camara);
    
}
