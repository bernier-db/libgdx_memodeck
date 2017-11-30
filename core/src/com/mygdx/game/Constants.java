package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by berni on 2017-11-20.
 */

public class Constants {
    public final static int TILESET_COL = 9;
    public final static int WIDTH = Gdx.graphics.getWidth();
    public final static int HEIGHT = WIDTH * 16 / 9;
    public final static int OFFSET_Y = HEIGHT / 2 - 50;

    public final static int TILE_W = 64;
    public final static int TILE_H = 48;
    public final static int TILE_SURFACE_H = 32;

    public final static int TILE_W_DRAW = WIDTH / Board.width;
    public final static int TILE_H_DRAW = TILE_W_DRAW * TILE_H / TILE_W;
    public final static int TILE_SURFACE_H_DRAW = TILE_W_DRAW / 2;


    static public final float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    public static boolean isClicked(float x, float y, float w, float h, float click_x, float click_y) {
        return (click_x < x + w && click_x > x && click_y < y + h && click_y > y);
    }
}
