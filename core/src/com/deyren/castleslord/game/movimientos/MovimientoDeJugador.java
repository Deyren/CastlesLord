/*
 * @Ruben@
 */
package com.deyren.castleslord.game.movimientos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.Eventos;
import com.deyren.castleslord.game.Mapa;
import com.deyren.castleslord.game.arrays.ArrayDeItems;
import com.deyren.castleslord.game.arrays.ArrayDeUsables;
import com.deyren.castleslord.game.arrays.ConjuntoDeSprites;
import com.deyren.castleslord.game.items.AbstractItem;
import com.deyren.castleslord.game.items.Consumible;
import com.deyren.castleslord.game.items.Equipable;
import com.deyren.castleslord.game.items.Usable;
import com.deyren.castleslord.game.items.armas.EspadaSimple;
import com.deyren.castleslord.game.items.armas.HachaSimple;
import com.deyren.castleslord.game.items.armas.MazaSimple;
import com.deyren.castleslord.game.items.armasabstracto.AbstractEspada;
import com.deyren.castleslord.game.items.armasabstracto.AbstractHacha;
import com.deyren.castleslord.game.items.armasabstracto.AbstractMaza;
import com.deyren.castleslord.game.items.equipo.CascoSimple;
import com.deyren.castleslord.game.items.equipo.botas.BotasSimples;
import com.deyren.castleslord.game.items.equipo.GuantesSimples;
import com.deyren.castleslord.game.items.equipo.PechoSimple;
import com.deyren.castleslord.game.items.equipo.botas.BotasRapidas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.personajes.Enemigo;
import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.SpriteAnimado;
import com.deyren.castleslord.game.utiles.Constantes;

import java.util.Random;

/**
 *
 * @author Ruben
 */
public class MovimientoDeJugador implements Eventos.EventosListener {

    private boolean IzqPulsado, DerPulsado;
    private final Eventos eventos;
    private final Protagonista person;
    private final Mapa mapa;
    private final OrthographicCamera camara;
    private final ArrayDeEnemigos enemigos;
    private final ArrayDeItems items;
    private final ArrayDeUsables usables;
    //private final ConjuntoDeSprites[] todo;
    private float margenIzquierdo, margenDerecho,
            margenDeAbajo, margenDeArriba;

    //Solo para debug, para que el personaje vuele al pulsar N____________________________________________________________________________
    private boolean movimientoLibre = false;
    private int up;
    //________________________________________________________________________________________

    public MovimientoDeJugador(Protagonista personaje, Mapa mapa, OrthographicCamera camara, ArrayDeEnemigos enemigos, ArrayDeItems items, ArrayDeUsables usables) {
        this.person = personaje;
        this.mapa = mapa;
        this.camara = camara;
        this.enemigos = enemigos;
        this.items = items;
        this.usables = usables;
        this.eventos = new Eventos();
        //this.todo = todo;
        //Gdx.input.setInputProcessor(eventos); //Descomentar esto para usar teclado
        margenes();
        iniciar();
    }

    private void iniciar() {
        eventos.addEventosListener(this);

        //person.getEquipo().getArma().setArmaListener(this);
    }

    private void moverMapa() {
        //Vector3 vp=camara.unproject(new Vector3(person.rectangulo.x, person.rectangulo.y, 0));
        if (person.rectangulo.x > camara.position.x) { // person.rectangulo.x > margenDerecho
            camara.position.x += person.atributos.velocidadDeMovimiento;
//            mapa.moverMapa(-person.atributos.velocidadDeMovimiento, true);
//        


            //    person.rectangulo.x -= person.atributos.velocidadDeMovimiento;
        } else if (person.rectangulo.x < camara.position.x - 20) { // person.rectangulo.x < margenIzquierdo
            camara.position.x -= person.atributos.velocidadDeMovimiento;
//            mapa.moverMapa(person.atributos.velocidadDeMovimiento, true);
//            person.rectangulo.x += person.atributos.velocidadDeMovimiento;
//

        }

        if (person.rectangulo.y > camara.position.y + 10) {
            camara.position.y += person.atributos.velocidadDeMovimiento;
//            mapa.moverMapa(-person.atributos.velocidadDeMovimiento, false);
//            Personaje.moverAbajo(person);


        } else if (person.rectangulo.y < camara.position.y - 120) {
            camara.position.y -= person.atributos.velocidadDeMovimiento;

//            mapa.moverMapa(person.atributos.velocidadDeMovimiento, false);
//            Personaje.moverArriba(person);
//

        }
    }

    /**
     * Usa el metodo actualizar del personaje jugador.
     */
    public void actualizar() {
        moverMapa();

        if (eventos.isDown(Input.Keys.D)) {
            Personaje.moverDerecha(person);

            DerPulsado = true;

        } else {
            DerPulsado = false;
        }
        if (eventos.isDown(Input.Keys.A)) {
            Personaje.moverIzquierda(person);

            IzqPulsado = true;
        } else {
            IzqPulsado = false;
        }
        if (eventos.isDown(Input.Keys.W)) {
            Personaje.moverArriba(person);

        }
        if (eventos.isDown(Input.Keys.S)) {

            Personaje.moverAbajo(person);
        }

        if (eventos.isDown(Input.Keys.Q)) {
            //person.posicionar(100, 350);
            enemigos.posicionar(0, 500 + new Random().nextInt(200));
        }
        if (eventos.isDown(Input.Keys.SPACE)) {
            person.saltar();
            //person.CambiarVida(200);

            // enemigo.saltar();
        }
        if (eventos.isDown(Input.Keys.G)) { //tecla G de prueba de desequipar
            person.getGestorEquipoInventario().desEquiparArma();
            // person.getEquipo().desEquiparArma();
        }

        if (eventos.isDown(Input.Keys.H)) { //tecla G de prueba de desequipar todo
            person.getGestorEquipoInventario().desequiparTodoLoDelEquipo();
            // person.getEquipo().desEquiparArma();
        }
        
          if (eventos.isDown(Input.Keys.R)) { //tecla G de prueba de desequipar todo
            person.getGestorEquipoInventario().vaciarInventario();
            // person.getEquipo().desEquiparArma();
        }

        if (eventos.isDown(Input.Keys.F)) { //tecla F de prueba
            person.atacar();

        }

        if (eventos.isDown(Input.Keys.V)) { //tecla V de prueba add equipo
            AbstractPecho pecho = new PechoSimple(person.rectangulo.x + 80, person.rectangulo.y);
            items.addItem(pecho);
            AbstractBotas botas = new BotasSimples(person.rectangulo.x + 160, person.rectangulo.y);
            items.addItem(botas);
            AbstractGuantes guantes = new GuantesSimples(person.rectangulo.x + 300, person.rectangulo.y);
            items.addItem(guantes);
            AbstractMaza maza = new MazaSimple(person.rectangulo.x + 380, person.rectangulo.y, enemigos);
            items.addItem(maza);
            AbstractEspada espada = new EspadaSimple(person.rectangulo.x + 450, person.rectangulo.y, enemigos);
            items.addItem(espada);
            AbstractHacha hacha = new HachaSimple(person.rectangulo.x + 520, person.rectangulo.y, enemigos);
            items.addItem(hacha);
            AbstractBotas botasRapidas = new BotasRapidas(person.rectangulo.x + 590, person.rectangulo.y);
            items.addItem(botasRapidas);
            AbstractCasco casco = new CascoSimple(person.rectangulo.x + 630, person.rectangulo.y);
            items.addItem(casco);
        }

        //prueba
        if (eventos.isDown(Input.Keys.E)) {
            enemigos.addEnemigo(Enemigo.crearEnemigo(mapa.getRectangulosDeColision(),Constantes.Personajes.Personaje1, person, 400, 600, enemigos.getEnemigos()));
        }

        person.setEstaAndando(DerPulsado || IzqPulsado);
        if (person.isEstaAndando()) {
            person.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.andando);
        } else if (person.isAtacando()) {
            person.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.atacando);
        } else {
            person.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.stand);
        }

        // Solo para debug, para que el personaje vuele___________________________________________
        if (eventos.isDown(Input.Keys.N)) {
            up++;
        }
        if (up == 1) {
            movimientoLibre = !movimientoLibre;
        }
        if (!movimientoLibre) {
            person.actualizar();
        }
        // ______________________________________________________________________________________

        colisionConItems();

    }

    /**
     * Lo que ocurre cuando el personaje choca con un item del escenario. <br>
     * Lo coje y lo guarda en el inventario o en el equipo si es equipable y no
     * lleva nada puesto
     */
    private void colisionConItems() {
        AbstractItem it = items.chocaCon(person.rectangulo); //Comprueba si choca con algun item
        if (it != null) {
            if (it instanceof Equipable) {//Si es equipo
                Equipable ee = (Equipable) it;
                // este metodo hace tod, lo equipa, o lo devuelve como un evento, tambien agrega las mejoras al protagonista
                person.getGestorEquipoInventario().addEquipable(ee, person);
            }else{
                //Si es un consumible
                person.getGestorEquipoInventario().addConsumible((Consumible)it, person);
                
            }

        }

        //Si choca con un usable, lo usa y se borra.
        Usable us = usables.chocaCon(person.rectangulo);
        if (us != null) {
            us.usar(person);
            usables.removeUsable(us);
        }

    }

    public final void margenes() {

        margenDerecho = Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * 0.45f;
        margenIzquierdo = Gdx.graphics.getWidth() * 0.3f;

        margenDeAbajo = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.8f;
        margenDeArriba = Gdx.graphics.getHeight() * 0.3f;

    }

    //Evento lanzado cuando se pulsa una tecla
    @Override
    public void teclaPulsada(int keyCode) {

    }

    //Evento lanzado cuando de libera una tecla
    @Override
    public void teclaLiberada(int keyCode) {

        ///Solo para debug, para que el personaje vuele
        if (keyCode == Input.Keys.N) {
            up = 0;
        }

    }

}
