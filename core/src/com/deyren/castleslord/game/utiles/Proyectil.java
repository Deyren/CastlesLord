/*
 * @Ruben@
 */

package com.deyren.castleslord.game.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.personajes.Enemigo;
import com.deyren.castleslord.game.personajes.Personaje;

/**
 *
 * @author Ruben
 */
public class Proyectil {
    private HaChocadoListener listener;
    public void setHaChocadoListener(HaChocadoListener l){
        listener=l;
    }
    public final Rectangle rectangulo;
    private final Texture imagen;
    public enum Direccion{Izquierda,Derecha}
    private Direccion direccion;
    private float Velocidad=2.5f;
    private int daño=1000;
    private Personaje personajeQueTieneElProyectil;
     private final ArrayDeEnemigos arrayDeEnemigos;
    public Proyectil(Personaje lanzador, ArrayDeEnemigos arrEnemys, Rectangle rectangulo,Texture imagen,Direccion dir) {   
      personajeQueTieneElProyectil=lanzador;
        arrayDeEnemigos=arrEnemys;
        this.rectangulo = rectangulo;
        this.imagen =imagen;
        this.direccion=dir;
    }
    
    private void actualizar(){
        Enemigo ec=arrayDeEnemigos.chocaCon(rectangulo);
        if(ec!=null){
            ec.CambiarVida(-personajeQueTieneElProyectil.atributos.daño-daño);
        }
        
        
    }
    
    public void dibujar(SpriteBatch batch){
        actualizar();
        if(direccion==Direccion.Derecha){
            rectangulo.x+=Velocidad;
        }else{
             rectangulo.x-=Velocidad;
        }
        batch.draw(imagen, rectangulo.x, rectangulo.y,rectangulo.width,rectangulo.height);
    }
    
    public static interface HaChocadoListener
    {
        public void haChocado(Proyectil este);
    }
}
