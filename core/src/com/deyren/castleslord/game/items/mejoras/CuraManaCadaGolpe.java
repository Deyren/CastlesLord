/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.mejoras;

import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * @author Ruben
 */
public class CuraManaCadaGolpe extends Mejora {

    private static final int NIVEL1 = 15;
    private static final int NIVEL2 = 30;
    private static final int NIVEL3 = 45;
    private static final int NIVEL4 = 60;

    public CuraManaCadaGolpe() {
        super(ArrayDeMejoras.ListaDeMejoras.CurarManaCadaGolpe);
    }

    @Override
    public void activar(Personaje p) {
        switch (nivel) {
            case 1:

                caso(p, NIVEL1);
                break;
            case 2:
                caso(p, NIVEL2);
                break;
            case 3:
                caso(p, NIVEL3);
                break;
            case 4:
                caso(p, NIVEL4);
                break;
        }
    }
    
    private void caso(Personaje p, float nivel) {
        p.CambiarMana((int) (p.atributos.daño * nivel / 100));
    }

}
