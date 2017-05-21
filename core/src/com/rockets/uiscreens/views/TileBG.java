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
public class TileBG extends Actor{
    Sprite[][] tiles;
    private float start;
    private int tileSize;
    private int totalWidth;
    private int totalHeight;
    public TileBG(TextureRegion[] graphics) {
        tileSize = graphics[0].getRegionWidth();
        int numRows = 1+ Display.HEIGHT / tileSize;
        int numCols = 1+ Display.WIDTH / tileSize;
        int graphicNum = 0;
        tiles = new Sprite[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            graphicNum = r%2;
            for (int c = 0; c < numCols; c++) {
                Sprite sprite = new Sprite(graphics[graphicNum]);
                sprite.setPosition(c*tileSize,r*tileSize);
                tiles[r][c] = sprite;
                graphicNum ++;
                graphicNum %= 2;
            }
        }
        totalWidth = numCols*tileSize;
        totalHeight = numRows*tileSize;
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

