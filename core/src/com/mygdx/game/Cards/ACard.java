package com.mygdx.game.Cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.ADrawable;
import com.mygdx.game.Constants;
import com.mygdx.game.TextureManager;

/**
 * General drawable object class
 */

public abstract class ACard extends ADrawable {

    public static double h = Constants.HEIGHT /6 ;
    public static double w = h *81/107;

    public static int origH = 107;
    public static int origW = 81;

    protected TextureRegion region;
    protected boolean selected;


    public ACard(float x, float y, CoordType coordType) {

        super(TextureManager.getInstance().getCardsTexture(), x, y, coordType);
        selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public abstract void display(Batch batch);

    public boolean handleClick(int x, int y, int nbSelected) {
        if(Constants.isClicked(Absolute_location.x, Absolute_location.y, (float)ACard.w, (float)ACard.h, x, y)) {

            this.selected = this.selected ? false :
                            !this.selected && nbSelected < 2 ? true :
                            this.selected;
            return true;
        }
        return false;
    }
}
