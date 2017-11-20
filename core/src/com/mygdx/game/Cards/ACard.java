package com.mygdx.game.Cards;

import com.mygdx.game.ADrawable;
import com.mygdx.game.TextureManager;

/**
 * General drawable object class
 */

public abstract class ACard extends ADrawable {
    public ACard(float x, float y, CoordType coordType){
        super(TextureManager.getInstance().getCardsTexture(), x, y, coordType);
    }

    public abstract void display();
}
