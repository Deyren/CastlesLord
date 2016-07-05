/*
 * @Ruben@
 */

package com.deyren.castleslord.game.utiles;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Ruben
 */
public class Atributos implements Cloneable{
public int vida,vidaMaxima,mana,manaMaximo,daño;
public int fuerza=1,inteligencia=1;
public float velocidadDeMovimiento=1; 
public int armadura=1;
public float velocidadDeAtaque=1;
public int nivel=1;
public int experiencia=0;

    public Atributos(int vida, int vidaMaxima, int mana, int manaMaximo, int daño) {
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.mana = mana;
        this.manaMaximo = manaMaximo;
        this.daño = daño;
    }

   
    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        font.draw(batch, "Vida:" +vida, pos.x, pos.y);
        font.draw(batch, "Mana:" + mana, pos.x, pos.y + 20);
        font.draw(batch, "Daño:" + daño, pos.x, pos.y + 40);
        font.draw(batch, "Fuerza :" + fuerza, pos.x, pos.y + 60);
        font.draw(batch, "Inteligencia :" + inteligencia, pos.x, pos.y + 80);
        font.draw(batch, "V. movimiento: " + velocidadDeMovimiento, pos.x, pos.y + 100);
        font.draw(batch, "V. de ataque: " + velocidadDeAtaque, pos.x, pos.y + 120);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        super.clone();
        Atributos atr=new Atributos(vida, vidaMaxima, mana, manaMaximo, daño);
        atr.fuerza=fuerza;
        atr.inteligencia=inteligencia;
        atr.velocidadDeMovimiento=velocidadDeMovimiento;
        atr.armadura=armadura;
        atr.velocidadDeAtaque=velocidadDeAtaque;
        atr.nivel=nivel;
        atr.experiencia=experiencia;
        return atr;
    }
    

}
