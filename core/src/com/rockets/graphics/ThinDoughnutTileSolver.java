package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * name: ThinDoughnutTileSolver
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ThinDoughnutTileSolver extends TileSolver{
    private static final int TOP = 0;
    private static final int MID = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 0;
    private static final int RIGHT = 2;
    @Override
    TextureRegion getTextureRegion(int r, int c) {
        int row = 0,col = 0;
        if(!exists(r-1,c) && exists(r+1,c)){
           row = TOP;
        }else if(!exists(r+1,c) && exists(r-1,c)){
            row = BOTTOM;
        }else{
            row = MID;
        }

        if(!exists(r,c-1) && exists(r,c+1)){
            col = LEFT;
        }else if(!exists(r,c+1) && exists(r,c-1)){
            col = RIGHT;
        }else{
            col = MID;
        }
        return regions[row][col];
    }
}
