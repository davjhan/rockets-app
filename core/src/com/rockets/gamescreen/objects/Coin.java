package com.rockets.gamescreen.objects;

import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.particles.AnimatedParticle;
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
public class Coin extends GameEntity implements Collidable {
    CoinListener coinListener;
    public Coin(IGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        setSprite(game.gameAssets().coin[0]);
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
    public void onHit(Collidable gameEntity) {
        if(gameEntity.getCollisionGroup().equals(CollisionGroup.player)){
            collect();
        }
    }

    private void collect() {
        AnimatedParticle sparkle = new AnimatedParticle(game.gameAssets().glisten);
        game.world().bodies().spawn(sparkle,getX(Align.center),getY(Align.center), Align.center);
        if(coinListener != null) coinListener.collected();
    }

    @Override
    public float getRadius() {
        return Display.UNIT;
    }

    @Override
    public void dispose() {
        super.dispose();
        coinListener = null;
    }

    @Override
    public GameEntity getGameEntity() {
        return this;
    }
    public interface CoinListener{
        void collected();
    }

    public void setCoinListener(CoinListener coinListener) {
        this.coinListener = coinListener;
    }
}
