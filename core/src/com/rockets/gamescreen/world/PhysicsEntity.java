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
    public Vector2 getVel();
    public Vector2 getDelta();
    public void setDelta(Vector2 delta);
    public void addVelX(float velX);
    public void addVelY(float velY);
}
