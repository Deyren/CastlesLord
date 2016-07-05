/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.personajes.Sprite;

/**
 *Clase base de el equipo y los items, como pociones y demas...
 * @author Ruben
 */
public abstract class AbstractItem implements Item{
    protected String nombre;
     protected Sprite sprite;
     
     protected ItemListener listener;
     public void setItemListener(ItemListener listener){
         this.listener=listener;
     }
     
     /**
      * Ejecuta el metodo itemCogido del listener.
      * Que a su vez es recogido por el arrayDeItems
      * para borrarlo del array.
      * 
      */
     public void cogerItem(){
         listener.itemCogido(this);
     }
     
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public AbstractItem(Sprite sprite) {
        this.sprite = sprite;
    }
    // Implementada en ArrayDeItems
    public static interface ItemListener{
        public void itemCogido(AbstractItem item);
        public void itemDesaparece(AbstractItem item);
    }
       @Override
    public String toString() {
       return nombre;
    }
}
