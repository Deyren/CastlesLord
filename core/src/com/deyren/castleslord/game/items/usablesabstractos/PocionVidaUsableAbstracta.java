/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.usablesabstractos;

import com.deyren.castleslord.game.items.Usable;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 *Pocion de vida que se usa en cuanto se coje. Como todo lo que herede de usable
 * @author Ruben
 */
public abstract class PocionVidaUsableAbstracta extends Usable{
    protected int cantidadDeCura;

    public PocionVidaUsableAbstracta(Sprite sprite,int cantidadDeCura) {
        super(sprite);
        this.cantidadDeCura=cantidadDeCura;
    }
}
