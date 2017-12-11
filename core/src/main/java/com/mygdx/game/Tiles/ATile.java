package com.mygdx.game.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ADrawable;
import com.mygdx.game.Constants;

/**
 * Created by berni on 2017-11-20.
 */

public abstract class ATile extends ADrawable {

    TextureRegion region;
    BitmapFont txt;
    public ATile(Texture t, float x, float y, int tilePos) {
        super(t, x, y, CoordType.grid);
        int     spriteX = (tilePos - 1) * Constants.TILE_W,
                spriteY = 0;

        region = new TextureRegion(t, spriteX, spriteY, Constants.TILE_W, Constants.TILE_H);
        txt = new BitmapFont();
    }

    public Vector2 getScreenLocation() {
        return this.Absolute_location;
    }

    public TextureRegion getTextureRegion() {
        return region;
    }

    @Override
    public void display(Batch batch){
        batch.draw(region,  Absolute_location.x+Constants.WIDTH/2 - Constants.TILE_W_DRAW/2, Absolute_location.y+Constants.OFFSET_Y, Constants.TILE_W_DRAW, Constants.TILE_H_DRAW);
        //txt.draw(batch, "("+ (int)Grid_location.x + ", " + (int)Grid_location.y+")", Absolute_location.x+Constants.WIDTH/2+Constants.TILE_W/2, Absolute_location.y+ Constants.OFFSET_Y+ Constants.TILE_H);

    }
}
