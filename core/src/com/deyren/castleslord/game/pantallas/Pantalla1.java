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
 *
 * @author Ruben
 */
public class Pantalla1 extends AbstractPantalla {

    public Pantalla1(Mapa mapa, ArrayDeEnemigos enemigos, ArrayDeItems items,ArrayDeUsables usables, OrthographicCamera camara) {
        super(mapa, enemigos, items,usables, camara);
    }

    @Override
    public void actualizar() {
      
    }

    /**
     * El metodo debe estar fuera del batch.begin y end
     * @param batch 
     */
    @Override
    public void dibujarMapa(SpriteBatch batch) {
        mapa.render(batch, camara);
    }

    @Override
    public void dibujarObjetos(SpriteBatch batch,OrthographicCamera camara) {
        enemigos.dibujar(batch,camara);        
        items.dibujar(batch,camara);
        usables.dibujar(batch, camara);
    }
    

    /**
     * Metodo para dibujar sin usar el batch. Como las barras de vida de los enemigos
     * Debe esta fuera del bach.begin y end
     */
    @Override
    public void dibujarFueraDelBatch( OrthographicCamera camara) {
       enemigos.dibujarBarrasDeVida(camara);
    }

}
