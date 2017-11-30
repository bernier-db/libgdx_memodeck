package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Cards.DirectionCard;

import java.util.ArrayList;

/**
 * Created by berni on 2017-11-16.
 */

public class Character extends ADrawable {

    Vector2 destination;
    float maxSpeed;
    float maxForce;
    Vector2 Acc;
    Vector2 Vel;
    Vector2 desired;
    ArrayList<DirectionCard> Cards = null;

    public Character(float gridx, float gridy){
        super(null, gridx, gridy, CoordType.grid);
        maxSpeed = 10;
        maxForce = 0.5f;
        Vel = new Vector2(0,0);
        Acc = new Vector2(0, 0);
        destination = new Vector2(gridx, gridy);
    }

    public boolean isMoving(){
        return Vel.len() > 1;
    }

    public boolean isAtDestination(){
        return Math.floor(Grid_location.x*10)/10 == destination.x && Math.floor(Grid_location.y * 10)/10 == destination.y;
    }


    public void applyCard (ArrayList<DirectionCard> cards){
        Cards = cards;
        DirectionCard cur = cards.get(0);
        cards.remove(cur);
        System.out.println("Applying card: " + cur.getDist() + " " + cur.getDir());
        //N3  S2  E1  W4
        switch(cur.getDir()){
            case N:
                this.destination.y -= cur.getDist();
                break;
            case S:
                this.destination.y += cur.getDist();
                break;
            case E:
                this.destination.x -= cur.getDist();
                break;
            case W:
                this.destination.x += cur.getDist();
                break;
        }
        if (this.destination.y > Board.height-1) this.destination.y = Board.height-1;
        else if (this.destination.y < 0)
            this.destination.y = 0;
        if (this.destination.x > Board.width-1) this.destination.x = Board.width-1;
        else if (this.destination.x < 0)
            this.destination.x = 0;
    }


    public void update(){
        if(Cards != null && isAtDestination() &&  Cards.size() > 0)
            applyCard(Cards);

        move();

        Vel.add(Acc);
        Acc.setZero();
        this.Vel.limit(this.maxSpeed);
        Absolute_location.add(Vel);
        Grid_location = ADrawable.ScreenToGrid(Absolute_location);
    }

    private void move() {
        Vector2 target = ADrawable.GridToScreen(this.destination);
        desired = target.cpy().sub(Absolute_location);

        float d = desired.len();

        desired.nor();
        if (d < 128) {
            float m = Constants.map(d, 0, 128, 0, this.maxSpeed);
            this.desired.scl(m);
        } else {
            this.desired.scl(this.maxSpeed);
        }


        Vector2 steer = desired.cpy().sub(Vel);
        steer.limit(maxForce);
        applyForce(steer);
    }

    public void applyForce( Vector2 f) {
        Acc.add(f);
    }

    @Override
    public void display(Batch batch){
        Gdx.gl.glLineWidth(2);
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(0,0,1,1);
        s.circle(this.Absolute_location.x + Constants.WIDTH/2, this.Absolute_location.y + Constants.OFFSET_Y + Constants.TILE_SURFACE_H_DRAW/2, (float)Math.PI*2);
        s.end();
        s.begin(ShapeRenderer.ShapeType.Line);
        s.setColor(0,0,0,1);
        s.circle(this.Absolute_location.x + Constants.WIDTH/2, this.Absolute_location.y + Constants.OFFSET_Y + Constants.TILE_SURFACE_H_DRAW/2, (float)Math.PI*2);
        s.end();
    }


    public Vector2 getGridLocation(){return Grid_location;}
}
