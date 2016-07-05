/*
 * @Ruben@
 */
package com.deyren.castleslord.game.arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.personajes.Enemigo;
import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.SpriteAnimado;

import java.util.Random;

/**
 *Array que contiene a todos los enemigos que hay en la pantalla.
 * y varios metodos utiles.
 * @author Ruben
 */
public class ArrayDeEnemigos implements Personaje.PersonajeListener,ConjuntoDeSprites {

    private final Personaje[] enemigos;
    private Protagonista protagonista;
    public Personaje[] getEnemigos() {
        return enemigos;
    }

    public ArrayDeEnemigos(int cantidad,Protagonista protagonista) {
        enemigos = new Personaje[cantidad];
        this.protagonista=protagonista;
    }

    /**
     * Devuelve la cantidad de enemigos que hay en un momento dado. <br>
     * El array siempre tiene el mismo tamaño, lo que devuelve es la
     * cantidad de objetos que no son null
     * @return 
     */
    public int getLenght() {
        int c = 0;
        for (Personaje enemigo : enemigos) {
            if (enemigo != null) {
                c++;
            }
        }
        return c;
    }

    public void addEnemigo(Personaje e) {
        e.addPersonajeListener(this);
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] == null) {
                enemigos[i] = e;
                break;
            }
        }
    }

    public void addEnemigo(Personaje e, int posicion) {
        e.addPersonajeListener(this);
        enemigos[posicion] = e;
    }

    public void posicionar(float x, float y) {
        int s = 0;
        boolean sube = true;
        for (Personaje enemigo : enemigos) {
            if (enemigo != null) {
                SpriteAnimado.posicionar(enemigo,x + s, y);
                if (s <= 0) {
                    sube = true;
                }
                if (s >= Gdx.graphics.getWidth()) {
                    sube = false;
                }
                if (sube) {
                    s += new Random().nextInt(80);
                } else {
                    s -= new Random().nextInt(80);
                }

            }
        }
    }

    public Enemigo chocaCon(Rectangle rect) {
        Enemigo salida = null;
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                if (enemigos[i].rectangulo.overlaps(rect)) {
                    salida = (Enemigo) enemigos[i];
                    break;
                }
            }
        }
        return salida;
    }

    //true si será x
    @Override
    public void mover(float cantidad, boolean xOy) {
        if (xOy) {
            for (Personaje enemigo : enemigos) {
                if (enemigo != null) {
                    enemigo.rectangulo.x += cantidad;
                }
            }
        } else {
            for (Personaje enemigo : enemigos) {
                if (enemigo != null) {
                    enemigo.rectangulo.y += cantidad;
                }
            }
        }
    }

    @Override
    public void dibujar(SpriteBatch batch,OrthographicCamera camara) {
        for (Personaje enemigo : enemigos) {
            if (enemigo != null) {
                enemigo.actualizar();
                enemigo.dibujar(batch,camara);
             
            }

        }
    }

    public void dibujarBarrasDeVida(OrthographicCamera camara){
        for (Personaje enemigo : enemigos) {
              if (enemigo != null) {
                   ((Enemigo)enemigo).dibujarBarraDeVida(camara);
              }
            
        }
    }
    //solo para debug
    public void dibujarNumerosDeBarrasDeVida(SpriteBatch batch){
        for (Personaje enemigo : enemigos) {
              if (enemigo != null) {
                   ((Enemigo)enemigo).dibujarNumerosBarraDeVida(batch);
              }
            
        }
    }
    
    @Override
    public void haMuerto(Personaje p) {
        for (int i = 0; i < enemigos.length; i++) {
            if (p.equals(enemigos[i])) {
                enemigos[i] = null;
                break;
            }
        }
    }

    /**
     * Cuando un enemigo hace daño al personaje
     * @param p 
     */
    @Override
    public void atacando(Personaje p) {
     
        protagonista.CambiarVida(-p.atributos.daño);
           protagonista.getMejoras().usarMejora(ArrayDeMejoras.ListaDeMejoras.ArmaduraPinchos, p);
       
    }

}
