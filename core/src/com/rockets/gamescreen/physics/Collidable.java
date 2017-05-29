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
    void onHit(Collidable gameEntity, int side);
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    float getRadius();
    GameEntity getGameEntity();
}
