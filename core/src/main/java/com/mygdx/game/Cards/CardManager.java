package com.mygdx.game.Cards;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by berni on 2017-11-23.
 */

public class CardManager {

    public CardManager(){}

    static public DirectionCard pickRandomCard(int pos){
        int dist = MathUtils.random(2,5);
        int dir = MathUtils.random(3);

        return new DirectionCard(pos, dist, dir);
    }
}
