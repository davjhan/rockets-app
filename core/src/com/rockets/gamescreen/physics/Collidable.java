package com.rockets.gamescreen.physics;

import com.rockets.gamescreen.world.GameEntity;

/**
 * name: Collidable
 * desc:
 * date: 2016-12-28
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Collidable {
    CollisionGroup getCollisionGroup();
    void onHit(Collidable gameEntity);
    public float getX();
    public float getY();
    public float getWidth();
    public float getHeight();
    public float getRadius();
    public GameEntity getGameEntity();
}
