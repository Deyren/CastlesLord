/*
 * @Ruben@
 */
package com.deyren.castleslord.game.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.utiles.Constantes;

/**
 *
 * @author Ruben
 */
public abstract class SpriteAnimado {

    public static enum TipoDeAnimacion {

        stand, andando, atacando, saltando,
    }

    public static enum MirandoHacia {

        Izquierda, Derecha
    }
    protected boolean estaAndando = false;

    public boolean isEstaAndando() {
        return estaAndando;
    }

    public void setEstaAndando(boolean estaAndando) {
        this.estaAndando = estaAndando;
    }
    
    /**
     * El tipo de animacion lo establece la clase protagonista 
     * en el metodo establecerAnimacion que implementa de esta clase
     * esa implementacion es llamada desde la clase MovimientoDelJugador. <br>
     * 
     */
    protected TipoDeAnimacion tipoDeAnimacion=TipoDeAnimacion.stand;
    
    public Rectangle rectangulo;
    protected TextureRegion imagenActual;
    protected Animation animacion;
    protected float velocidadDeAnimacion = 0.2f;
    protected float acumulador;
    protected MirandoHacia mirandoHacia = MirandoHacia.Derecha;

    public MirandoHacia getMirandoHacia() {
        return mirandoHacia;
    }

    public void setMirandoHacia(MirandoHacia mirandoHacia) {
        this.mirandoHacia = mirandoHacia;
    }

    TextureAtlas atlas;

//    public float getVelocidadDeAnimacion() {
//        return velocidadDeAnimacion;
//    }
//
//    public void setVelocidadDeAnimacion(float velocidadDeAnimacion) {
//        this.velocidadDeAnimacion = velocidadDeAnimacion;
//    }
    public Rectangle getRectangulo() {
        return rectangulo;
    }

    public SpriteAnimado() {
        rectangulo = new Rectangle(0, 100, 32, 64);
    }

      protected static TextureAtlas setImagen(Constantes.Personajes imagen) {
        TextureAtlas salida = null;
        switch (imagen) {
            case Personaje1:
                salida = Constantes.Imagenes.Personajes.PERSONAJE_1;
                break;
            default:
                salida = Constantes.Imagenes.Personajes.PERSONAJE_1;
        }
        return salida;
    }
    
    
    /**
     * La implementacion debe crea las imagenes con el archivo pack. <br>
     * y debe usarse en el contructor del objeto, para que no de un
     * NullPointerException al querer usar algo aun no creado. <br>
     * La implementacion debe ser algo como esto <br>
     * atlas = new TextureAtlas(archivoPack); <br>
     * imagenesStand = new TextureRegion[2]; <br>
     * imagenesStand[0] = atlas.findRegion("0001"); <br>
     * imagenesStand[1] = atlas.findRegion("0002"); <br>
     *
     * imagenesAndando = new TextureRegion[3]; <br>
     * imagenesAndando[0] = atlas.findRegion("0003"); <br>
     * imagenesAndando[1] = atlas.findRegion("0004"); <br>
     * imagenesAndando[2] = atlas.findRegion("0005"); <br>
     *
     * imagenesAtacando = new TextureRegion[2]; <br>
     * imagenesAtacando[0] = atlas.findRegion("0006"); <br>
     * imagenesAtacando[1] = atlas.findRegion("0007"); <br>
     *
     * animacionStand = new Animation(velocidadDeAnimacion, imagenesStand); <br>
     * animacionAndando = new Animation(velocidadDeAnimacion, imagenesAndando);
     * <br>
     * animacionAtacando = new Animation(velocidadDeAnimacion,
     * imagenesAtacando); <br>
     * animacion = animacionAndando; <br>
     * acumulador = 0; <br>
     * imagenActual = animacion.getKeyFrame(acumulador, true); <br>
     * rectangulo = new Rectangle(0, 100, 32, 64); <br>
     *
     *
     */
    protected abstract void crearAnimaciones(Constantes.Personajes imagen);

    /**
     * La implementacion establece la animacion en cada momento. <br>
     * Debe ser algo como esto:<br>
     * switch (tipo) {<br>
     * case stand:<br>
     * animacion = animacionStand;<br>
     * break;<br>
     * case andando:<br>
     * animacion = animacionAndando;<br>
     * break;<br>
     * case atacando:<br>
     * animacion = animacionAtacando;<br>
     * break;<br>
     * default:<br>
     * animacion = animacionStand;<br>
     * }<br>
     * animacionStand, animacionAndando y animacionAtacando deben crearse en el
     * objeto que lo implementa, y son objetos del tipo Animation<br>
     *
     * @param tipo
     */
    public abstract void establecerAnimacion(TipoDeAnimacion tipo);

    /**
     * Devuelve true si el sprite esta dentro de la pantalla.
     * @param spr
     * @param camara
     * @return 
     */
    private static boolean estaDentroDeLaPantalla(SpriteAnimado spr, OrthographicCamera camara) {
        return !(spr.rectangulo.x + spr.rectangulo.width < camara.position.x - 450
                || spr.rectangulo.x > camara.position.x + 450
                || spr.rectangulo.y + spr.rectangulo.height < camara.position.y - 450
                || spr.rectangulo.y > camara.position.y + 450);
    }

    /**
     * Coloca el sprite que se le pasa en la posicion que se le pasa. <br>
     * @param spr
     * @param x
     * @param y 
     */
    public static void posicionar(SpriteAnimado spr, float x, float y) {
        spr.rectangulo.x = x;
        spr.rectangulo.y = y;
    }

    public void actualizar() {
        acumulador += Gdx.graphics.getDeltaTime();
        imagenActual = animacion.getKeyFrame(acumulador, true);
        if (animacion.isAnimationFinished(acumulador)) {
            acumulador = 0;
        }
    }

    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        if (estaDentroDeLaPantalla(this, camara)) {
            if (mirandoHacia == MirandoHacia.Derecha) {
                batch.draw(imagenActual, rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
            } else {
                batch.draw(imagenActual, rectangulo.x + rectangulo.width, rectangulo.y, -rectangulo.width, rectangulo.height);
            }
        }

    }

}
