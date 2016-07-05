/*
 * @Ruben@
 */
package com.deyren.castleslord.game.items;

import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.arrays.ArrayDeMejoras;
import com.deyren.castleslord.game.personajes.Enemigo;
import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.Sprite;

/**
 * Las armas tienen una referencia al array de enemigos. Tambien implementan la
 * interface listener del personaje que la lleva asi cuando el personaje ataque,
 * el arma busca si choca con algun enemigo y así quitarle vida.
 *
 * @author Ruben
 */
public abstract class Arma extends Equipable implements Personaje.PersonajeListener {
    
    protected AtributosDeEquipo atributos;
    
    protected int danio;
    
    protected ArrayDeEnemigos enemigos;

    //podría se borrado, no le hace falta de momento, ya que el evento del personaje ya manda al personaje por parametro
    //protected Personaje personajeQueLlevaElArma;
    public Arma(Sprite sprite, ArrayDeEnemigos enemigos) {
        super(sprite);
        this.enemigos = enemigos;
        // this.personajeQueLlevaElArma = personajeQueLlevaElArma;

    }
    
    public int getDanio() {
        return danio;
    }
    
    public AtributosDeEquipo getAtributos() {
        return atributos;
    }
    
    @Override
    public void atacando(Personaje p) {
        ataque((Protagonista) p);
    }

    //Metodo heredado, aqui va lo que hace el arma cuando el personaje ataca
    //Es aqui donde se quita vida al enemigo, y esto se llama desde el evento de cuando el personaje ataca, que está en esta misma clase
    public void ataque(Protagonista personaje) {
     //   Debug.Print("Ataco", this);
        Enemigo e = enemigos.chocaCon(sprite.getRectangulo());
        if (e != null) {//si choca con algun enemigo...           
            int daño = personaje.atributos.daño;
            ArrayDeMejoras ms = personaje.getMejoras();
            
            ms.usarGolpeCritico(personaje);//aumenta el daño del personaje, por eso se guarda antes, para volverselo a poner
            ms.usarCurarVidaCadaGolpe(personaje);
            ms.usarCurarManaCadaGolpe(personaje);
            
            ms.usarProvabilidadAturdir(e);//hay que pasarle al enemigo

            ms.usarVelocidadDeAtaqueTemporal(personaje);//no implementado
            ms.usarCuraEnElTiempo(personaje);//no implementado
            ms.usarDanioDeArea(personaje);//no implementado
            ms.usarDanioEnElTiempo(personaje);//no implementado
            
        
        //    Debug.Print("_____________________________________ " + e.atributos.vida, this);
       //     Debug.Print("Daño hecho: " + personaje.atributos.daño, this);
            
            e.CambiarVida(-personaje.atributos.daño);//Quita vida al enemigo  
            personaje.atributos.daño = daño;
        }
    }
    
    @Override
    public void haMuerto(Personaje p) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
