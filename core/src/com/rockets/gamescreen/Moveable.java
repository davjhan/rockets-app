package com.rockets.gamescreen;

import com.badlogic.gdx.math.Vector2;

/**
 * name: Moveable
 * desc:
 * date: 2016-12-28
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Moveable {
    void tryMoveBy(float x, float y);
    void tryMoveBy(Vector2 delta);
}
