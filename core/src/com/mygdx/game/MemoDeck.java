package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Cards.ACard;
import com.mygdx.game.Cards.CardManager;
import com.mygdx.game.Cards.DirectionCard;

import java.util.ArrayList;

public class MemoDeck extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Board board;
    Character perso;
    ArrayList<DirectionCard> Cards;
    Button button;
    ArrayList<DirectionCard> Selected;
    @Override
    public void create() {
        batch = new SpriteBatch();
        board = new Board();
        perso = new Character(0.5f, 0.5f);
        Cards = new ArrayList<DirectionCard>();
        Selected = new ArrayList<DirectionCard>();
        float w = Board.width * Constants.TILE_W_DRAW;
        button = new Button(0, (float)ACard.h);

        PickCards();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                handleClick(screenX, screenY);
                return true;
            }
        });
    }

    public void update() {
        button.setEnable(Selected.size() == 2);
        perso.update();
    }

    @Override
    public void render() {

        update();

        drawBackground();
        batch.begin();
        drawBoard(batch);
        batch.end();
        //draw cards
        for(int i=0; i<Cards.size(); i++){
            ((DirectionCard)Cards.get(i)).drawIfSelected();
        }
        perso.display(batch);
        button.display(batch);
    }

    void drawBackground(){
        ADrawable.s.begin(ShapeRenderer.ShapeType.Filled);
        ADrawable.s.setColor(0,0,0,1);
        ADrawable.s.rect(0,0,Constants.WIDTH, Constants.HEIGHT);
        ADrawable.s.end();
    }

    void drawBoard(SpriteBatch batch){
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

        if(button.isEnabled() && button.handleClick(x,y)){
            confirmRound();
            return;
        }

        for(int i = 0; i < Cards.size(); i++){
            DirectionCard current = Cards.get(i);
            if(current.handleClick(x, y, Selected.size())){
                if(current.isSelected()){
                    Selected.add(current);
                } else {
                    Selected.remove(current);
                }
                break;
            }
        }
    }

    void confirmRound(){

        perso.applyCard((ArrayList<DirectionCard>)Selected);
    }

}
