/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 *
 * @author Ruben
 */
public abstract class Equipable extends AbstractItem {

    protected Mejora mejora;
    protected boolean equipada;
    protected EquipableListener equipableListener;
    protected Protagonista PersonajeQueLoLleva = null;

    public void setEquipableListener(EquipableListener l) {
        equipableListener = l;
    }

    public boolean isEquipada() {
        return equipada;
    }

    public void equipar(Protagonista prota) {
        this.PersonajeQueLoLleva = prota;
        equipada = true;
        //Debug.Print("Equipado:"+this, prota);
        equipableListener.equipado(this);
    }

    public void desequipar() {
        this.PersonajeQueLoLleva = null;
        equipada = false;
        equipableListener.desequipado(this);
    }

    public Equipable(Sprite sprite) {
        super(sprite);
    }

    public Mejora getMejora() {
        return mejora;
    }

    //Atributos que contiene el equipo que aumentan al personaje
    public static class AtributosDeEquipo {

        public int vidaMaxima, manaMaximo, fuerza, inteligencia, armadura;
        public float velocidadDeMovimiento, velocidadDeAtaque;
    }

    /**
     * Eventos para cuando se equipa o des-equipa un objeto equipable
     */
    public static interface EquipableListener {

        public void equipado(Equipable e);

        public void desequipado(Equipable e);
    }
}
