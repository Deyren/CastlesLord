package com.deyren.castleslord.game;

import com.deyren.castleslord.game.arrays.ArrayDeItems;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.deyren.castleslord.game.arrays.ArrayDeUsables;
import com.deyren.castleslord.game.arrays.ConjuntoDeSprites;
import com.deyren.castleslord.game.items.Usable;
import com.deyren.castleslord.game.items.armas.EspadaSimple;
import com.deyren.castleslord.game.items.armasabstracto.AbstractEspada;
import com.deyren.castleslord.game.items.consumible.PocionVida;
import com.deyren.castleslord.game.items.equipo.botas.BotasSimples;
import com.deyren.castleslord.game.items.equipo.CascoSimple;
import com.deyren.castleslord.game.items.equipo.GuantesSimples;
import com.deyren.castleslord.game.items.equipo.PechoSimple;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.items.usables.PocionVidaUsable;
import com.deyren.castleslord.game.movimientos.MovimientoDeJugador;
import com.deyren.castleslord.game.pantallas.Pantalla1;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.SpriteAnimado;
import com.deyren.castleslord.game.utiles.Constantes;


/**
 * Esto no se usa. el Main es CastleLordMain
 */
public class MyGdxGame extends ApplicationAdapter {

    //public static Texture tper;
    Texture fondo;
    SpriteBatch batch;

    Mapa mapa;
    OrthographicCamera camara;
    OrthographicCamera camaraQuieta;
    Protagonista person;
    MovimientoDeJugador movJugador;
    BitmapFont font;
   // int sFps, Fps;
    // Timer timer;
    ShapeRenderer lin;

    Pantalla1 p1;
    private ArrayDeEnemigos enemigos;
    private ArrayDeItems items;//Items que hay por la pantalla
    private ArrayDeUsables usables;
    //private ConjuntoDeSprites todosLosSprites[];

    @Override
    public void create() {
        //Gdx.graphics.setContinuousRendering(false);
        //Gdx.graphics.requestRendering();

        // tper = new Texture("personaje" + File.separator + File.separator +"personaje.png");
        lin = new ShapeRenderer(2);
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Fps = sFps;
//                sFps = 0;
//            }
//        }, 0, 1000);

        usables = new ArrayDeUsables(10);



        fondo = new Texture("noche.jpg");
        mapa = new Mapa("mapaprueba.tmx");
        batch = new SpriteBatch();

        camara = new OrthographicCamera();
        camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camara.update();

        camaraQuieta = new OrthographicCamera();
        camaraQuieta.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camaraQuieta.update();

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        person = new Protagonista(mapa.getRectangulosDeColision(), Constantes.Personajes.Personaje1);

        SpriteAnimado.posicionar(person, 300, 400);
        person.rectangulo.width = 64;
        person.rectangulo.height = 96;
        person.atributos.velocidadDeMovimiento = 3.4f;
        person.setEnemigos(enemigos);

        items = new ArrayDeItems(600, person);
        enemigos = new ArrayDeEnemigos(500, person);

//        todosLosSprites = new ConjuntoDeSprites[2];
//        todosLosSprites[0] = items;
//        todosLosSprites[1] = enemigos;

        movJugador = new MovimientoDeJugador(person, mapa, camara, enemigos, items, usables);

        p1 = new Pantalla1(mapa, enemigos, items, usables, camara);

        for (int i = 0; i < 10; i++) {
            PocionVida pv = new PocionVida(person.rectangulo.x + 200 + (120 * i), 100 * i);
            items.addItem(pv);
        }

        AbstractEspada espada = new EspadaSimple(person.rectangulo.x, person.rectangulo.y, enemigos);
        items.addItem(espada);

        //Equipo que sale al empezar_____________________________________________________________
        AbstractCasco casco = new CascoSimple(830, 380);
        items.addItem(casco);

        AbstractPecho pecho = new PechoSimple(870, 380);
        items.addItem(pecho);

        AbstractBotas botas = new BotasSimples(910, 380);
        items.addItem(botas);

        espada = new EspadaSimple(960, 510, enemigos);
        items.addItem(espada);

        AbstractGuantes guantes = new GuantesSimples(1060, 510);
        items.addItem(guantes);
        //________________________________________________________________________________________

        Usable posiconVida = new PocionVidaUsable(106, 410, 5000);
        usables.addUsable(posiconVida);

    }

    private void update() {
        movJugador.actualizar();
    }

    @Override
    public void render() {

        update();
        // sFps++;
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camara.update();
        //batch.setProjectionMatrix(camaraQuieta.combined);
        // batch.begin();
        //batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//dibuja la imagen de fondo
        //   batch.end();

        p1.dibujarMapa(batch);
        p1.dibujarFueraDelBatch(camara);//dibuja la vida de los enemigos, de momento

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        p1.dibujarObjetos(batch, camara);
        person.dibujar(batch, camara);
        enemigos.dibujarNumerosDeBarrasDeVida(batch);
        batch.flush();
        batch.end();

        batch.setProjectionMatrix(camaraQuieta.combined);
        batch.begin();
        debug();
        batch.end();
        person.dibujarBarraDeVida(camaraQuieta);
        //  enemigos.dibujarBarrasDeVida();
    }

    private void debug() {

        //font.setColor(Color.BLACK);
        //font.draw(batch, "FPS: : " + String.valueOf(Fps), 300, 580);
        font.draw(batch, "pulsar N para que el personaje vuele", 350, 570);
//        font.draw(batch, " Cantidad de enemigos: " + String.valueOf(enemigos.getLenght()), 5, 440);
////        font.draw(batch, "Y: " + String.valueOf(enemigo.rectangulo.y), 5, 420);
////        font.draw(batch, String.valueOf(enemigo.estaEnElSuelo), 5, 400);
//        font.draw(batch, "esta en el suelo: " + String.valueOf(person.isEstaEnElSuelo()), 5, 380);
//
//        font.draw(batch, "Tamaño: " + String.valueOf("Ancho: " + Gdx.graphics.getWidth() + " Alto: " + Gdx.graphics.getHeight()), 5, 500);
        person.toScreenString(batch, font, new Vector2(5, 250));
    }

    @Override
    public void resize(int width, int height) {
        movJugador.margenes();
    }

}
