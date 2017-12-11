package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Cards.*;


import java.lang.*;
import java.util.ArrayList;

public class MemoDeck extends ApplicationAdapter {
    private SpriteBatch batch;
    private Board board;
    private Character perso;
    private ArrayList<DirectionCard> Cards;
    private Button confirmButton;
    private Button resetButton;
    private ArrayList<DirectionCard> Selected;
    private ArrayList<Coin> Coins = new ArrayList<Coin>();
    private boolean isOver = false;
    private int curRound;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                handleClick(screenX, screenY);
                return true;
            }
        });
        batch = new SpriteBatch();
        board = new Board();
        perso = new Character(0.5f, 0.5f);
        Cards = new ArrayList<DirectionCard>();
        Selected = new ArrayList<DirectionCard>();
        float w = Board.width * Constants.TILE_W_DRAW;
        confirmButton = new Button(0, (float)ACard.h, "Confirm");
        resetButton = new Button(0, Constants.HEIGHT/2, "Reset");
        curRound = Config.MAX_ROUND - Config.ROUNDS_LEFT;

        init();
    }

    private void init(){

        Cards.clear();
        Selected.clear();
        Coins.clear();
        isOver = false;
        Config.POINTS = 0;
        Config.ROUNDS_LEFT = Config.MAX_ROUND;
        curRound = 0;
        perso.reset();

        CreateCoins(Config.NB_COINS);
        PickCards();
    }

    private void update() {

            confirmButton.setEnable(Selected.size() - 2 * curRound == 2);
            perso.update(Coins);
            for(Coin c : Coins){
                c.update();
            }
    }

    @Override
    public void render(){

            update();

            drawBackground();
            batch.begin();
            drawBoard(batch);
            for(Coin c : Coins){
                c.display(batch);
            }
            batch.end();
            //draw cards
            for (int i = 0; i < Cards.size(); i++) {
                Cards.get(i).drawIfSelected();
            }
            perso.display(batch);
            confirmButton.display(batch);
        drawStats();
        if(isOver){
            drawEnd();
        }

        isOver = Config.ROUNDS_LEFT == 0 && Selected.size() == 0 && perso.isAtDestination();

    }

    private void drawBackground(){
        ADrawable.s.begin(ShapeRenderer.ShapeType.Filled);
        ADrawable.s.setColor(0,0,0,1);
        ADrawable.s.rect(0,0,Constants.WIDTH, Constants.HEIGHT);
        ADrawable.s.end();
    }

    private void drawBoard(SpriteBatch batch){
        board.display(batch);
        for(int i=0; i<Cards.size(); i++){
            Cards.get(i).display(batch);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        TextureManager.getInstance().dispose();

    }

    private void PickCards(){
        Cards.clear();
        for(int i = 0; i < 4; i++){
            Cards.add(CardManager.pickRandomCard(i));
        }
    }

    private void handleClick(int x, int y){
       y = Constants.HEIGHT - y;

        if(Config.ROUNDS_LEFT == 0){
            if(resetButton.handleClick(x, y)){
                init();
            }
            return;
        }


        curRound = Config.MAX_ROUND - Config.ROUNDS_LEFT;
        //Bouton
        if(confirmButton.isEnabled() && confirmButton.handleClick(x,y)){
            confirmRound();
            return;
        }
        //Cartes
        for(int i = 0; i < Cards.size(); i++){
            DirectionCard current = Cards.get(i);

            if(current.handleClick(x, y, Selected.size() - 2*curRound)){
                if(current.isSelected()){
                    Selected.add(current);
                } else {
                    Selected.remove(current);
                }
                break;
            }
        }
    }

    private void confirmRound(){

        if(Config.ROUNDS_LEFT == 1)
            perso.applyCard(Selected);
        else {
            PickCards();
        }
        Config.ROUNDS_LEFT--;
        curRound = Config.MAX_ROUND - Config.ROUNDS_LEFT;
    }

    private void drawEnd(){
        String text = Config.POINTS >= Config.GOAL ? "Congratulations!" : "Boohooooooo";
        ShapeRenderer s = ADrawable.s;
        BitmapFont txt = ADrawable.txt;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(0.1f,0f,0f,0.6f);
        s.rect(0f, 0f, Constants.WIDTH, Constants.HEIGHT);
        ADrawable.s.end();

        resetButton.setEnable(true);
        resetButton.display(batch);
        batch.begin();
        txt.setColor(1,1,1,1);
        txt.getData().scale(4f);
        txt.draw(batch, text, 0, Constants.HEIGHT/4*3, (float)Constants.WIDTH, Align.center, true);
        batch.end();
    }

    private void CreateCoins(int nbCoins){
        float x = 0, y = 0;
        for(int i = 0; i < nbCoins; i++){

            do{
               x = (float)Math.floor(Math.random() * Board.width) +0.5f;
               y = (float)Math.floor(Math.random() * Board.width) +0.5f;
            }while(!tileIsEmpty(x,y));

            Coins.add(new Coin(x, y));
        }
    }

    private boolean tileIsEmpty(float x, float y){
        boolean ok = true;

        Vector2 loc = perso.getGridLocation();
        float p_x = (float)Math.floor(loc.x),
        p_y = (float)Math.floor(loc.y);
        if (p_x + 0.5f == x && p_y + 0.5f == y) {
            ok = false;
        }

        for(Coin c : Coins) {
            if (c.Grid_location.x == x && c.Grid_location.y == y) {
                ok = false;
            }
        }

        return ok;
    }

    private void drawStats(){
        BitmapFont txt = ADrawable.txt;
        txt.getData().setScale(2f);
        txt.getData().setLineHeight(14);
        txt.setColor(1,1,1,1);
        batch.begin();
        txt.draw(batch, "Rounds left: " + Config.ROUNDS_LEFT, 20, Constants.HEIGHT-20, (float)Constants.WIDTH/2, Align.left, true);
        txt.draw(batch, "Goal: " + Config.GOAL, Constants.WIDTH/2, Constants.HEIGHT-20, (float)Constants.WIDTH/2, Align.right, true);
        txt.draw(batch, "Points: " + Config.POINTS, Constants.WIDTH/2, Constants.HEIGHT-50, (float)Constants.WIDTH/2, Align.right, true);
        batch.end();
    }

}
