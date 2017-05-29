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
    public static final int UNDEFINED = -1;
    public Vector2 p1;
    public Vector2 p2;
    public int type;

    public Side(Vector2 p1, Vector2 p2,int type) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
    }

    public static boolean isHorizontal(int side) {
        return side == LEFT || side == RIGHT;
    }
    public static boolean isVertical(int side) {
        return side == TOP || side == BOTTOM;
    }

    public static int opposite(int sideType) {
        return (sideType+2)%4;
    }

    /**
     * Transforms the given vector to calculate the bounce.
     */
    public static void applyBounce(Vector2 vec, int side, float bounciness,float baseBounce) {
        if(isHorizontal(side)){
            vec.x *= -bounciness;
        }
        if(isVertical(side)){
            vec.y *= -bounciness;
        }
        vec.setLength(vec.len()+baseBounce);

    }
    public static void applyBounce(Vector2 vec, int side, float bounciness) {
        applyBounce(vec,side,bounciness,0);

    }
}
