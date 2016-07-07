package com.deyren.castleslord.game.interfacesvisuales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Interface visual del juego cuando se está jugango una pantalla
 * de momento solo tiene los botones pero la idea es que
 * contenga toda la interface. barra de vida, mana y demas cosas
 * De momento falta el boton de salto y el de hechizos
 * Created by User on 06/07/2016.
 */
public class InterfaceDelJuego{

    /*
     contiene los true o false de si se pulsa algun boton de la interface
     de momento falta el de hechizos
     0 = izquierda : 1 = derecha : 2 = ataque : 3 = salto
    * */
        public final boolean[] estados=new boolean[4];

        private Table contenedorMando;
        private Table contenedorBotones;
        private TextButton botonIzq,botonDer,botonAtaque,botonSalto;

    //seguramente no haya que usarlo, pero de momento lo dejo
        private InterfaceDelJuegoListener listener;
        public void setInterfaceDelJuegoListener(InterfaceDelJuegoListener listener){
            this.listener=listener;
        }

//        public TextButton getBotonIzq() {
//            return botonIzq;
//        }
//
//        public TextButton getBotonDer() {
//            return botonDer;
//        }
//
//        public TextButton getBotonAtaque() {
//            return botonAtaque;
//        }

        private Stage stage;
        public Stage getStage(){
            return stage;
        }
        public InterfaceDelJuego(){
            estados[0]=false;estados[1]=false;estados[2]=false;
            this.stage=new Stage();
            contenedorMando =new Table();
            contenedorBotones=new Table();
            BitmapFont fuen=new BitmapFont(Gdx.files.internal("Pruebas/skins/default.fnt"),false);
            TextureAtlas atlasBotones=new TextureAtlas(Gdx.files.internal("interfacejugando/BotonesJuego.pack"));
            Skin skinBotones=new Skin();
            skinBotones.addRegions(atlasBotones);

            TextButton.TextButtonStyle estiloBtnIzq=new TextButton.TextButtonStyle();
            estiloBtnIzq.up=skinBotones.getDrawable("btnIzqUp");
            estiloBtnIzq.down=skinBotones.getDrawable("btnIzqDown");
            estiloBtnIzq.font=fuen;

            TextButton.TextButtonStyle estiloBtnDer=new TextButton.TextButtonStyle();
            estiloBtnDer.up=skinBotones.getDrawable("btnDerUp");
            estiloBtnDer.down=skinBotones.getDrawable("btnDerDown");
            estiloBtnDer.font=fuen;

            final TextButton.TextButtonStyle estiloBtnAtacar=new TextButton.TextButtonStyle();
            estiloBtnAtacar.up=skinBotones.getDrawable("btnAtacarUp");
            estiloBtnAtacar.down=skinBotones.getDrawable("btnAtacarDown");
            estiloBtnAtacar.font=fuen;

            final TextButton.TextButtonStyle estiloBtnSalto=new TextButton.TextButtonStyle();
            estiloBtnSalto.up=skinBotones.getDrawable("btnSaltarUp");
            estiloBtnSalto.down=skinBotones.getDrawable("btnSaltarDown");
            estiloBtnSalto.font=fuen;


            botonIzq=new TextButton(null,estiloBtnIzq);
            botonIzq.addListener(new ClickListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.izquierdaPulsado();
                    estados[0]=true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.izquierdaSoltado();
                    estados[0]=false;
                }
            });


            botonDer=new TextButton(null,estiloBtnDer);
            botonDer.addListener(new ClickListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.derechaPulsado();
                    estados[1]=true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.derechaSoltado();
                    estados[1]=false;
                }
            });


            botonAtaque=new TextButton(null,estiloBtnAtacar);
            botonAtaque.addListener(new ClickListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.ataquePulsado();
                    estados[2]=true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //listener.ataqueSoltado();
                    estados[2]=false;
                }
            });

            botonSalto=new TextButton(null,estiloBtnSalto);
            botonSalto.addListener(new ClickListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    estados[3]=true;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    estados[3]=false;
                }
            });

            contenedorMando.add(botonIzq);
            contenedorMando.add(botonDer);
            contenedorBotones.add(botonAtaque);
            contenedorBotones.add(botonSalto);

            contenedorMando.bottom().left().padLeft(30).padBottom(10).setFillParent(true);
            contenedorBotones.bottom().right().padRight(30).padBottom(10).setFillParent(true);
            stage.addActor(contenedorMando);
            stage.addActor(contenedorBotones);


        }

        public void dibujar(){
            stage.draw();
        }


    // para borrar si se usa los boolean de estados
    public static interface InterfaceDelJuegoListener{
         void izquierdaPulsado();
         void izquierdaSoltado();
         void derechaPulsado();
         void derechaSoltado();
         void ataquePulsado();
         void ataqueSoltado();
         //void saltoPulsado();
        // void saltoSoltado();
    }
}
