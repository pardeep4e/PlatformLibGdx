package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import static com.mygdx.game.GameScreen.WORLD_HEIGHT;

/**
 * Created by pardeep on 14/03/17.
 */

public class Player{

    public  Animation<TextureRegion>[] animationDude = new Animation[3];
    TextureAtlas dude;
    private static final float SPEED = 3f;
    public static final float DUDE_WIDTH = 67;
    public static final float DUDE_HEIGHT = 94;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int JUMP = 2;
    int STATE;
    int x = 120,y = WORLD_HEIGHT -30;
    int jumpCount = 0;
    float gravity = -0.2f;

    Vector2 speed = new Vector2();
    Vector2 pos;
    Rectangle bounds;


    /**
     * Create player
     */
    public Player(){
        dude = new TextureAtlas(Gdx.files.internal("assets/dude/final/dude.atlas"));
        animationDude[RIGHT] = new Animation(1/10f, dude.findRegions("walk"));
        animationDude[LEFT] = new Animation(1/10f, dude.findRegions("walkl"));
        animationDude[JUMP] = new Animation(1/10f, dude.findRegions("stop"));

        pos = new Vector2(x,y);
        bounds = new Rectangle(pos.x, pos.y,DUDE_WIDTH,DUDE_HEIGHT);

        speed.y = 0.5f/2;
        STATE = 3;
    }

    /**
     * Detect if key pressed
     */
    void processKeys () {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            speed.y = 3f;
            STATE = JUMP;
            jumpCount += 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            speed.x = -SPEED;
            STATE = LEFT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            speed.x = SPEED;
            STATE = RIGHT;
        }
        else {
            speed.x = 0;
            STATE = JUMP;
        }
    }

    /**
     * Set actual postion and + Gravity
     */
    void update(){
        setPos(pos.x + speed.x, pos.y + speed.y);
        speed.y += gravity;
    }

    /**
     * Set bounds  positions
     * @param x
     * @param y
     */
    void setPos(float x, float y){
        pos.x = x;
        pos.y = y;
        bounds.setPosition(pos.x , pos.y);
    }
}
