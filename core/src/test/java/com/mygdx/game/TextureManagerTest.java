package com.mygdx.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by davebernier on 17-12-10.
 */
public class TextureManagerTest extends GameTest{
    @Test
    public void getInstance() throws Exception {
        assertEquals(TextureManager.getInstance(), TextureManager.getInstance());
    }

    @Test
    public void getCoinTexture() throws Exception {
        assertNotNull(TextureManager.getInstance().getCoinTexture());
    }

    @Test
    public void getTileTexture() throws Exception {
        assertNotNull(TextureManager.getInstance().getTileTexture());
    }

    @Test
    public void getCardsTexture() throws Exception {
        assertNotNull(TextureManager.getInstance().getCardsTexture());
    }

}