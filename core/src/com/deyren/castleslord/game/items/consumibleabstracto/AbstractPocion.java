/*
 * @Ruben@
 */

package com.deyren.castleslord.game.items.consumibleabstracto;

import com.deyren.castleslord.game.items.Consumible;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 *
 * @author Ruben
 */
public abstract class AbstractPocion extends Consumible{
    public AbstractPocion(Sprite sprite) {
        super(sprite);
    }
    
    @Override
    public abstract void usar();
        
}
