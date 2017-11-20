package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tiles.ATile;
import com.mygdx.game.Tiles.TileFactory;

import java.util.ArrayList;

/**
 * Created by berni on 2017-11-20.
 */

public class Board {

    /**
     * Size in tiles
     */
    int height = 9;
    int width = 9;
    int nbCoins = 80;
    ArrayList<Coin> coins = new ArrayList<Coin>();
    ATile[][] tiles = new ATile[width][height];

    public Board(){
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                tiles[i][j] = TileFactory.CreateWalkable(i,j);
            }
        }
    }

    public void display(SpriteBatch batch){
        batch.begin();
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                Vector2 location = tiles[i][j].getScreenLocation();
                batch.draw(tiles[i][j].getTextureRegion(), location.x, location.y);
            }
        }
        batch.end();
    }

}
