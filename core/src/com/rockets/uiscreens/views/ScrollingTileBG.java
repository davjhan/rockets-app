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
        int numRows = 3 + Display.HEIGHT / tileSize;
        int numCols = 2 + Display.WIDTH / tileSize;
        int graphicNum = 0;
        int lastRowGraphicNum = 0;
        start = -tileSize;
        this.speed = speed;
        tiles = new Sprite[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            graphicNum = lastRowGraphicNum+1;
            graphicNum %= 2;
            for (int c = 0; c < numCols; c++) {
                Sprite sprite = new Sprite(graphics[graphicNum]);
                sprite.setPosition(start+c*tileSize,start+r*tileSize);
                tiles[r][c] = sprite;
                graphicNum ++;
                graphicNum %= 2;
            }
            lastRowGraphicNum = graphicNum;
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


                if(tile.getX()> Display.WIDTH){
                    tile.setX(tile.getX()-totalWidth);
                }
                if(tile.getY()> Display.HEIGHT){
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

