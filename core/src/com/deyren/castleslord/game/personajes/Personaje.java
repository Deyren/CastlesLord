/*
 * @Ruben@
 */
package com.deyren.castleslord.game.personajes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.deyren.castleslord.game.arrays.ArrayDePerjuicios;
import com.deyren.castleslord.game.utiles.Atributos;

import java.util.ArrayList;

/**
 * Clase de la que heredan todo lo que sean personajes en pantalla . <br>
 * Lanza un evento cuando muere. <br>
 * Se le pasa el array de rectangulos de colision del mapa para que vaya
 * actualizando las colisiones Contiene un objeto
 *
 * @author Ruben
 */
public abstract class Personaje extends SpriteAnimado {

    public static enum ChocaCon {
        Nada, Izquierda, Derecha, Arriba
    }

    private ChocaCon chocaCon = ChocaCon.Nada;
    public ChocaCon getChocaCon() {
        return chocaCon;
    }
    protected ArrayList< PersonajeListener> listeners = null;

    /**
     * Agrega un listener para que escuche los eventos del personaje. 
     * @param l 
     */
    public void addPersonajeListener(PersonajeListener l) {
        listeners.add(l);
    }
   /**
     * Borra un listener de  los eventos del personaje. 
     * @param l 
     */
    public void removePersonajeListener(PersonajeListener l) {
        listeners.remove(l);
    }

    public static final float gravedad = 3.2f;
    protected float valorDeCaida = 0;
    protected float desAceleracionSubiendo = 0.17f;
    protected float aceleracionCayendo = 0.17f;
    protected boolean saltando = false;
    protected boolean atacando = false;

    public boolean estaEnElSuelo = false;
    protected Vector2 posicionAntigua = new Vector2(0, 0);
    protected float aceleracionActual = 0;
    protected float cantidadDeSalto = 4.4f;
    protected float reduccion = cantidadDeSalto;
    //protected float velocidadAnimacionAtacando = 0.4f;
    private final Array<RectangleMapObject> rectangulosDeColision;
    public Atributos atributos;
    public ArrayDePerjuicios perjuicios;
    protected boolean aturdido = false;

    
    
    /**
     * Variable para que lance el evento de que esta atacando una sola vez, y no
     * todo el tiempo que dura la animacion en el frame que ataca. tipica
     * variable Up
     */
    private int upDeAtaque = 0;

    public boolean isAturdido() {
        return aturdido;
    }

    public void setAturdido(boolean aturdido) {
        this.aturdido = aturdido;
    }

    public boolean isAtacando() {
        return atacando;
    }

    public void setAtacando(boolean atacando) {
        this.atacando = atacando;
    }

    public float getAceleracionActual() {
        return aceleracionActual;
    }

    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    public void setEstaEnElSuelo(boolean estaEnElSuelo) {
        this.estaEnElSuelo = estaEnElSuelo;
    }

    public void setAceleracionDeCaida(float aceleracionDeCaida) {
        this.valorDeCaida = aceleracionDeCaida;
    }

    public boolean isSaltando() {
        return saltando;
    }

    public abstract void atacar();

    public boolean isEstaEnElSuelo() {
        return estaEnElSuelo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public ArrayDePerjuicios getPerjuicios() {
        return perjuicios;
    }

    private boolean vivo = true;

    public Personaje(Array<RectangleMapObject> rectangulosDeColision) {
        super();
        this.rectangulosDeColision = rectangulosDeColision;
        atributos = new Atributos(900, 1000, 10, 100, 15);
        atributos.velocidadDeAtaque = 0.08f;
        atributos.velocidadDeMovimiento = 0.2f;
        listeners = new ArrayList<PersonajeListener>();
        perjuicios = new ArrayDePerjuicios(this);
    }

    /**
     * Lanza los eventos de cuando ataca o se muere, entre otras cosas
     */
    @Override
    public void actualizar() {
        super.actualizar();
        if (!estaEnElSuelo && !saltando) {
            rectangulo.y -= gravedad * valorDeCaida;
            if (valorDeCaida < 15) {
                valorDeCaida += aceleracionCayendo;
            }
        } else if (saltando) {
            if (reduccion > 0) {
                rectangulo.y += gravedad * reduccion;
                reduccion -= desAceleracionSubiendo;
            } else {
                saltando = false;
                reduccion = cantidadDeSalto;
            }
        }

        boolean chocaConElSuelo = false;
        chocaCon = ChocaCon.Nada;
        for (RectangleMapObject rectangulosDeColision1 : rectangulosDeColision) {
            if (colisionNueva(rectangulosDeColision1.getRectangle(), this) > 0) {
                chocaConElSuelo = true;
            }
        }
        if (!chocaConElSuelo) {
            estaEnElSuelo = false;
        }

        //Solo de prueba
        aceleracionActual = Vector2.dst2(posicionAntigua.x, posicionAntigua.y, rectangulo.x, rectangulo.y);

        posicionAntigua.x = rectangulo.x;
        posicionAntigua.y = rectangulo.y;

        if (atacando) {
            //System.out.println(acumulador);
            if (acumulador == 0) {
                atacando = false;
                //animacionListener.animacionCambiada();
            }

            //el evento de atacando solo salta cuando la animacion esta justo cuando tiene el brazo estirado
            if (animacion.getKeyFrame(acumulador).equals(atlas.findRegion("0008"))) {
                if (upDeAtaque++ == 1) {// para que solo ocurra una vez
                    for (PersonajeListener listener : listeners) {
                        listener.atacando(this);
                    }
                    //Utiles.Print("Ataco", this);
                }
            } else {
                upDeAtaque = 0;
            }

        }
    }
    
 
    public void saltar() {
        if (estaEnElSuelo) {
            saltando = true;
        }

    }

    public void parar() {
        estaAndando = false;
    }

    public static void moverArriba(Personaje p) {
        p.rectangulo.y += p.atributos.velocidadDeMovimiento;
    }

    public static void moverAbajo(Personaje p) {
        p.rectangulo.y -= p.atributos.velocidadDeMovimiento;
    }

    public static void moverDerecha(Personaje p) {
        p.rectangulo.x += p.atributos.velocidadDeMovimiento;
        p.mirandoHacia = MirandoHacia.Derecha;
        p.estaAndando = true;
    }

    public static void moverIzquierda(Personaje p) {
        p.rectangulo.x -= p.atributos.velocidadDeMovimiento;
        p.mirandoHacia = MirandoHacia.Izquierda;
        p.estaAndando = true;
    }

    /**
     * Para quitarle vida el numero debe ser negativo
     *
     * @param cantidad
     */
    public void CambiarVida(int cantidad) {
        //Debug.Print("Vida antes del daño: "+atributos.vida, this);
        atributos.vida += cantidad;
       // Debug.Print("Vida quitada: "+cantidad, this);
        //  Debug.Print("Vida despues del daño: "+atributos.vida, this);
        //  Debug.Print("_____________________________________ "+atributos.vida, this);
        if (atributos.vida <= 0) {
            vivo = false;
            atributos.vida = 0;
            for (PersonajeListener listener : listeners) {
                listener.haMuerto(this);
            }
        } else if (atributos.vida > atributos.vidaMaxima) {
            atributos.vida = atributos.vidaMaxima;
        }
    }

    public void CambiarVidaMaxima(int cantidad) {
        atributos.vidaMaxima += cantidad;
        if (atributos.vidaMaxima < atributos.vida) {
            throw new Error("La vida maxima no puede ser menor que la vida");
        }
    }

    /**
     * Para quitarle vida el numero debe ser negativo
     *
     * @param cantidad
     */
    public void CambiarMana(int cantidad) {
        atributos.mana += cantidad;
        if (atributos.mana <= 0) {
            atributos.mana = 0;

        } else if (atributos.mana > atributos.manaMaximo) {
            atributos.mana = atributos.manaMaximo;
        }
    }

    public void CambiarManaMaximo(int cantidad) {
        atributos.manaMaximo += cantidad;
        if (atributos.manaMaximo < atributos.mana) {
            throw new Error("El mana maximo no puede ser menor que el mana");
        }
    }

    //r= el rectangulo con el que se comprueba si el personaje choca
    private static int colisionNueva(Rectangle r, Personaje p) {
        //p.chocaCon=ChocaCon.Nada;
        int cant = 0;
        float w = 0.5f * (p.rectangulo.width + r.width);
        float h = 0.5f * (p.rectangulo.height + r.height);

        Vector2 vv1 = new Vector2();
        vv1 = p.rectangulo.getCenter(vv1);

        Vector2 vv2 = new Vector2();
        vv2 = r.getCenter(vv2);

        float dx = vv1.x - vv2.x;
        float dy = vv1.y - vv2.y;

        if (Math.abs(dx) <= w && Math.abs(dy) <= h) {
            /* collision! */
            float wy = w * dy;
            float hx = h * dx;

            if (wy > hx) {
                if (wy > -hx) {
                    /*Si el personaje choca con la parte de ARRIBA del rectangulo */
                    p.rectangulo.y = r.y + r.height;
                    p.estaEnElSuelo = true;
                    p.valorDeCaida = 0;
                    cant++;
                } else {
                    /*Si el personaje choca con la parte IZQUIERDA del rectangulo */
                    p.chocaCon = ChocaCon.Derecha;
                    p.rectangulo.x = r.x - p.rectangulo.width;
                }
            } else {
                if (wy > -hx) {
                    /*  Si el personaje choca con la parte de DERECHA del rectangulo */
                    p.chocaCon = ChocaCon.Izquierda;
                    p.rectangulo.x = r.x + r.width;
                } else {
                    /*Si el personaje choca con la parte de ABAJO del rectangulo */
                    p.chocaCon = ChocaCon.Arriba;
                    p.rectangulo.y = r.y - p.rectangulo.height;
                    p.saltando = false;
                    p.reduccion = p.cantidadDeSalto;
                }
            }
        }
        return cant;
    }

    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        font.draw(batch, "Esta en el suelo:" + estaEnElSuelo, pos.x, pos.y);
        font.draw(batch, "Andando:" + estaAndando, pos.x, pos.y + 20);
        font.draw(batch, "Posicion:" + rectangulo.getPosition(new Vector2()).toString(), pos.x, pos.y + 40);
        font.draw(batch, "Choca con :" + chocaCon.name(), pos.x, pos.y + 60);
        font.draw(batch, "Vida :" + String.valueOf(atributos.vida), pos.x, pos.y + 80);
        font.draw(batch, "Aceleracion :" + String.valueOf(aceleracionActual), pos.x, pos.y + 100);
        font.draw(batch, "Atacando :" + String.valueOf(atacando), pos.x, pos.y + 120);
        atributos.toScreenString(batch, font, new Vector2(pos.x, pos.y + 140));
    }

    public static interface PersonajeListener {

        public void haMuerto(Personaje p);

        /**
         * Este evento solo salta cuando el personaje esta haciendo daño con el
         * ataque. Cuando la animacion esta con el brazo estirado. La condicion
         * esta puesta en esta clase.
         *
         * @param p
         */
        public void atacando(Personaje p);

    }
}
