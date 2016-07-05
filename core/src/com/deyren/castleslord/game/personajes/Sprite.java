/*
 * @Ruben@
 */

package com.deyren.castleslord.game.personajes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Ruben
 */
public class Sprite {
    private Texture imagen;
    protected com.badlogic.gdx.graphics.g2d.Sprite spr;
    private Rectangle rectangulo;

    public Texture getImagen() {
        return imagen;
    }

    public void setImagen(Texture imagen) {
        this.imagen = imagen;
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.rectangulo = rectangulo;
    }

    public Sprite(Texture imagen, Rectangle rectangulo) {
        this.imagen = imagen;
        this.rectangulo = rectangulo;
        this.spr=new com.badlogic.gdx.graphics.g2d.Sprite(imagen);
        spr.setX(rectangulo.x);
        spr.setY(rectangulo.y);
        spr.setSize(rectangulo.width, rectangulo.height);
        spr.setOrigin(rectangulo.width/2, rectangulo.height/2);
    }
    boolean sub=false;
    float grados=0;
    public void dibujar(SpriteBatch batch,OrthographicCamera camara){
        spr.setX(rectangulo.x);
        spr.setY(rectangulo.y);
//        spr.setSize(rectangulo.width, rectangulo.height);
        
        if(grados>45){
            sub=false;
        }
        if(grados<-45){
            sub=true;
        }
        if(sub){
            grados+=4.3;
        }else{
            grados-=4.3;
        }
        
        spr.setRotation(grados);
        spr.draw(batch);
        //batch.draw(spr,rectangulo.x, rectangulo.y,rectangulo.width,rectangulo.height);
      //  batch.draw(imagen, rectangulo.x, rectangulo.y,rectangulo.width,rectangulo.height);
       // com.badlogic.gdx.graphics.g2d.Sprite spr=new com.badlogic.gdx.graphics.g2d.Sprite(imagen);
        
    }
    
       public void dibujar(SpriteBatch batch,OrthographicCamera camara,float rotacion){
        spr.setX(rectangulo.x);
        spr.setY(rectangulo.y);
    //    spr.setSize(rectangulo.width, rectangulo.height);
      
        spr.setRotation(rotacion);
        spr.draw(batch);
    }
}
