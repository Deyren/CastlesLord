/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.armasabstracto;

import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.items.Arma;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 *
 * @author Ruben
 */
public abstract class AbstractHacha extends Arma{

    public AbstractHacha(Sprite sprite, ArrayDeEnemigos enemigos ) {
        super(sprite, enemigos);
    }

}
