package com.mygdx.game.Tiles;

/**
 * Created by berni on 2017-11-20.
 */

public class TileFactory {
    public static TileWalkable CreateWalkable(float x, float y){
        return new TileWalkable(x,y);
    }
}
