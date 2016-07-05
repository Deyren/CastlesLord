/*
 * @Ruben@
 */
package com.deyren.castleslord.game.arrays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Personaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Array con las mejoras que tiene el personaje en un momento dado. <br>
 * Las mejoras las tiene el personaje y se asignan segun el equipo que tenga.
 * <br>
 * El equipo es quien implementa cualquier mejora.
 *
 * @author Ruben
 */
public final class ArrayDeMejoras {

    public static enum ListaDeMejoras {

        GolpeCritico, ArmaduraPinchos, CurarVidaCadaGolpe, CurarManaCadaGolpe,
        ProvabilidadAturdir, VelocidadDeAtaqueTemporal, DanioEnElTiempo,
        CuraEnElTiempo, DanioDeArea

    }

    public void usarGolpeCritico(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.GolpeCritico);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarArmaduraDePinchos(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.ArmaduraPinchos);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarCurarVidaCadaGolpe(Personaje p) {
        
        
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.CurarVidaCadaGolpe);
        
//         Iterator it = mejoras.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            if (pair.getValue() != null) {
//                mms = (ArrayList<Mejora>) pair.getValue();
//                Debug.Print(mms, this);
//                if (!mms.isEmpty()) {
//                    mms.get(0).activar(p);
//                }
//            }
//        }
        
        if (mms != null && ! mms.isEmpty()) {            
            mms.get(0).activar(p);
        }
    }

    public void usarCurarManaCadaGolpe(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.CurarManaCadaGolpe);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarProvabilidadAturdir(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.ProvabilidadAturdir);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarVelocidadDeAtaqueTemporal(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.VelocidadDeAtaqueTemporal);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarDanioEnElTiempo(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.DanioEnElTiempo);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarCuraEnElTiempo(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.CuraEnElTiempo);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    public void usarDanioDeArea(Personaje p) {
        ArrayList<Mejora> mms = mejoras.get(ListaDeMejoras.DanioDeArea);
        if (mms != null && !mms.isEmpty()) {
            mms.get(0).activar(p);
        }
    }

    private final HashMap<ListaDeMejoras, ArrayList< Mejora>> mejoras;

    public ArrayDeMejoras() {
        this.mejoras = new HashMap<ListaDeMejoras, ArrayList<Mejora>>();
    }

    /**
     * Establece el objeto mejora del tipo que se le pasa
     *
     * @param mejora
     * @param obj
     */
    public void add(ListaDeMejoras mejora, Mejora obj) {
        if ( ! mejoras.containsKey(mejora)) {
            ArrayList<Mejora> m = new ArrayList<Mejora>();
            m.add(obj);
            mejoras.put(mejora, m);
        } else {
                mejoras.get(mejora).add(obj);
        }

    }

    /**
     * Usa todas las mejoras. <br>
     *
     * @param p
     */
    public void usarMejoras(Personaje p) {
//        ListaDeMejoras[] la = ListaDeMejoras.values();
//        for (int i = 0; i < la.length; i++) {
//            usarMejora(la[i], p);
//
//        }

        Iterator it = mejoras.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() != null) {
                ArrayList<Mejora> mms = (ArrayList<Mejora>) pair.getValue();
                if (!mms.isEmpty()) {
                    mms.get(0).activar(p);
                }
            }
        }

    }

    /**
     * Llama al metodo activar si la mejora existe
     *
     * @param mejora
     * @param p
     */
    public void usarMejora(ListaDeMejoras mejora, Personaje p) {
        if (mejoras.get(mejora) != null && !mejoras.get(mejora).isEmpty()) {
            Mejora m = mejoras.get(mejora).get(0);
            if (m != null) {
                m.activar(p);
            }
        }

    }

    /**
     * Borra la mejora que sea igual que la que se le pasa
     *
     * @param m
     */
    public void removeMejora(Mejora m) {
        mejoras.get(m.getTipo()).remove(m);
    }

    /**
     * true si contiene al menos una mejora de ese tipo. <br>
     * Primero busca si está la clave que coincide con el tipo pasado,. si está,
     * comprueba si el array para esa clave no esta vacio. <br>
     *
     * @param mejora tipo enum de mejora a buscar
     * @return True si se cumplen las 2 condiciones.
     */
    public boolean contains(ListaDeMejoras mejora) {
        if (mejoras.containsKey(mejora)) {
            if (!mejoras.get(mejora).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        Iterator it = mejoras.entrySet().iterator();
        int y = (int) pos.y + 20;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() != null) {
                ArrayList<Mejora> mms = (ArrayList<Mejora>) pair.getValue();
                if (!mms.isEmpty()) {
                    font.draw(batch, String.valueOf(mms.get(0)), pos.x, y += 20);
                }

            }

        }

    }

}
