package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by tarda on 10/03/17.
 */

public class WelcomeScreen extends MyGdxGameScreen  {

    float startGameTimer;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;


    public WelcomeScreen(Game game) {
        super(game);
    }

    @Override
    public void show(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/map/welcome.jpg"));
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float delta) {
        startGameTimer += delta;
        Gdx.gl.glClearColor(0f,0f,0f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(sprite,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        if(startGameTimer > 2) {
            game.setScreen(new GameScreen(game));
        }
    }


}
