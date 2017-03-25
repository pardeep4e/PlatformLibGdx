package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pardeep on 10/03/17.
 */

public class GameOverScreen extends MyGdxGameScreen  {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    public GameOverScreen(Game game) {
        super(game);
    }


    public void show() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/map/gameover.jpg"));
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(45f,33f,54f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite,0,0,700,640);
        batch.end();
    }
}
