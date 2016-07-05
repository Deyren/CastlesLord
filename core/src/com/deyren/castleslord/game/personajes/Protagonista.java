 /*
 * @Ruben@
 */
package com.deyren.castleslord.game.personajes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.equipo.GestorDeEquipoEInventario;
import com.deyren.castleslord.game.items.mejoraabstracta.Mejora;
import com.deyren.castleslord.game.utiles.Barra;
import com.deyren.castleslord.game.utiles.Constantes;
import com.deyren.castleslord.game.utiles.Debug;

/**
 * Personaje principal con el que se juega
 *
 * @author Ruben
 */
public class Protagonista extends Personaje {

    protected ArrayDeMejoras mejoras;
    // protected Equipo equipo;
    // protected Inventario inventario;
    protected GestorDeEquipoEInventario gestorEquipoInventario;
    protected Animation animacionStand;
    protected Animation animacionAndando;
    protected Animation animacionAtacando;
    TextureRegion[] imagenesStand;
    TextureRegion[] imagenesAndando;
    TextureRegion[] imagenesAtacando;
    Barra barraDeVida;
    //private Proyectil proyectil;
    private ArrayDeEnemigos arrayDeEnemigos;

    public GestorDeEquipoEInventario getGestorEquipoInventario() {
        return gestorEquipoInventario;
    }

    public Protagonista(Array<RectangleMapObject> rectangulosDeColision,Constantes.Personajes imagen) {
        super(rectangulosDeColision);
        crearAnimaciones(imagen);
        barraDeVida = Barra.getBarraDeVida(new Rectangle(rectangulo));
        gestorEquipoInventario = GestorDeEquipoEInventario.getInstance(this);
        mejoras = new ArrayDeMejoras();
        atributos.vida = 2000;
        atributos.vidaMaxima = 2000;
        atributos.daño = 20;
        atributos.fuerza = 1;
        atributos.inteligencia = 1;
        
        // inicializar();
    }

  

    private void inicializar() {
    }

    public ArrayDeMejoras getMejoras() {
        return mejoras;
    }

    public void setEnemigos(ArrayDeEnemigos e) {
        arrayDeEnemigos = e;
    }

    @Override
    public void atacar() {
        atacando = true;
//        if (proyectil == null) {
//            if (mirandoHacia == MirandoHacia.Derecha) {
//                proyectil = new Proyectil(this, arrayDeEnemigos, new Rectangle(rectangulo.x, rectangulo.y + rectangulo.height / 2, 20, 20), new Texture("luzAmarilla.png"), Proyectil.Direccion.Derecha);
//            } else {
//                proyectil = new Proyectil(this, arrayDeEnemigos, new Rectangle(rectangulo.x, rectangulo.y + rectangulo.height / 2, 20, 20), new Texture("luzAmarilla.png"), Proyectil.Direccion.Izquierda);
//            }
//        }
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        super.dibujar(batch, camara);
//        if (proyectil != null) {
//            proyectil.dibujar(batch);
//            if (proyectil.rectangulo.x > Gdx.graphics.getWidth()
//                    || proyectil.rectangulo.x < 0) {
//                proyectil = null;
//            }
//        }
//        equipo.dibujar(batch, camara);
        gestorEquipoInventario.dibujar(batch, camara, 15);
    }

    public void dibujarBarraDeVida(OrthographicCamera camara) {
        barraDeVida.dibujar(atributos.vida, atributos.vidaMaxima, camara, new Rectangle(20, 300, 200, 100));
    }

    /**
     * Metodo en el que se establecen las imagenes de las animaciones
     *
     * @param imagen
     * 
     */
    @Override
    protected final void crearAnimaciones(Constantes.Personajes imagen) {
        //    atlas = new TextureAtlas(archivoPack);
        atlas = setImagen(imagen);
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
        animacionAndando = new Animation(atributos.velocidadDeMovimiento, imagenesAndando);
        animacionAtacando = new Animation(atributos.velocidadDeAtaque, imagenesAtacando);
        animacion = animacionStand;
        acumulador = 0;
        imagenActual = animacion.getKeyFrame(acumulador, true);
        rectangulo = new Rectangle(0, 100, 32, 64);//esto se debe cambiar para ajustar el tamaño a la ventana
    }

    @Override
    public void establecerAnimacion(TipoDeAnimacion tipo) {
        if (!tipoDeAnimacion.equals(tipo)) {
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

    public void addMejora(Mejora e) {
        mejoras.add(e.getTipo(), e);

    }

    @Override
    public void toScreenString(SpriteBatch batch, BitmapFont font, Vector2 pos) {
        super.toScreenString(batch, font, pos); //To change body of generated methods, choose Tools | Templates.
        mejoras.toScreenString(batch, font, new Vector2(this.rectangulo.x, this.rectangulo.y - 80));
        //gestorEquipoInventario.toScreenString(batch, font, new Vector2(this.rectangulo.x,this.rectangulo.y+100));
    }

}
