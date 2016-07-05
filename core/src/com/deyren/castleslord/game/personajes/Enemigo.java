/*
 * @Ruben@
 */
package com.deyren.castleslord.game.personajes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.deyren.castleslord.game.movimientos.MovimientoDeAtrasALante;
import com.deyren.castleslord.game.movimientos.MovimientoUI;
import com.deyren.castleslord.game.utiles.Barra;
import com.deyren.castleslord.game.utiles.Constantes;
import com.deyren.castleslord.game.utiles.Debug;

/**
 *
 * @author Ruben
 */
public class Enemigo extends Personaje {

    protected Animation animacionStand;
    protected Animation animacionAndando;
    protected Animation animacionAtacando;
    TextureRegion[] imagenesStand;
    TextureRegion[] imagenesAndando;
    TextureRegion[] imagenesAtacando;

    Barra barraDeVida;
    //private Protagonista prota;
    protected MovimientoUI movimiento;

    //El parametro enemigos se quitará
    public Enemigo(Array<RectangleMapObject> rectangulosDeColision, MovimientoUI movimiento,Constantes.Personajes imagen) {
        super(rectangulosDeColision);
        //this.prota = prota;
        this.movimiento = movimiento;
        crearAnimaciones(imagen);
        barraDeVida = Barra.getBarraDeVida(new Rectangle(this.rectangulo));
        barraDeVida.anchoDelBorde = 2;
        atributos.vidaMaxima = 200;
        atributos.vida = 200;
        atributos.daño=20;
      //  aturdido=true;
    }
    

    @Override
    public void actualizar() {
        super.actualizar(); //To change body of generated methods, choose Tools | Templates.
        movimiento.mover();
     

        //-----------------------------------------------------------------------------------------------------------------------------------------------------
    }

    public void dibujarBarraDeVida(OrthographicCamera camara) {
        barraDeVida.dibujar(atributos.vida, atributos.vidaMaxima, camara, rectangulo);
    }

    //solo para debug
    public void dibujarNumerosBarraDeVida(SpriteBatch batch) {
        barraDeVida.dibujarNumeros(atributos.vida, atributos.vidaMaxima,batch);
    }
    
    public static Enemigo crearEnemigo(Array<RectangleMapObject> rectangulosDeColision,Constantes.Personajes imagen, Protagonista prota, float x, float y, Personaje[] enemigos) {
        Enemigo enemy = new Enemigo(rectangulosDeColision,null, imagen);
        MovimientoDeAtrasALante mov = new MovimientoDeAtrasALante(enemigos, prota);
        mov.setPersonajeDeEsteMovimiento(enemy);
        enemy.rectangulo.x = x;
        enemy.rectangulo.y = y;
        enemy.rectangulo.width = Constantes.anchoDeSpriteDeEnemigo;
        enemy.rectangulo.height = Constantes.altoDeSpriteDeEnemigo;
        enemy.atributos.velocidadDeMovimiento = 2;
        mov.setMinimo(500);
        mov.setMaximo(500);
        enemy.movimiento = mov;
        return enemy;
    }

    @Override
    public void atacar() {
        atacando = true;
        
    }

    @Override
    protected final void crearAnimaciones(Constantes.Personajes imagen) {
       // atlas = new TextureAtlas(archivoPack);
        atlas=setImagen(imagen);
        imagenesStand = new TextureRegion[2];
        imagenesStand[0] = atlas.findRegion("0001");
        imagenesStand[1] = atlas.findRegion("0002");

        imagenesAndando = new TextureRegion[3];
        imagenesAndando[0] = atlas.findRegion("0003");
        imagenesAndando[1] = atlas.findRegion("0004");
        imagenesAndando[2] = atlas.findRegion("0005");

        imagenesAtacando = new TextureRegion[4];
        imagenesAtacando[0] = atlas.findRegion("0006");
        imagenesAtacando[1] = atlas.findRegion("0007");
        imagenesAtacando[2] = atlas.findRegion("0008");
        imagenesAtacando[3] = atlas.findRegion("0009");

        animacionStand = new Animation(velocidadDeAnimacion, imagenesStand);
        animacionAndando = new Animation(velocidadDeAnimacion, imagenesAndando);
        animacionAtacando = new Animation(velocidadDeAnimacion, imagenesAtacando);
        animacion = animacionAndando;
        acumulador = 0;
        imagenActual = animacion.getKeyFrame(acumulador, true);
        rectangulo = new Rectangle(0, 100, 32, 64);
    }

    @Override
    public void establecerAnimacion(TipoDeAnimacion tipo) {
       if ( ! tipoDeAnimacion.equals(tipo)) {
            acumulador = 0;
            switch (tipo) {
                case stand:
                    animacion = animacionStand;
                    break;
                case andando:
                    animacion = animacionAndando;
                    break;
                case atacando:               
                    animacion = animacionAtacando;
                    break;
                case saltando:
                     Debug.Print("saltando no tiene animacion de momento", this);
                default:
                    animacion = animacionStand;
            }
            tipoDeAnimacion = tipo;
        }
    }

}
