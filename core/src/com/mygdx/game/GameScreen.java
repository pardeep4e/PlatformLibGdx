package com.mygdx.game;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by pardeep on 13/03/17.
 */

public class GameScreen extends  MyGdxGameScreen {
    public static final int WORLD_WIDTH = 700;
    public static final int  WORLD_HEIGHT  = 640;
    OrthographicCamera worldCamera;
    FitViewport woldViewport;
    World world;
    HUD hud;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show(){
        if (! Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.graphics.setWindowedMode(WORLD_WIDTH,WORLD_HEIGHT);
        }

        worldCamera = new OrthographicCamera();
        worldCamera.update();

        woldViewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,worldCamera);
        woldViewport.apply();

        world = new World(worldCamera,WORLD_WIDTH,WORLD_HEIGHT);
        hud = new HUD(world,worldCamera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.update(delta);
        world.render();
        hud.render(world.player.jumpCount);
        if(world.statusWorld == 2){
            game.setScreen(new GameOverScreen(game));
        }
        else if(world.statusWorld == 0){
            game.setScreen(new GameWinScreen(game));
        }
    }

    public void resize(int width, int height) {
        woldViewport.update(width, height);
    }



}
