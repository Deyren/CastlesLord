/*
 * @Ruben@
 */
package com.deyren.castleslord.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Ruben
 */
public class Mapa {

    Map ma;
    private final TiledMap tiledMap;
    private final TiledMapRenderer mapa;
    private final TiledMapTileSets tileSet;
    MapObjects objects;
    MapLayer collisionObjectLayer;

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapTileSets getTileSet() {
        return tileSet;
    }

    public TiledMapRenderer getMapa() {
        return mapa;
    }
/**
 * Se le pasa un String con la ruta del archivo .tmx creado con el programa 'tiled'
 * @param archivoTMX 
 */
    public Mapa(String archivoTMX) {
        tiledMap = new TmxMapLoader().load(archivoTMX);
        mapa = new OrthogonalTiledMapRenderer(tiledMap);
        tileSet = tiledMap.getTileSets();
        
        collisionObjectLayer = tiledMap.getLayers().get("CapaDeColision");
        objects = collisionObjectLayer.getObjects();
    }

    public Array<RectangleMapObject> getRectangulosDeColision() {
        return objects.getByType(RectangleMapObject.class);
    }

    public void render(SpriteBatch batch, OrthographicCamera camara) {
        mapa.setView(camara);
        mapa.render();
    }

//    public void moverMapa(float val, boolean x) {
//        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
//            Rectangle r = rectangleObject.getRectangle();
//            if (x) {
//                r.x += val;
//            } else {
//                r.y += val;
//            }
//        }
//    }
}
