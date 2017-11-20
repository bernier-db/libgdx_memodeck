package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Singleton qui possède toutes les textures
 */
public final class TextureManager {
    private final static TextureManager instance = new TextureManager();
    private Texture coin, tile, cards;
    private TextureManager(){
        coin = new Texture("coin_sprite.png");
        tile = new Texture("tiles.png");
        cards = new Texture("cards");
    }

    /**
     * Retourne l'instance
     *
     */
    public static TextureManager getInstance(){
        return instance;
    }

    public Texture getCoinTexture(){return coin;}
    public Texture getTileTexture(){return tile;}
    public Texture getCardsTexture(){return cards;}

}
