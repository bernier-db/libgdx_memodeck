package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Cards.ACard;

import java.util.ArrayList;

/**
 * Created by berni on 2017-11-30.
 */

public class Button extends ADrawable {

    boolean enable;
    float text_y;
    static float padding_bottom = 5;
    Button(float x, float y){
        super(null, x, y + padding_bottom, CoordType.abs);
        w = Constants.WIDTH;
        h = Constants.HEIGHT / 15;
        text_y = Absolute_location.y + h;
        enable = false;
    }

    public boolean isEnabled(){return enable;}
    public void setEnable(boolean b){enable = b;}

    public boolean handleClick(int x, int y) {
        if(Constants.isClicked(Absolute_location.x, Absolute_location.y+h/2, w, h, x, y)){
            return true;
        }
        return false;
    }

    @Override
    public void display(Batch batch) {

        if(enable){
            s.setColor(0.2f,1,0.2f, 1);
            txt.setColor(0.8f,1,0.8f,1);
        }
        else {
            s.setColor(0.5f, 0.5f, 0.5f,1);
            txt.setColor(0.8f, 0.8f, 0.8f,1);
        }
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.rect(Absolute_location.x, Absolute_location.y, w, h);
        s.end();

        txt.getData().setScale(2f);
        txt.getData().setLineHeight(h);
        batch.begin();
        txt.draw(batch, "Confirm", Absolute_location.x, text_y - (h - txt.getXHeight())/2, (float)Constants.WIDTH, Align.center, true);
        batch.end();

    }
}
