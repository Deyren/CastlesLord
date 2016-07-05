/*
 * @Ruben@
 */
package com.deyren.castleslord.game.movimientos;

import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.SpriteAnimado;

/**
 *
 * @author Ruben
 */
public class MovimientoDeAtrasALante implements MovimientoUI {

    private final Personaje[] enemigos;
    Personaje protagonista;
    private int minimo, maximo;
    private boolean direccion = false;//false = izquierda
    Personaje personajeDeEsteMovimiento = null;

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = (int) this.personajeDeEsteMovimiento.rectangulo.x - minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = (int) this.personajeDeEsteMovimiento.rectangulo.x + maximo;
    }

    public Personaje getPersonajeDeEsteMovimiento() {
        return personajeDeEsteMovimiento;
    }

    public void setPersonajeDeEsteMovimiento(Personaje personajeDeEsteMovimiento) {
        this.personajeDeEsteMovimiento = personajeDeEsteMovimiento;

    }

    public MovimientoDeAtrasALante(Personaje[] enemigos, Personaje protagonista) {
        this.enemigos = enemigos;
        this.protagonista = protagonista;
        this.minimo = 0;
        this.maximo = 0;
    }

    int upAt=0;//tiempo que esta aturdido,. se quitará
    @Override
    public void mover() {
        
        if(personajeDeEsteMovimiento.isAturdido()){
             personajeDeEsteMovimiento.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.stand);
            return;
             
        }
        
    
        if (Math.abs(personajeDeEsteMovimiento.rectangulo.x - protagonista.rectangulo.x) < 40
                && Math.abs(personajeDeEsteMovimiento.rectangulo.y - protagonista.rectangulo.y) < 90) {
            personajeDeEsteMovimiento.atacar();
            personajeDeEsteMovimiento.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.atacando);
        }
        if ( ! personajeDeEsteMovimiento.isAtacando()) {
            if (personajeDeEsteMovimiento.rectangulo.x <= minimo) {
                direccion = true;
            }
            if (personajeDeEsteMovimiento.rectangulo.x >= maximo) {
                direccion = false;
            }
            // System.out.println(direccion);
            if (direccion) {
                Personaje.moverDerecha(personajeDeEsteMovimiento);
            } else {
                Personaje.moverIzquierda(personajeDeEsteMovimiento);
            }
            
            if (personajeDeEsteMovimiento.getChocaCon() == Personaje.ChocaCon.Derecha
                    || personajeDeEsteMovimiento.getChocaCon() == Personaje.ChocaCon.Izquierda) {
                personajeDeEsteMovimiento.saltar();
            }

            personajeDeEsteMovimiento.establecerAnimacion(SpriteAnimado.TipoDeAnimacion.andando);

        } else {// si esta atacando

            if (personajeDeEsteMovimiento.rectangulo.x <= protagonista.rectangulo.x) {
                direccion = true;
            }
            if (personajeDeEsteMovimiento.rectangulo.x >= protagonista.rectangulo.x) {
                direccion = false;
            }
            // System.out.println(direccion);
            if (direccion) {
                personajeDeEsteMovimiento.setMirandoHacia(SpriteAnimado.MirandoHacia.Derecha);
            } else {
                personajeDeEsteMovimiento.setMirandoHacia(SpriteAnimado.MirandoHacia.Izquierda);
            }

        }

        //Esto es solo de prueba, es para quitar, los enemigos no chocaran entre ellos-----------------------------------
        for (Personaje enemigo : enemigos) {
            if (enemigo != null && ! enemigo.equals(personajeDeEsteMovimiento)) {

                if (Math.abs(personajeDeEsteMovimiento.rectangulo.x - enemigo.rectangulo.x) < 40
                        && Math.abs(personajeDeEsteMovimiento.rectangulo.y - enemigo.rectangulo.y) < 90) {
                    personajeDeEsteMovimiento.setEstaAndando(false);
                    enemigo.setEstaAndando(false);
                }

                if (personajeDeEsteMovimiento.rectangulo.overlaps(enemigo.rectangulo)) {
                    double angulo = Math.atan2(enemigo.rectangulo.y - personajeDeEsteMovimiento.rectangulo.y, enemigo.rectangulo.x - personajeDeEsteMovimiento.rectangulo.x);

                    if (!enemigo.isAtacando()) {
                        enemigo.rectangulo.x += (Math.cos(angulo) * personajeDeEsteMovimiento.atributos.velocidadDeMovimiento);

                    }
                    if (!personajeDeEsteMovimiento.isAtacando()) {
                        personajeDeEsteMovimiento.rectangulo.x -= (Math.cos(angulo) * personajeDeEsteMovimiento.atributos.velocidadDeMovimiento);

                    }
                    personajeDeEsteMovimiento.setEstaAndando(false);
                    enemigo.setEstaAndando(false);

                    if (angulo < 0) {
                        personajeDeEsteMovimiento.rectangulo.y -= (Math.sin(angulo) * personajeDeEsteMovimiento.atributos.velocidadDeMovimiento);
                        //estaEnElSuelo=true;
                    } else {

                        enemigo.rectangulo.y -= (Math.sin(angulo) * personajeDeEsteMovimiento.atributos.velocidadDeMovimiento);
                        //enemigo.estaEnElSuelo=true;
                    }

                }
            }
        }
        //________________________________________________________________________________________________________________
    }

}
