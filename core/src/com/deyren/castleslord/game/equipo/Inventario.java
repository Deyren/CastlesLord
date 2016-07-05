/*
 * @Ruben@
 */
package com.deyren.castleslord.game.equipo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deyren.castleslord.game.items.AbstractItem;
import com.deyren.castleslord.game.items.Consumible;
import com.deyren.castleslord.game.items.Equipable;

import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class Inventario {

    private final Consumible consumibles[];
    private final Equipable equipo[];

    public Inventario(int cantidadDeConsumibles, int cantidadDeEquipo) {
        //items=new Item[cantidadDeItems];
        consumibles = new Consumible[cantidadDeConsumibles];
        equipo = new Equipable[cantidadDeEquipo];

    }

    /**
     * Agrega un objeto consumible al inventario. Si no tiene sitio en el
     * inventario devuelve false.
     *
     * @param c
     * @return true si tiene sitio.
     */
    public boolean addConsumible(Consumible c) {
        for (int i = 0; i < consumibles.length; i++) {
            if (consumibles[i] == null) {
                consumibles[i] = c;
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un objeto equipable al inventario. Si no tiene sitio en el
     * inventario devuelve false.
     *
     * @param c
     * @return true si tiene sitio.
     */
    public boolean addEquipable(Equipable c) {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == null) {
                equipo[i] = c;
                return true;
            }
        }
        return false;
    }

    /**
     * Borra un objeto Consumible del array que coincida con el que se le pase.
     *
     * @param item
     * @return
     */
    public void removeConsumible(Consumible item) {
        for (int i = 0; i < consumibles.length; i++) {
            if (item.equals(consumibles[i])) {
                consumibles[i] = null;
                //   listener.consumibleBorradoDelnventario(item);
                return;
            }
        }

    }

    public AbstractItem[] vaciarInventario() {
        ArrayList<AbstractItem> itemsASoltar = new ArrayList<AbstractItem>();
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] != null) {
                itemsASoltar.add(equipo[i]);
                equipo[i] = null;
            }
        }
         for (int i = 0; i < consumibles.length; i++) {
            if (consumibles[i] != null) {
                itemsASoltar.add(consumibles[i]);
                consumibles[i] = null;
            }
        }
         AbstractItem[] arr=new AbstractItem[itemsASoltar.size()];
         for (int i = 0; i < arr.length; i++) {
            arr[i]=itemsASoltar.get(i);
        }
         return arr;
    }

    /**
     * Borra un objeto Equipable del array que coincida con el que se le pase.
     *
     * @param item
     */
    public void removeEquipable(Equipable item) {
        for (int i = 0; i < equipo.length; i++) {
            if (item.equals(equipo[i])) {
                consumibles[i] = null;
                // listener.equipableBorradoDelInventario(item);
                return;
            }
        }
    }

    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        int equips = 0;
        int conss = 0;
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] != null) {
                equips++;
            }
        }
        for (int i = 0; i < consumibles.length; i++) {
            if (consumibles[i] != null) {
                conss++;
            }
        }

        font.draw(batch, "Cantidad de equipables: " + String.valueOf(equips), pos.x, pos.y + 20);
        font.draw(batch, "Cantidad de consumibles: " + String.valueOf(conss), pos.x, pos.y + 40);

    }

}
