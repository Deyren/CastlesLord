/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.mejoras;

import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * Esta mejora salta cuando un enemigo ataca al personaje. <br>
 * la clase ArrrayDeEnemigos es la que lo usa, cuando detecta que un enemigo ataca.
 * @author Ruben
 */
public class ArmaduraDePinchos extends Mejora {
    
    private static final int NIVEL1 = 12;
    private static final int NIVEL2 = 24;
    private static final int NIVEL3 = 48;
    private static final int NIVEL4 = 80;
    
    public ArmaduraDePinchos(ArrayDeMejoras.ListaDeMejoras tipo) {
        super(tipo);
    }

    /**
     * Hay que pasarle al personaje enemigo, que es el que recibe daño.
     *
     * @param p
     */
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
        p.CambiarVida((int) (-p.atributos.daño * nivel / 100));
        
    }
}
