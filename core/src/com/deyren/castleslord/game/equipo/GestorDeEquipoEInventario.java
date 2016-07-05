/*
 * @Ruben@
 */
package com.deyren.castleslord.game.equipo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deyren.castleslord.game.items.AbstractItem;
import com.deyren.castleslord.game.items.Arma;
import com.deyren.castleslord.game.items.Consumible;
import com.deyren.castleslord.game.items.Equipable;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.personajes.Protagonista;

/**
 *
 * @author Ruben
 */
public class GestorDeEquipoEInventario {

    private Equipo equipo;
    private Inventario inventario;
    private static GestorDeEquipoEInventario gestor;
    private Protagonista person;

    private GestorDeEquipoEInventarioListener listener;

    public void setGestorDeEquipoEInventarioListener(GestorDeEquipoEInventarioListener l) {
        listener = l;
    }

    /**
     * devuelve un objeto unica instancia de esta clase.
     *
     * @param p
     * @return
     */
    public static GestorDeEquipoEInventario getInstance(Protagonista p) {
        if (gestor == null) {
            gestor = new GestorDeEquipoEInventario(p);
            //    gestor.init();
            gestor.person = (Protagonista) p;
        }
        return gestor;
    }

    private GestorDeEquipoEInventario(Protagonista p) {
        equipo = new Equipo(p);
        inventario = new Inventario(50, 50);

    }

    // No comprueba si el casco es null, nunca deberia usarse si no tiene casco
    private void pasarCascoDeEquipoAInventario() {
        if (inventario.addEquipable(equipo.getCasco())) { // intenta agregarlo al inventario
            equipo.desEquiparCasco(); // si lo agrega lo borra del equipo
        } else {
            listener.equipableDevuelto(equipo.getCasco());//si no, lanza el evento que devuelve el casco
            equipo.desEquiparCasco();//borra el casco del equipo
        }

    }

    private void pasarBotasDeEquipoAInventario() {
        if (inventario.addEquipable(equipo.getBotas())) { // intenta agregarlo al inventario
            equipo.desEquiparBotas(); // si lo agrega lo borra del equipo
        } else {
            listener.equipableDevuelto(equipo.getBotas());//si no, lanza el evento 
            equipo.desEquiparBotas();//borra el casco del equipo
        }

    }

    private void pasarGuantesDeEquipoAInventario() {
        if (inventario.addEquipable(equipo.getGuantes())) { // intenta agregarlo al inventario
            equipo.desEquiparGuantes(); // si lo agrega lo borra del equipo
        } else {
            listener.equipableDevuelto(equipo.getGuantes());//si no, lanza el evento 
            equipo.desEquiparGuantes();//borra el casco del equipo
        }
    }

    private void pasarPechoDeEquipoAInventario() {
        if (inventario.addEquipable(equipo.getPecho())) { // intenta agregarlo al inventario
            equipo.desEquiparPecho(); // si lo agrega lo borra del equipo
        } else {
            listener.equipableDevuelto(equipo.getPecho());//si no, lanza el evento 
            equipo.desEquiparPecho();//borra el casco del equipo
        }
    }

    private void pasarArmaDeEquipoAInventario() {
        if (inventario.addEquipable(equipo.getArma())) { // intenta agregarlo al inventario
            equipo.desEquiparArma(); // si lo agrega lo borra del equipo
        } else {
            listener.equipableDevuelto(equipo.getArma());//si no, lanza el evento
            equipo.desEquiparArma();//borra el casco del equipo
        }
    }

    /**
     * Esto se ejecuta en el movimiento del personaje cuando el personaje choca
     * con algun item del escenario y ese item es Equipable. <br>
     * IDEA
     * 1) comprobar si el personaje ya lleva puesto ese equipo.
     * 2) sino lo lleva puesto, se lo pone, lo agrega al array del objeto equipo
     * 3) si ya tiene uno, intenta ponerlo en el inventario, ..
     *
     * @param e Objeto equipable a equipar
     * @return true si lo ha agregado
     */
    public void addEquipable(Equipable e, Protagonista person) {
        boolean agregado = false;
        if (e instanceof AbstractCasco) {//Si es un casco...             
            if (equipo.getCasco() == null) {//si no tiene casco en el equipo
                equipo.equiparCasco(person, (AbstractCasco) e);
                agregado = true;
            }
        } else if (e instanceof AbstractPecho) {//Si es una pechera...
            if (equipo.getPecho() == null) {
                equipo.equiparPecho(person, (AbstractPecho) e);
                agregado = true;
            }
        } else if (e instanceof AbstractBotas) {//Si son botas
            if (equipo.getBotas() == null) {
                //Utiles.Print("botas es null", this);
                equipo.equiparBotas(person, (AbstractBotas) e);
                agregado = true;
            }
        } else if (e instanceof AbstractGuantes) {//Si son guantes
            if (equipo.getGuantes() == null) {
                equipo.equiparGuantes(person, (AbstractGuantes) e);
                agregado = true;
            }
        } else if (e instanceof Arma) {//Si es arma
            if (equipo.getArma() == null) {
                equipo.equiparArma(person, (Arma) e);
                agregado = true;

            }

        }

        if (agregado) {
            listener.equipableAgregadoAlEquipo(e);
            e.cogerItem();
            if (e.getMejora() != null) {//hay equipo que no tiene mejoras, y esta a null
                person.getMejoras().add(e.getMejora().getTipo(), e.getMejora()); // agrega la mejora al personaje
            }
            if (e instanceof Arma) {
                person.addPersonajeListener((Arma) e);//Pasa al arma los eventos del personaje
            }
        } else {
            if (inventario.addEquipable(e)) { // lo intenta agregar al inventario
                e.cogerItem();
                listener.itemAgregadoAlInventario(e);
            }
        }

    }

    public void addConsumible(Consumible c, Protagonista p) {
        if (inventario.addConsumible(c)) { // lo intenta agregar al inventario
            c.cogerItem();
            listener.itemAgregadoAlInventario(c);
        }
    }

    public void desEquiparArma() {      
        listener.equipableDevuelto(equipo.getArma());
        equipo.desEquiparArma();
    }

    public void vaciarInventario() {
        listener.itemsDevueltos(inventario.vaciarInventario());

    }

    public void desequiparTodoLoDelEquipo() {
        Equipable[] todo = new Equipable[5];
        todo[0] = equipo.getArma();
        todo[1] = equipo.getBotas();
        todo[2] = equipo.getCasco();
        todo[3] = equipo.getGuantes();
        todo[4] = equipo.getPecho();
        listener.equipablesDevueltos(todo);
        equipo.desequiparTodo();
    }

    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        equipo.dibujar(batch, camara); //solo dibuja el arma de momento, cuando la lleva puesta el personaje
        //  inventario no dibuja de momento....
    }

    public void dibujar(SpriteBatch batch, OrthographicCamera camara, float rotacion) {
        equipo.dibujar(batch, camara, rotacion); //solo dibuja el arma de momento, cuando la lleva puesta el personaje
        //  inventario no dibuja de momento....
    }

    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        font.draw(batch, "", pos.x, pos.y);
        equipo.toScreenString(batch, font, new Vector2(pos.x, pos.y + 20));
        inventario.toScreenString(batch, font, new Vector2(pos.x, pos.y + 180));
    }

    // Implementada por ArrayDeItems, para devolverle items items 
    public static interface GestorDeEquipoEInventarioListener {

        public void equipableAgregadoAlEquipo(Equipable e);

        public void equipableDevuelto(Equipable e);

        public void equipablesDevueltos(Equipable[] e);

        public void itemAgregadoAlInventario(AbstractItem item);

        public void itemDevuelto(AbstractItem c);

        public void itemsDevueltos(AbstractItem[] cs);
    }
}
