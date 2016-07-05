/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.mejoras;

import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Personaje;
import java.util.Random;

/**
 *
 * @author Ruben
 */
public class Aturdir extends Mejora {

    //probabilidad de aturdir.   Aturdirá siempre el mismo tiempo
    private static final int NIVEL1[] = new int[2];

    private static final int NIVEL2[] = new int[2];
    private static final int NIVEL3[] = new int[2];
    private static final int NIVEL4[] = new int[2];

    public Aturdir() {
        super(ArrayDeMejoras.ListaDeMejoras.ProvabilidadAturdir);
        NIVEL1[0] = 8; // 8% 
        NIVEL1[1] = 500; // de aturdir durante medio segundo

        NIVEL2[0] = 14; // 14% 
        NIVEL2[1] = 1000; // de aturdir durante 1 segundo

        NIVEL3[0] = 20;
        NIVEL3[1] = 1000;

        NIVEL4[0] = 32;
        NIVEL4[1] = 1000;

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

    private void caso(Personaje p, int[] nivel) {
        if(new Random().nextInt(100)<nivel[0]){
             p.getPerjuicios().aturdir(nivel[1]);
        }
       
    }

}
