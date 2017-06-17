package com.rockets.gamescreen.objects;

import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.world.GameEntity;

/**
 * name: Coin
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Spike extends GameEntity implements Collidable {
    public static final int SIZE = 42;
    public Spike(IGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        setSprite(game.gameAssets().specialObjects[0]);
        sprites.get(0).setPosition(-4,-4);
        setSize(42,42);
        setOrigin(Align.center);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void fresh() {

    }

    @Override
    public CollisionGroup getCollisionGroup() {
        return CollisionGroup.touchable;
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {
        if(gameEntity.getCollisionGroup().equals(CollisionGroup.player)){
            Player player = (Player) gameEntity;
            player.getKilled();
        }
    }

    @Override
    public float getRadius() {
        return 75;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public GameEntity getGameEntity() {
        return this;
    }


}
