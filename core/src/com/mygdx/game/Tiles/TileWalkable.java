package com.mygdx.game.Tiles;

import com.mygdx.game.TextureManager;

/**
 * Created by berni on 2017-11-20.
 */

public class TileWalkable extends ATile {
    TileWalkable(float gridX, float gridY){
        super(TextureManager.getInstance().getTileTexture(), gridX, gridY);
    }
}
