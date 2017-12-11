package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Tiles.ATile;
import com.mygdx.game.Tiles.TileFactory;

import java.util.ArrayList;

/**
 * Created by berni on 2017-11-20.
 */

public class Board   {

    /**
     * Size in tiles
     */
    public static int height = 10;
    public static int width = 10;
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

    public void display(Batch batch){
        for(int j = height; j > 0; j--){
            for(int i = width; i > 0; i--){
                tiles[i-1][j-1].display(batch);
            }
        }
    }

}
