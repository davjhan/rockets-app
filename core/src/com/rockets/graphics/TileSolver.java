package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * name: TileSolver
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class TileSolver {
    protected TextureRegion[][] regions;
    protected boolean[][] map;

    public void init(TextureRegion[][] regions, boolean[][]map){

        this.regions = regions;
        this.map = map;
    }
    abstract TextureRegion getTextureRegion(int r, int c);

    protected boolean exists(int r,int c) {
        return (r > 0 && c > 0 && r < map.length && c < map[0].length) && map[r][c];
    }
}
