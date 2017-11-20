package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

/**
 * Created by berni on 2017-11-16.
 */

public abstract class ADrawable {
    public enum CoordType {abs, grid}

    protected Vector2 Absolute_location;
    protected Vector2 Grid_location;
    protected float h;
    protected float w;
    protected Texture texture;

    public ADrawable(Texture t, float x, float y, CoordType coordType) {
        this.texture = t;
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

    public abstract void display();

    static Vector2 ScreenToGrid(float x, float y) {
        //TODO remplir
        float _x = 0, _y = 0;

        _x = (x / (Constants.TILE_W/2) + y / (Constants.TILE_SURFACE_H/2)) /2;
        _y = (y / (Constants.TILE_SURFACE_H/2) - x / (Constants.TILE_W/2)) /2;

        return new Vector2( Math.round( _x * 10 ) / 10, Math.round( _y * 10 ) / 10);
    }

    static Vector2 GridToScreen(Vector2 v) {
        return GridToScreen(v.x, v.y);
    }

    static Vector2 GridToScreen(float x, float y) {
        float _x = (x - y) * Constants.TILE_W/2;
        float _y = (x + y) * Constants.TILE_SURFACE_H/2;
        return new Vector2(_x, _y);
    }
}
