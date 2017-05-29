package com.rockets.gamescreen.world;

import com.badlogic.gdx.math.Vector2;

/**
 * name: PhysicsEntity
 * desc:
 * date: 2016-12-27
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface PhysicsEntity {
    Vector2 getVel();
    Vector2 getDelta();
    void setDelta(Vector2 delta);
    void addVelX(float velX);
    void addVelY(float velY);
}
