package com.rockets.gamescreen.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * name: Side
 * desc:
 * date: 2016-12-28
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Side {
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public Vector2 p1;
    public Vector2 p2;
    public int type;

    public Side(Vector2 p1, Vector2 p2,int type) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
    }
}
