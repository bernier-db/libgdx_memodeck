package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Cards.CardManager;
import com.mygdx.game.Cards.DirectionCard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by davebernier on 17-12-10.
 */
public class CharacterTest extends GameTest {
    private Character c;


    @Before
    public void setUp(){
        c = new Character(0,0);
    }
    @Test
    public void isMoving() throws Exception {
        assertFalse(c.isMoving());
        c.Vel.x = 2;
        assertTrue(c.isMoving());
        c.Vel.x =0;
    }

    @Test
    public void isAtDestination() throws Exception {
        assertTrue(c.isAtDestination());
        c.destination.x = 1;
        assertFalse(c.isAtDestination());
        c.destination.x =0;
    }

    @Test
    public void applyCard() throws Exception {
        ArrayList<DirectionCard> cards = new ArrayList<DirectionCard>();
        cards.add(new DirectionCard(0, 1, 0));
        cards.add(new DirectionCard(1, 1, 1));
        cards.add(new DirectionCard(1, 1, 2));
        cards.add(new DirectionCard(1, 1, 3));

        c.applyCard(cards);
        assertEquals(1,c.destination.x, 0.1);

        c.applyCard(cards);
        assertEquals(0,c.destination.x, 0.1);

        c.applyCard(cards);
        assertEquals(1,c.destination.y, 0.1);

        c.applyCard(cards);
        assertEquals(0,c.destination.y, 0.1);

        cards.add(new DirectionCard(0,1,3));
        c.applyCard(cards);
        assertEquals(0.5,c.destination.y, 0.1);

        cards.add(new DirectionCard(0,1,1));
        c.applyCard(cards);
        assertEquals(0.5,c.destination.x, 0.1);

        cards.add(new DirectionCard(0,10,2));
        c.applyCard(cards);
        assertEquals(9.5,c.destination.y, 0.1);

        cards.add(new DirectionCard(0,10,0));
        c.applyCard(cards);
        assertEquals(9.5,c.destination.x, 0.1);
    }


    @Test
    public void applyForce() throws Exception {
        c.applyForce(new Vector2(2,2));
        assertEquals(2, c.Acc.x,0);
        assertEquals(2, c.Acc.y,0);
    }

    @Test
    public void getGridLocation() throws Exception {
        assertEquals(0, c.getGridLocation().x, 0);
        assertEquals(0, c.getGridLocation().y, 0);
    }

    @Test
    public void reset() throws Exception {
        c.reset();

        assertNull(c.Cards);
        assertEquals(0.5, c.getGridLocation().x,0);
        assertEquals(0.5, c.getGridLocation().y,0);
        assertEquals(0, c.Vel.x,0);
        assertEquals(0, c.Vel.y,0);
        assertEquals(0, c.Acc.x,0);
        assertEquals(0, c.Acc.y,0);
        assertEquals(0, Config.POINTS);
    }

}