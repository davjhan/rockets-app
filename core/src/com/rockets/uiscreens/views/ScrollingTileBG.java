package com.rockets.uiscreens.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rockets.constants.Display;

/**
 * name: ScrollingTileBG
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ScrollingTileBG extends Actor{
    Sprite[][] tiles;
    private float start;
    float speed;
    private int tileSize;
    private int totalWidth;
    private int totalHeight;
    public ScrollingTileBG( float speed, TextureRegion[] graphics) {
        tileSize = graphics[0].getRegionWidth();
        int numRows = (int) (3 + Display.SCREEN_HEIGHT / tileSize);
        int numCols = (int) (2 + Display.SCREEN_WIDTH / tileSize);
        numRows += numRows%2;
        numCols += numCols%2;
        int graphicNum = 0;
        start = -tileSize;
        this.speed = speed;
        tiles = new Sprite[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            graphicNum = r%2;
            for (int c = 0; c < numCols; c++) {
                Sprite sprite = new Sprite(graphics[graphicNum]);
                sprite.setPosition(start+c*tileSize,start+r*tileSize);
                tiles[r][c] = sprite;
                graphicNum ++;
                graphicNum %= 2;
            }
        }
        totalWidth = numCols*tileSize;
        totalHeight = numRows*tileSize;
    }

    @Override
    public void act(float delta) {
        Sprite tile;
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                tile = tiles[r][c];
                tile.setPosition(tile.getX()+speed,tile.getY()+speed);


                if(tile.getX()> Display.SCREEN_WIDTH){
                    tile.setX(tile.getX()-totalWidth);
                }
                if(tile.getY()> Display.SCREEN_HEIGHT){
                    tile.setY(tile.getY()-totalHeight);
                }
            }
        }

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                tiles[r][c].draw(batch,parentAlpha);
            }
        }
        super.draw(batch, parentAlpha);
    }
}

