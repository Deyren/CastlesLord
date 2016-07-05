/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.personajes.Sprite;

/**
 *Objetos que no se pueden equipar pero se pueden poner en el 
 * inventario para usarlos. <br>
 * @author Ruben
 */
public abstract class Consumible extends AbstractItem {

    protected ConsumibleListener listener;
    public void setConsumibleListener(ConsumibleListener l){
        listener=l;
    }
    public Consumible(Sprite sprite) {
        super(sprite);
    }
    
    public abstract void usar();
    
    public static interface ConsumibleListener{
        public void usado(Consumible consumible);
    }
}
