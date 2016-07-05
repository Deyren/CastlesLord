/*
 * @Ruben@
 */
package com.deyren.castleslord.game.equipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deyren.castleslord.game.items.Arma;
import com.deyren.castleslord.game.items.Armadura;
import com.deyren.castleslord.game.items.Equipable;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.personajes.Protagonista;

/**
 * Equipo implementa EquipableListener para ver cuando se quita o pone algo en
 * el equipo y a su vez esta clase lanza un evento, que lo implementa la clase
 * Protagonista.
 *
 * @author Ruben
 */
public class Equipo implements Equipable.EquipableListener {

    private AbstractCasco casco;
    private AbstractPecho pecho;
    private AbstractBotas botas;
    private AbstractGuantes guantes;
    private Arma arma;
    //  private EquipoListener listener;
    private final Protagonista personaje;

//    public void setEquipoListener(EquipoListener l) {
//        listener = l;
//    }
    public Equipo(Protagonista personaje) {
        this.personaje = personaje;
    }

    public AbstractCasco getCasco() {
        return casco;
    }

    /**
     * Agrega un casco al equipo. <br>
     * Este metodo agrega al objeto Equipable que le llege el
     * 'Equipable.EquipableListener', pasandole esta clase. Despues ejecuta el
     * metodo 'equipar()' del objeto Equipable que se le pasa, lo que hace que
     * el objeto lance el evento de que ha sido equipado, y esta misma clase es
     * la que atrapa esos eventos de todo el quipo que contenga.
     *
     * @param casco
     */
    public void equiparCasco(Protagonista prota, AbstractCasco casco) {
        this.casco = casco;
        this.casco.setEquipableListener(this);
        this.casco.equipar(prota);

    }

    public void desEquiparCasco() {
        if (this.casco != null) {
            this.casco.desequipar();
        }
    }

    public AbstractPecho getPecho() {
        return pecho;
    }

    /**
     * Agrega un pecho al equipo. <br>
     * Este metodo agrega al objeto Equipable que le llege el
     * 'Equipable.EquipableListener', pasandole esta clase. Despues ejecuta el
     * metodo 'equipar()' del objeto Equipable que se le pasa, lo que hace que
     * el objeto lance el evento de que ha sido equipado, y esta misma clase es
     * la que atrapa esos eventos de todo el quipo que contenga.
     *
     * @param prota
     * @param pecho
     */
    public void equiparPecho(Protagonista prota, AbstractPecho pecho) {
        this.pecho = pecho;
        this.pecho.setEquipableListener(this);
        this.pecho.equipar(prota);
    }

    public void desEquiparPecho() {
        if (this.pecho != null) {
            this.pecho.desequipar();
        }
    }

    public AbstractBotas getBotas() {
        return botas;
    }

    /**
     * Agrega una botas al equipo. <br>
     * Este metodo agrega al objeto Equipable que le llege el
     * 'Equipable.EquipableListener', pasandole esta clase. Despues ejecuta el
     * metodo 'equipar()' del objeto Equipable que se le pasa, lo que hace que
     * el objeto lance el evento de que ha sido equipado, y esta misma clase es
     * la que atrapa esos eventos de todo el quipo que contenga.
     *
     * @param botas
     */
    public void equiparBotas(Protagonista prota, AbstractBotas botas) {
        this.botas = botas;
        this.botas.setEquipableListener(this);
        this.botas.equipar(prota);
    }

    public void desEquiparBotas() {
        if (this.botas != null) {
            this.botas.desequipar();
        }
    }

    public AbstractGuantes getGuantes() {
        return guantes;
    }

    /**
     * Agrega unos guantes al equipo. <br>
     * Este metodo agrega al objeto Equipable que le llege el
     * 'Equipable.EquipableListener', pasandole esta clase. Despues ejecuta el
     * metodo 'equipar()' del objeto Equipable que se le pasa, lo que hace que
     * el objeto lance el evento de que ha sido equipado, y esta misma clase es
     * la que atrapa esos eventos de todo el quipo que contenga.
     *
     * @param guantes
     */
    public void equiparGuantes(Protagonista prota, AbstractGuantes guantes) {
        this.guantes = guantes;
        this.guantes.setEquipableListener(this);
        this.guantes.equipar(prota);
    }

    public void desEquiparGuantes() {
        if (this.guantes != null) {
            this.guantes.desequipar();
        }
    }

    public Arma getArma() {
        return arma;
    }

    /**
     * Agrega un arma al equipo. <br>
     * Este metodo agrega al objeto Equipable que le llege el
     * 'Equipable.EquipableListener', pasandole esta clase. Despues ejecuta el
     * metodo 'equipar()' del objeto Equipable que se le pasa, lo que hace que
     * el objeto lance el evento de que ha sido equipado, y esta misma clase es
     * la que atrapa esos eventos de todo el quipo que contenga.
     *
     * @param arma
     */
    public void equiparArma(Protagonista prota, Arma arma) {
        this.arma = arma;
        this.arma.setEquipableListener(this);
        this.arma.equipar(prota);
    }

    public void desEquiparArma() {
        if (this.arma != null) {
            personaje.removePersonajeListener(this.arma);
            this.arma.desequipar();
        }
    }

    public void desequiparTodo() {
        desEquiparArma();
        desEquiparBotas();
        desEquiparCasco();
        desEquiparGuantes();
        desEquiparPecho();
    }

    /**
     * Cuanfo un Equipable se equipa, lanza este evento.<br>
     * Aqui establece los atributos del personaje al equipo y guarda el objeto
     * pasado segun el tipo que sea
     *
     * @param e
     */
    @Override
    public void equipado(Equipable e) {
        //System.out.println("Cogido un " + e);
        //Utiles.Print("Equipado", this);
        if (e instanceof Armadura) {
            Armadura ar = (Armadura) e;//Aumenta los atributos que de el equipo al personaje
            personaje.atributos.vidaMaxima += ar.getAtributos().vidaMaxima;
            personaje.atributos.fuerza += ar.getAtributos().fuerza;
            personaje.atributos.inteligencia += ar.getAtributos().inteligencia;
            personaje.atributos.armadura += ar.getAtributos().armadura;
            personaje.atributos.manaMaximo += ar.getAtributos().manaMaximo;
            personaje.atributos.velocidadDeAtaque += ar.getAtributos().velocidadDeAtaque;
            personaje.atributos.velocidadDeMovimiento += ar.getAtributos().velocidadDeMovimiento;

            //Establece segun que objeto sea.
            if (e instanceof AbstractCasco) {
                this.casco = (AbstractCasco) e;
            } else if (e instanceof AbstractPecho) {
                this.pecho = (AbstractPecho) e;
            } else if (e instanceof AbstractBotas) {
                this.botas = (AbstractBotas) e;
            } else if (e instanceof AbstractGuantes) {
                this.guantes = (AbstractGuantes) e;
            }
        } else {
            //cuando es un arma
            Arma ar = (Arma) e;
            personaje.atributos.daño += ar.getDanio();
            personaje.atributos.vidaMaxima += ar.getAtributos().vidaMaxima;
            personaje.atributos.fuerza += ar.getAtributos().fuerza;
            personaje.atributos.inteligencia += ar.getAtributos().inteligencia;
            personaje.atributos.armadura += ar.getAtributos().armadura;
            personaje.atributos.manaMaximo += ar.getAtributos().manaMaximo;
            personaje.atributos.velocidadDeAtaque += ar.getAtributos().velocidadDeAtaque;
            personaje.atributos.velocidadDeMovimiento += ar.getAtributos().velocidadDeMovimiento;
            this.arma = ar;
            //  personaje.addPersonajeListener(ar);
            //provablemente hay que poner un metodo en personaje para borrar el listener
            //por que si el equipo se quita y desaparece, la implementacion que se le pase tambien se borra....
        }

        //   listener.itemEquipado(e);
        // System.out.println("Equipado con "+e);
    }

    /**
     * Cuanfo un Equipable se desequipa, lanza este evento.<br>
     * Aqui establece los atributos del personaje al perder el equipo y
     * establece a null el equipo del tipo pasado.
     *
     * @param e
     */
    @Override
    public void desequipado(Equipable e) {
        //Utiles.Print("desequipado", this);
        if (e instanceof Armadura) {
            Armadura ar = (Armadura) e;
            personaje.atributos.vidaMaxima -= ar.getAtributos().vidaMaxima;
            personaje.atributos.fuerza -= ar.getAtributos().fuerza;
            personaje.atributos.inteligencia -= ar.getAtributos().inteligencia;
            personaje.atributos.armadura -= ar.getAtributos().armadura;
            personaje.atributos.manaMaximo -= ar.getAtributos().manaMaximo;
            personaje.atributos.velocidadDeAtaque -= ar.getAtributos().velocidadDeAtaque;
            personaje.atributos.velocidadDeMovimiento -= ar.getAtributos().velocidadDeMovimiento;

            //Establece segun que objeto sea.
            if (e instanceof AbstractCasco) {
                this.casco = null;
            } else if (e instanceof AbstractPecho) {
                this.pecho = null;
            } else if (e instanceof AbstractBotas) {
                this.botas = null;
            } else if (e instanceof AbstractGuantes) {
                this.guantes = null;
            }

        } else {
            //cuando es un arma
            //Debug.Print("Arma desequipada "+e, this);
            Arma ar = (Arma) e;
            personaje.atributos.daño -= ar.getDanio();
            personaje.atributos.vidaMaxima -= ar.getAtributos().vidaMaxima;
            personaje.atributos.fuerza -= ar.getAtributos().fuerza;
            personaje.atributos.inteligencia -= ar.getAtributos().inteligencia;
            personaje.atributos.armadura -= ar.getAtributos().armadura;
            personaje.atributos.manaMaximo -= ar.getAtributos().manaMaximo;
            personaje.atributos.velocidadDeAtaque -= ar.getAtributos().velocidadDeAtaque;
            personaje.atributos.velocidadDeMovimiento -= ar.getAtributos().velocidadDeMovimiento;
            this.arma = null;
        }
        // listener.itemDesequipado(e);
    }

    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
       
        if (casco != null && casco.isEquipada()) {
                casco.dibujar(batch, camara);
            }
           if (pecho != null && pecho.isEquipada()) {
                pecho.dibujar(batch, camara);
            }
        
        if (arma != null && arma.isEquipada()) {
            arma.dibujar(batch, camara);

            
        
        }
    }

    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        if (arma != null && arma.isEquipada()) {
            arma.dibujar(batch, camara, rotacion);
        }
             if (pecho != null && pecho.isEquipada()) {
                pecho.dibujar(batch, camara);
            }
        if (casco != null && casco.isEquipada()) {
            casco.dibujar(batch, camara, rotacion);
        }
    }

    /**
     * Muestra informacion Del equipo
     *
     * @param batch
     * @param font
     * @param pos
     */
    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        font.draw(batch, "Casco: " + casco, pos.x, pos.y);
        font.draw(batch, "Pecho: " + pecho, pos.x, pos.y + 20);
        font.draw(batch, "Botas: " + botas, pos.x, pos.y + 40);
        font.draw(batch, "Guantes: " + guantes, pos.x, pos.y + 60);
        font.draw(batch, "Arma: " + arma, pos.x, pos.y + 80);
    }

//    /**
//     * Eventos para cuando un objeto se agrega o se quita del inventario.
//     */
//    public static interface EquipoListener {
//        public void itemEquipado(Equipable e);
//        public void itemDesequipado(Equipable e);
//    }
}
