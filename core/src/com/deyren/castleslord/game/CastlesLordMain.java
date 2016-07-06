package com.deyren.castleslord.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.deyren.castleslord.game.arrays.ArrayDeEnemigos;
import com.deyren.castleslord.game.arrays.ArrayDeItems;
import com.deyren.castleslord.game.arrays.ArrayDeUsables;
import com.deyren.castleslord.game.items.Usable;
import com.deyren.castleslord.game.items.armas.EspadaSimple;
import com.deyren.castleslord.game.items.armasabstracto.AbstractEspada;
import com.deyren.castleslord.game.items.consumible.PocionVida;
import com.deyren.castleslord.game.items.equipo.CascoSimple;
import com.deyren.castleslord.game.items.equipo.GuantesSimples;
import com.deyren.castleslord.game.items.equipo.PechoSimple;
import com.deyren.castleslord.game.items.equipo.botas.BotasSimples;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractBotas;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractCasco;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractGuantes;
import com.deyren.castleslord.game.items.equipoabstracto.AbstractPecho;
import com.deyren.castleslord.game.items.usables.PocionVidaUsable;
import com.deyren.castleslord.game.movimientos.MovimientoDeJugador;
import com.deyren.castleslord.game.pantallas.Pantalla1;
import com.deyren.castleslord.game.personajes.Personaje;
import com.deyren.castleslord.game.personajes.Protagonista;
import com.deyren.castleslord.game.personajes.SpriteAnimado;
import com.deyren.castleslord.game.utiles.Constantes;

//Falta el boton de salto
class InterfaceDeJuego{
	private Table contenedorMando;
	private Table contenedorBotones;
	private TextButton botonIzq,botonDer,botonAtaque;

	public TextButton getBotonIzq() {
		return botonIzq;
	}

	public TextButton getBotonDer() {
		return botonDer;
	}

	public TextButton getBotonAtaque() {
		return botonAtaque;
	}

	Stage stage;
	public InterfaceDeJuego(Stage stage){
		this.stage=stage;
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

		TextButton.TextButtonStyle estiloBtnAtacar=new TextButton.TextButtonStyle();
		estiloBtnAtacar.up=skinBotones.getDrawable("btnAtacarUp");
		estiloBtnAtacar.down=skinBotones.getDrawable("btnAtacarDown");
		estiloBtnAtacar.font=fuen;

		botonIzq=new TextButton(null,estiloBtnIzq);
		botonDer=new TextButton(null,estiloBtnDer);
		botonAtaque=new TextButton(null,estiloBtnAtacar);

		contenedorMando.add(botonIzq);
		contenedorMando.add(botonDer);
		contenedorBotones.add(botonAtaque);

		contenedorMando.bottom().left().padLeft(30).padBottom(10).setFillParent(true);
		contenedorBotones.bottom().right().padRight(30).padBottom(10).setFillParent(true);
		stage.addActor(contenedorMando);
		stage.addActor(contenedorBotones);
	}

	public void render(){
		stage.draw();
	}
}

/*
//________________para pruebas______________
public class CastlesLordMain extends ApplicationAdapter {
	SpriteBatch batch;
	Skin skin;
	Stage stage;
	InterfaceDeJuego interJuego;
	@Override
	public void create() {
		batch=new SpriteBatch();
		skin=new Skin(Gdx.files.internal("Pruebas/skins/uiskin.json"));
		stage=new Stage();

		final TextButton button = new TextButton("Click me", skin, "default");

		button.setWidth(200f);
		button.setHeight(20f);
		button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight() - 20f);

		button.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				button.setText("Boton pulsado");
			}
		});

		Label label=new Label("Un label",skin);
		label.setWidth(200f);
		label.setHeight(20f);
		label.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight() - 50f);


		Table tabla=new Table(skin);
		tabla.center().setFillParent(true);


		Label nombre=new Label("Nombre",skin);
		TextField name=new TextField("",skin);
		Label password=new Label("Password",skin);
		TextField pass=new TextField("",skin);

		tabla.add(nombre);
		tabla.add(name);
		tabla.row();//pasa a la siguiente fila
		tabla.add(password);
		tabla.add(pass);

		stage.addActor(tabla);


		stage.addActor(button);
		stage.addActor(label);


		TextureAtlas botonAtlas=new TextureAtlas(Gdx.files.internal("Pruebas/btns.pack"));
		Skin botonSkin=new Skin();

		botonSkin.addRegions(botonAtlas);

		BitmapFont fuen=new BitmapFont(Gdx.files.internal("Pruebas/skins/default.fnt"),false);


		TextButton.TextButtonStyle bStyle=new TextButton.TextButtonStyle();
		bStyle.up=botonSkin.getDrawable("btn1S");
		bStyle.down=botonSkin.getDrawable("btn1P");
 		bStyle.font=fuen;

		TextButton botIma=new TextButton("",bStyle);
		botIma.setPosition(100,100);
		botIma.setSize(50,50);

		tabla.row();
		tabla.add(botIma);

		interJuego=new InterfaceDeJuego(stage);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width,height);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		stage.draw();
		interJuego.render();
		batch.end();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
// ________ fin para pruebas _____

*/




public class CastlesLordMain extends ApplicationAdapter {
	//public static Texture tper;
	Texture fondo;
	SpriteBatch batch;
	private Stage stage;
	private InterfaceDeJuego interfaceDeJuego;

	Mapa mapa;
	OrthographicCamera camara;
	OrthographicCamera camaraQuieta;
	Protagonista person;
	MovimientoDeJugador movJugador;
	BitmapFont font;
	// int sFps, Fps;
	// Timer timer;
	ShapeRenderer lin;

	Pantalla1 p1;
	private ArrayDeEnemigos enemigos;
	private ArrayDeItems items;//Items que hay por la pantalla
	private ArrayDeUsables usables;
	//private ConjuntoDeSprites todosLosSprites[];


	private boolean pruebaTouch=false;

	@Override
	public void create() {
		//Gdx.graphics.setContinuousRendering(false);
		//Gdx.graphics.requestRendering();

		// tper = new Texture("personaje" + File.separator + File.separator +"personaje.png");
		lin = new ShapeRenderer(2);
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Fps = sFps;
//                sFps = 0;
//            }
//        }, 0, 1000);

		usables = new ArrayDeUsables(10);
		fondo = new Texture("noche2.jpg");
		mapa = new Mapa("mapaprueba.tmx");
		batch = new SpriteBatch();

		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camara.update();

		camaraQuieta = new OrthographicCamera();
		camaraQuieta.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraQuieta.update();

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		person = new Protagonista(mapa.getRectangulosDeColision(), Constantes.Personajes.Personaje1);

		SpriteAnimado.posicionar(person, 300, 400);
		person.rectangulo.width = 64;
		person.rectangulo.height = 96;
		person.atributos.velocidadDeMovimiento = 3.4f;
		person.setEnemigos(enemigos);

		items = new ArrayDeItems(600, person);
		enemigos = new ArrayDeEnemigos(500, person);



		movJugador = new MovimientoDeJugador(person, mapa, camara, enemigos, items, usables);

		p1 = new Pantalla1(mapa, enemigos, items, usables, camara);

		for (int i = 0; i < 10; i++) {
			PocionVida pv = new PocionVida(person.rectangulo.x + 200 + (120 * i), 100 * i);
			items.addItem(pv);
		}

		AbstractEspada espada = new EspadaSimple(person.rectangulo.x, person.rectangulo.y, enemigos);
		items.addItem(espada);

		//Equipo que sale al empezar_____________________________________________________________
		AbstractCasco casco = new CascoSimple(830, 380);
		items.addItem(casco);

		AbstractPecho pecho = new PechoSimple(870, 380);
		items.addItem(pecho);

		AbstractBotas botas = new BotasSimples(910, 380);
		items.addItem(botas);

		espada = new EspadaSimple(960, 510, enemigos);
		items.addItem(espada);

		AbstractGuantes guantes = new GuantesSimples(1060, 510);
		items.addItem(guantes);
		//________________________________________________________________________________________

		Usable posiconVida = new PocionVidaUsable(106, 410, 5000);
		usables.addUsable(posiconVida);


		stage=new Stage();
		interfaceDeJuego=new InterfaceDeJuego(stage);

		interfaceDeJuego.getBotonIzq().addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Personaje.moverIzquierda(person);
			}
		});
		interfaceDeJuego.getBotonDer().addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				pruebaTouch=true;
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				  pruebaTouch=false;
			}
		});

		Gdx.input.setInputProcessor(stage);

	}

	private void update() {

		if(pruebaTouch){
			Personaje.moverDerecha(person);
		}

		movJugador.actualizar();
	}

	@Override
	public void render() {

		update();
		// sFps++;
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camara.update();

		batch.setProjectionMatrix(camaraQuieta.combined);
		batch.begin();
		batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//dibuja la imagen de fondo
		batch.end();

		p1.dibujarMapa(batch);
		p1.dibujarFueraDelBatch(camara);//dibuja la vida de los enemigos, de momento

		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		p1.dibujarObjetos(batch, camara);
		person.dibujar(batch, camara);
		enemigos.dibujarNumerosDeBarrasDeVida(batch);
		interfaceDeJuego.render();
		batch.flush();
		batch.end();

		batch.setProjectionMatrix(camaraQuieta.combined);
		batch.begin();
		debug();
		batch.end();
		person.dibujarBarraDeVida(camaraQuieta);
		//  enemigos.dibujarBarrasDeVida();
	}

	private void debug() {

		//font.setColor(Color.BLACK);
		font.draw(batch, Eventos.pruebaPulsado, 400, 400);
		font.draw(batch, "pulsar N para que el personaje vuele", 350, 370);
//        font.draw(batch, " Cantidad de enemigos: " + String.valueOf(enemigos.getLenght()), 5, 440);
//        font.draw(batch, "Y: " + String.valueOf(enemigo.rectangulo.y), 5, 420);
//        font.draw(batch, String.valueOf(enemigo.estaEnElSuelo), 5, 400);
//        font.draw(batch, "esta en el suelo: " + String.valueOf(person.isEstaEnElSuelo()), 5, 380);
//
//        font.draw(batch, "Tamaño: " + String.valueOf("Ancho: " + Gdx.graphics.getWidth() + " Alto: " + Gdx.graphics.getHeight()), 5, 500);
		person.toScreenString(batch, font, new Vector2(5, 250));
	}

	@Override
	public void resize(int width, int height) {
		movJugador.margenes();
	}

}



