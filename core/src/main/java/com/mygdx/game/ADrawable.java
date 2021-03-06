package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by berni on 2017-11-16.
 */

public abstract class ADrawable {
    public enum CoordType {abs, grid}
    public static ShapeRenderer s;
    protected Vector2 Absolute_location;
    protected Vector2 Grid_location;
    protected float h;
    protected float w;
    protected Texture texture;
    public static BitmapFont txt;

    public ADrawable(Texture t, float x, float y, CoordType coordType) {
        this.texture = t;
        if(this.s==null)
            this.s = new ShapeRenderer();
        if(this.txt==null)
            this.txt = new BitmapFont();

        switch (coordType) {
            case grid:
                this.Grid_location = new Vector2(x, y);
                this.Absolute_location = GridToScreen(x, y);
                break;
            case abs:
                this.Absolute_location = new Vector2(x, y);
                this.Grid_location = ScreenToGrid(x,y);
                break;
            default:break;
        }


    }

    public abstract void display(Batch batch);

    static Vector2 ScreenToGrid (Vector2 v){
        Vector2 temp = ScreenToGrid(v.x, v.y);
        return temp;
    }

    static Vector2 ScreenToGrid(float x, float y) {
        float _x = 0, _y = 0;

        _x = (x / (Constants.TILE_W_DRAW/2) + y / (Constants.TILE_SURFACE_H_DRAW/2)) /2;
        _y = (y / (Constants.TILE_SURFACE_H_DRAW/2) - x / (Constants.TILE_W_DRAW/2)) /2;

        Vector2 vec = new Vector2( Math.round( _x * 10 ) / 10f, Math.round( _y * 10 ) / 10f);
        return vec;
    }

    static Vector2 GridToScreen(Vector2 v) {
        return GridToScreen(v.x, v.y);
    }

    static Vector2 GridToScreen(float x, float y) {
        float _x = (x - y) * Constants.TILE_W_DRAW/2;
        float _y = (x + y) * Constants.TILE_SURFACE_H_DRAW/2;
        return new Vector2(_x, _y);
    }
}
