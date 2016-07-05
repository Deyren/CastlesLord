/*
 * Copyright @ negrata
 */

package com.deyren.castleslord.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Logger;

import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class Eventos implements InputProcessor {

    public static String pruebaPulsado="prueba de touch pulsado";
    private final ArrayList<Integer> teclas;
    public ArrayList<Integer> getTeclas() {
        return teclas;
    }
    
    
    private ArrayList< EventosListener> listeners;
    public void addEventosListener(EventosListener l){
        listeners.add(l);
    }
    
    
    public Eventos(){
        teclas=new ArrayList<Integer>();
        listeners=new ArrayList<EventosListener>();
    }

    public boolean isDown(int keycode){
        return teclas.contains(keycode);
    }

    @Override
    public boolean keyDown(int keycode) {
        if( ! teclas.contains(keycode)){
             teclas.add(keycode);
        }
        for (EventosListener listener : listeners) {
            listener.teclaPulsada(keycode);
        }
        
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (int i = 0; i < teclas.size(); i++) {
            if(teclas.get(i)==keycode){
                teclas.remove(i);
                break;
            }
        }       
         for (EventosListener listener : listeners) {
            listener.teclaLiberada(keycode);
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for (Integer tecla : teclas) {
            sb.append(tecla).append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean keyTyped(char character) {
     return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pruebaPulsado="TouchPulsado";
         return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        pruebaPulsado="Touch soltado";
       return false;
     }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
           return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
       return false;
    }

    @Override
    public boolean scrolled(int amount) {
       return false;
    }
    
    public static interface EventosListener{
        public void teclaPulsada(int keyCode);
        public void teclaLiberada(int keyCode);
    }
    
}

