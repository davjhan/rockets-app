package com.rockets.gamescreen.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.particles.base.AnimatedParticle;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.world.GameEntity;
import com.rockets.utils.WhiteBlinkUtils;

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
        setSize(34,34);
        sprites.get(0).setPosition(-4,-4);
        setOrigin(Align.center);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void fresh() {
        clearActions();
    }

    @Override
    public CollisionGroup getCollisionGroup() {
        return CollisionGroup.touchable;
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {
        if(gameEntity.getCollisionGroup().equals(CollisionGroup.player)){
            Player player = (Player) gameEntity;
            player.getVel().y = Math.max(player.getVel().y,0);
            player.getVel().add(0,1);
            die();
            collect();
        }
    }

    private void die() {
        remove();
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

    @Override
    protected void onShow() {
        super.onShow();
        addAction(WhiteBlinkUtils.getWhiteShortBlinkAction(game.gameAssets(),(Sprite)sprites.get(0)));
    }

}
