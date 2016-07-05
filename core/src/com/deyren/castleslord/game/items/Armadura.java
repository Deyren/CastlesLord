/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.items.mejoras.GolpeCritico;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 *
 * @author Ruben
 */
public abstract class Armadura extends Equipable{

    protected AtributosDeEquipo atributos;
    

 

    public Armadura(Sprite sprite) {
        super(sprite);
        mejora=new GolpeCritico();
    }

    public AtributosDeEquipo getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributosDeEquipo atributos) {
        this.atributos = atributos;
    }
   
}
