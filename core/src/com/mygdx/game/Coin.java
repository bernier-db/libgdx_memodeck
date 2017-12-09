package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by berni on 2017-11-16.
 */

public class Coin extends ADrawable {

    Character PickedBy = null;
    int frameIndex = 0;
    TextureRegion region;
    int NumberOfFrames = 10;
    int OrigSize = 44;
    float counter = 0;

    public Coin(float gridX, float gridY){
        super(TextureManager.getInstance().getCoinTexture(), gridX, gridY, CoordType.grid);
        w = 11;
        h= 11;
        region = new TextureRegion(texture, OrigSize*frameIndex, 0, OrigSize, OrigSize);
    }

    public boolean Pick(Character c){
        if(PickedBy != null){
            return false;
        }
        PickedBy = c;
        return true;
    }

    void update(){
        counter += Gdx.graphics.getDeltaTime();
        if(counter > 120) {
            frameIndex = ++this.frameIndex % this.NumberOfFrames;
            region.setRegion(frameIndex*OrigSize,0,OrigSize,OrigSize);
        }

    }

    @Override
    public void display(Batch batch){
        batch.draw(region, Absolute_location.x + Constants.WIDTH/2 + this.w/2, Absolute_location.y + Constants.OFFSET_Y, 22,22);
    }
}
