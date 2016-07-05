/*
 * @Ruben@
 */

package com.deyren.castleslord.game.arrays;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.items.Usable;

/**
 *Array de objetos usables del escenario. Usables son los que se usan sin guardarse en el inventario
 * @author Ruben
 */
public class ArrayDeUsables{
        Usable[] usables;

    public ArrayDeUsables(int cantidad) {
        usables=new Usable[cantidad];
    }
        
        public void addUsable(Usable item) {
       
        for (int i = 0; i < usables.length; i++) {
            if (usables[i] == null) {
                usables[i] = item;
                break;
            }
        }
    }

    public void addUsable(Usable item, int posicion) {
        
        this.usables[posicion] = item;
    }

    public void removeUsable(Usable u){
        for (int i = 0; i < usables.length; i++) {
            if(u.equals(usables[i])){
                usables[i]=null;
            }
        }
    }
    public Usable chocaCon(Rectangle rect) {
        Usable salida = null;
        for (int i = 0; i < usables.length; i++) {
            if (usables[i] != null) {
                if (usables[i].getSprite().getRectangulo().overlaps(rect)) {
                    salida = (Usable) usables[i];
                    break;
                }
            }
        }
        return salida;
    }
    
    public void dibujar(SpriteBatch batch,OrthographicCamera camara){
     for (Usable u : usables) {
            if (u != null) {
                u.dibujar(batch, camara);
            }
        }
}

}
