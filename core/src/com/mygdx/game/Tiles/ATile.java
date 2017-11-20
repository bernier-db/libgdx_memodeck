package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ADrawable;
import com.mygdx.game.Constants;

/**
 * Created by berni on 2017-11-20.
 */

public abstract class ATile extends ADrawable {

    TextureRegion region;

    public ATile(Texture t, float x, float y, float spriteX, float spriteY){
        super(t, x, y, CoordType.grid);
        region = new TextureRegion(t, spriteX, spriteY, Constants.TILE_W, Constants.TILE_H);

    }

    public Vector2 getScreenLocation(){return this.Absolute_location;}

    public TextureRegion getTextureRegion(){
        return region;
    }
}
