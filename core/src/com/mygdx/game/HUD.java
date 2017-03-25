package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pardeep on 14/03/17.
 */

public class HUD {

    World world;
    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;

    HUD(World world, OrthographicCamera camera){
        this.world = world;
        this.camera = camera;
        this.camera.position.x = camera.viewportWidth /2f;
        this.camera.position.y = camera.viewportHeight /2f;
        camera.update();

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void render(int jumpCpunt){
        batch.begin();
            font.draw(batch, "Jumps: "+jumpCpunt, camera.viewportWidth-100, camera.viewportHeight-10);
        batch.end();
    }
}
