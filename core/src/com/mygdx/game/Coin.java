package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by berni on 2017-11-16.
 */

public class Coin extends ADrawable {

    Character PickedBy = null;

    public Coin(Texture t, float gridX, float gridY){
        super(t, gridX, gridY);
    }

    public boolean Pick(Character c){
        if(PickedBy != null){
            return false;
        }
        PickedBy = c;
        return true;
    }
}
