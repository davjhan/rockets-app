package com.rockets.gamescreen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.physics.Side;
import com.rockets.gamescreen.world.GameEntity;

/**
 * name: Coin
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class WallBlock extends GameEntity implements Collidable {
    public static final int SIZE = 50;
    public WallBlock(IGame game) {
        super(game);
        init(null);
    }

    @Override
    protected void init() {

    }

    public WallBlock(IGame game, TextureRegion texture) {
        super(game);
        init(texture);
    }

    protected void init(TextureRegion texture) {
        if(texture != null){
            setSprite(texture);
        }else {
            setSprite(game.gameAssets().specialObjects[2]);
        }
        setSize(SIZE,SIZE);
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
        return CollisionGroup.wall;
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {
        if(gameEntity.getCollisionGroup().equals(CollisionGroup.player)){
            Player player = (Player) gameEntity;
            Gdx.app.log("tttt WallBlock", "side: " +side);
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
