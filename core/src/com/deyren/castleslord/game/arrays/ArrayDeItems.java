/*
 * @Ruben@
 */
package com.deyren.castleslord.game.arrays;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deyren.castleslord.game.equipo.GestorDeEquipoEInventario;
import com.deyren.castleslord.game.items.AbstractItem;
import com.deyren.castleslord.game.items.Equipable;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.SpriteAnimado;

/**
 * Array de los items que hay en la pantalla implementa ConjuntoDeSprites, pero
 * puede que eso ya no sirva...
 *
 * @author Ruben
 */
public class ArrayDeItems implements ConjuntoDeSprites, AbstractItem.ItemListener, GestorDeEquipoEInventario.GestorDeEquipoEInventarioListener {

    private AbstractItem items[];

    /**
     * de momento se usa para cuando el gestor de equipo devuelva un item, saber
     * la posicion del personaje y ponerlo en base a esa posicion tembien se usa
     * para poner el listener del gestor de inventario que tiene el Protagonista
     */
    private Protagonista protagonista;

    public ArrayDeItems(int cantidad, Protagonista protagonista) {
        items = new AbstractItem[cantidad];
        this.protagonista = protagonista;
        initListener();
    }

    private void initListener() {
        protagonista.getGestorEquipoInventario().setGestorDeEquipoEInventarioListener(this);
    }

    public void addItem(AbstractItem item) {
        item.setItemListener(this);
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }

    public void addItem(AbstractItem item, int posicion) {
        item.setItemListener(this);
        this.items[posicion] = item;
    }

    //true si será x
    @Override
    public void mover(float cantidad, boolean xOy) {
        if (xOy) {
            for (AbstractItem item : items) {
                if (item != null) {
                    item.getSprite().getRectangulo().x += cantidad;
                }
            }
        } else {
            for (AbstractItem item : items) {
                if (item != null) {
                    item.getSprite().getRectangulo().y += cantidad;
                }
            }
        }
    }

    public AbstractItem chocaCon(Rectangle rect) {
        AbstractItem salida = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getSprite().getRectangulo().overlaps(rect)) {
                    salida = (AbstractItem) items[i];
                    break;
                }
            }
        }
        return salida;
    }

    @Override
    public void dibujar(SpriteBatch batch, OrthographicCamera camara) {
        for (AbstractItem item : items) {
            if (item != null) {
                item.dibujar(batch, camara);
            }

        }
    }

// Eventos de itemListener
//Evento cuando un item es cogido por el personaje.
    //Borra el item del array
    @Override
    public void itemCogido(AbstractItem item) {
        for (int i = 0; i < items.length; i++) {
            if (item.equals(items[i])) {
                items[i] = null;
                break;
            }
        }
    }

    @Override
    public void itemDesaparece(AbstractItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    //   Metodos de eventos del gestor de equipo
    @Override
    public void equipableAgregadoAlEquipo(Equipable e) {
        //Debug.Print("Agregado " + e, this);
    }

    @Override
    public void equipableDevuelto(Equipable e) {
        if (e != null) {
            Rectangle r = new Rectangle(protagonista.getRectangulo());
            protagonista.getMejoras().removeMejora(e.getMejora());
            r.y -= 32;
            if (protagonista.getMirandoHacia() == SpriteAnimado.MirandoHacia.Derecha) {
                r.x += 100;
            } else {
                r.x -= 100;
            }
            e.getSprite().setRectangulo(r);
            this.addItem(e);
        }

    }

    @Override
    public void equipablesDevueltos(Equipable[] e) {
        float posx = 0;
        for (Equipable e1 : e) {
            if (e1 != null) {
                Rectangle r = new Rectangle(protagonista.getRectangulo());
                  if (e1.getMejora() != null) {
                       protagonista.getMejoras().removeMejora(e1.getMejora());
                  }
                if (protagonista.getMirandoHacia() == SpriteAnimado.MirandoHacia.Derecha) {
                    r.x += 100 + (posx += 32);
                } else {
                    r.x -= 100 - (posx -= 32);
                }
                e1.getSprite().setRectangulo(r);
                this.addItem(e1);
            }

        }
    }

    @Override
    public void itemAgregadoAlInventario(AbstractItem item) {
      // Debug.Print("Agregado " + item, this);
    }

    @Override
    public void itemDevuelto(AbstractItem c) {
     
    }

    @Override
    public void itemsDevueltos(AbstractItem[] cs) {
      float posx = 0;
        for (AbstractItem e1 : cs) {
            if (e1 != null) {
                Rectangle r = new Rectangle(protagonista.getRectangulo());
                if (protagonista.getMirandoHacia() == SpriteAnimado.MirandoHacia.Derecha) {
                    r.x += 100 + (posx += 32);
                } else {
                    r.x -= 100 - (posx -= 32);
                }
                e1.getSprite().setRectangulo(r);
                this.addItem(e1);
            }

        }
    }
    

    

}
