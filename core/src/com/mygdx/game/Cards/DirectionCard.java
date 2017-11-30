package com.mygdx.game.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Constants;

import static com.mygdx.game.Cards.DirectionCard.Direction.E;
import static com.mygdx.game.Cards.DirectionCard.Direction.N;
import static com.mygdx.game.Cards.DirectionCard.Direction.S;
import static com.mygdx.game.Cards.DirectionCard.Direction.W;

/**
 * Created by berni on 2017-11-16.
 */

public class DirectionCard extends ACard {

    public enum Direction{N, S, E, W}
    Direction dir;
    int dist;
    int pos;
    DirectionCard(int pos, int dist, int dir){
        super(0, 0, CoordType.abs);
        this.dist = dist;
        this.pos = pos;
        switch (dir){
            case 0: this.dir = W;
        break;
            case 1: this.dir = E;
        break;
            case 2: this.dir = S;
        break;
            case 3: this.dir = N;
        break;
        }

        int spriteX = dir * ACard.origW;
        int spriteY = (dist - 2) * ACard.origH;
        this.region = new TextureRegion(this.texture, spriteX, spriteY, ACard.origW, ACard.origH);
    }
    public Direction getDir(){return this.dir;}
    public int getDist(){return this.dist;}

    public void display(Batch batch) {
        if(Absolute_location.x == 0) {
            float each = Constants.WIDTH / 4;
            float offset = (float)((each - ACard.w)/2);
            Absolute_location.x = pos*(each + offset);
        }
        batch.draw(this.region, this.Absolute_location.x, this.Absolute_location.y, (float)ACard.w, (float)ACard.h);
    }

    public void drawIfSelected(){
        if(this.selected){
            Gdx.gl.glLineWidth(6);
            s.setColor(1,0,1,1);

            s.begin(ShapeRenderer.ShapeType.Line);
            s.rect(Absolute_location.x+1, Absolute_location.y, (float)ACard.w-2, (float)ACard.h-2);
            s.end();
        }
    }

}
