/*
 * @Ruben@
 */
package com.deyren.castleslord.game.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Barra para mostrar vida mana , etc
 *
 * @author Ruben
 */
public class Barra {

    public final Rectangle rectangulo;
    ShapeRenderer sr, sr2;
    public int anchoDelBorde = 2;
    private final Color colorLleno;
    private final Color colorMitad;
    private final Color colorBajo;
    private BitmapFont fuente;

    public Barra(Rectangle rectangulo, Color colorLleno, Color colorMitad, Color colorBajo) {
        this.rectangulo = rectangulo;
        this.colorLleno = colorLleno;
        this.colorMitad = colorMitad;
        this.colorBajo = colorBajo;
        sr = new ShapeRenderer();
        sr2 = new ShapeRenderer();
        fuente=new BitmapFont();
    }

     public void dibujar(int cantidadActual, int cantidadMaxima, OrthographicCamera camara, Rectangle rect) {
        rectangulo.x = rect.x;
        rectangulo.y = rect.y + rect.height + 10;
        rectangulo.width = rect.width - 10;
        rectangulo.height = 8;
        sr.setProjectionMatrix(camara.combined);
        sr.setColor(Color.WHITE);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(rectangulo.x - anchoDelBorde / 2, rectangulo.y - anchoDelBorde / 2, rectangulo.width + anchoDelBorde, rectangulo.height + anchoDelBorde);
        sr.end();
        /*
         cantidamaxima       100%
         cantidadActual            x%
         */
        int can = cantidadActual * 100 / cantidadMaxima;
        if (can < 15) {
            sr2.setColor(colorBajo);
        } else if (can < 40) {
            sr2.setColor(colorMitad);
        } else {
            sr2.setColor(colorLleno);
        }
        /*
         ancho rectangulo  =         100%
                     x                =         can%
         */
        int s = (int) (can * rectangulo.width / 100);
        sr2.setProjectionMatrix(camara.combined);
        sr2.begin(ShapeRenderer.ShapeType.Filled);
        sr2.rect(rectangulo.x, rectangulo.y, s, rectangulo.height);
        sr2.end();
    }
     
     public void dibujarNumeros(int cantidadActual, int cantidadMaxima,SpriteBatch batch){
          fuente.draw(batch, String.valueOf(cantidadActual+"/"+cantidadMaxima), rectangulo.x, rectangulo.y+rectangulo.height*3);
     }



    public static Barra getBarraDeVida(Rectangle rectangulo) {
        Barra b = new Barra(rectangulo, Color.GREEN, Color.ORANGE, Color.RED);
        return b;
    }

    public static Barra getBarraDeMana(Rectangle rectangulo) {
        Barra b = new Barra(rectangulo, Color.BLUE, Color.BLUE, Color.BLUE);
        return b;
    }
}
