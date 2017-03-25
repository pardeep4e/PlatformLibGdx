package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by pardeep on 14/03/17.
 */

public class World {
    ShapeRenderer shapeRenderer;

    SpriteBatch batch;
    Player player;
    OrthographicCamera camera;
    TiledMap map;
    TiledMapRenderer tileRender;
    int elapsedTime = 0;
    int WORLD_WIDTH = 3200;
    int WORLD_HEIGHT = 640;
    int statusWorld = 1; // 0 -> win, 1 -> live, 2 -> dead

    World(OrthographicCamera camera,int WIDTH,int HEIGHT) {
        this.camera = camera;
        this.camera.position.x = WORLD_WIDTH * 0.5f;
        this.camera.position.y = WORLD_HEIGHT * 0.5f;

        camera.update();
        this.camera = camera;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player();

        map = new TmxMapLoader().load("assets/map/map.tmx");
        tileRender = new OrthogonalTiledMapRenderer(map);
        activeCollisions();
    }


    void update(float delta){
        player.update();
        player.processKeys();

        camera.position.set(player.pos.x, camera.viewportHeight / 2f, 0);
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, WORLD_WIDTH - camera.viewportWidth / 2f);
        camera.update();
    }

    /**
     * CAll all functions and Draw player
     */
    void render(){

        elapsedTime += Gdx.graphics.getDeltaTime();
        tileRender.setView(camera);
        tileRender.render();


        activeCollisions();
        activeCollisionsMAP();
        activeCollisionsWin();
        activeCollisionsDead();
        lineFisics();
        handleInput();


        batch.setProjectionMatrix(camera.combined);
        batch.begin();
            batch.draw(player.animationDude[player.STATE].getKeyFrame(elapsedTime,true),player.pos.x,player.pos.y);
        batch.end();
    }

    /**
     *  Detect if player collide whit (Map platform) Fisics
     */
    void activeCollisions(){
        MapObjects collisionObjects = map.getLayers().get("Fisics").getObjects();
        for(MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rect = ((RectangleMapObject) object);
                if(player.bounds.overlaps(rect.getRectangle( ))) {
                    player.pos.y = rect.getRectangle().y + 64;
                    player.speed.y = 0;
                }
            }
        }
    }

    /**
     * Detect if player collide whit (Map) Fisics3
     */
    void activeCollisionsMAP(){
        MapObjects collisionObjects = map.getLayers().get("Fisics3").getObjects();
        for(MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rect = ((RectangleMapObject) object);
                if(player.bounds.overlaps(rect.getRectangle( ))) {
                    player.pos.x = rect.getRectangle().x +64;
                    player.speed.y = 0;
                }
            }
        }
    }

    /**
     * Detect if player collide whit (Win Game) FisicsWin
     */
    void activeCollisionsWin(){
        MapObjects collisionObjects = map.getLayers().get("FisicsWin").getObjects();
        for(MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rect = ((RectangleMapObject) object);
                if(player.bounds.overlaps(rect.getRectangle( ))) {
                    statusWorld = 0;
                }
            }
        }
    }

    /**
     * Draw Fisic Lines
     */
    void lineFisics(){
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        MapObjects collisionObjects = map.getLayers().get("Fisics").getObjects();
        for(MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Detect if player collide whit (Dead)Fisics2
     */
    void activeCollisionsDead(){
        MapObjects collisionObjects = map.getLayers().get("Fisics2").getObjects();
        for(MapObject object : collisionObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rect = ((RectangleMapObject) object);
                if(player.bounds.overlaps(rect.getRectangle( ))) {
                    statusWorld = 2;
                }
            }
        }
    }

    /**
     * Handle zoom of camara
     */
     void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            camera.zoom -= 0.02;
        }
        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 1f);
    }


}
