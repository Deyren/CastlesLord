/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items.mejoras;

import com.deyren.castleslord.game.arrays.ArrayDeMejoras.ListaDeMejoras;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Personaje;

import java.util.Random;

/**
 *
 * @author Ruben
 */
public class GolpeCritico extends Mejora  {

    private static final float NIVEL1[] = new float[2];
    private static final float NIVEL2[] = new float[2];
    private static final float NIVEL3[] = new float[2];
    private static final float NIVEL4[] = new float[2];

    public GolpeCritico() {
        super(ListaDeMejoras.GolpeCritico);
        NIVEL1[0] = 0.12f;//12 % de posibilidad 
        NIVEL1[1] = 0.15f;// de quitar un 15% mas de daño

        NIVEL2[0] = 0.24f;//24 % de posibilidad 
        NIVEL2[1] = 0.3f;// de quitar un 30% mas de daño

        NIVEL3[0] = 0.40f;//40 % de posibilidad 
        NIVEL3[1] = 0.5f;// de quitar un 50% mas de daño

        NIVEL4[0] = 0.66f;//66 % de posibilidad 
        NIVEL4[1] = 0.8f;// de quitar un 80% mas de daño

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


    private void caso(Personaje p, float[] niv) {
        if (new Random().nextFloat() < niv[0]) {
            p.atributos.daño += p.atributos.daño * niv[1];
            //System.out.println(p.atributos.daño);
        }
    }

}
