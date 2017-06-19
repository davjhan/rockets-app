package com.rockets.gamescreen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.physics.Side;
import com.rockets.gamescreen.world.GameEntity;
import com.rockets.graphics.ActionFactory;
import com.rockets.graphics.NestedSingleSpriteActor;
import com.rockets.utils.WhiteBlinkUtils;

/**
 * name: Coin
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class WoodenBox extends GameEntity implements Collidable {
    public static final int SIZE = 50;
    private OnBrokenListener listener;
    private NestedSingleSpriteActor graphic;
    public WoodenBox(IGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        graphic = new NestedSingleSpriteActor(game.gameAssets().specialObjects[1]);
        addSprite(graphic);
        setOrigin(Align.center);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        graphic.act(delta);
    }

    @Override
    public void fresh() {
        graphic.clearActions();
    }

    @Override
    public CollisionGroup getCollisionGroup() {
        return CollisionGroup.wall;
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {
        if(gameEntity.getCollisionGroup().equals(CollisionGroup.player)){
            Player player = (Player) gameEntity;
            Side.applyBounce(player.getVel(),side,1,5);
            if(player.isState(Player.STATE_THRUSTING)){
                die();
                if(listener != null)listener.onBroken();
            }else{
                graphic.addAction(ActionFactory.shake(graphic,4));
            }
            Gdx.app.log("tttt WoodenBox", "side: " +side);
        }
    }

    private void die() {
        remove();
    }

    @Override
    public float getRadius() {
        return 75;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.listener = null;
    }
    public void setBrokenListener(OnBrokenListener listener){
        this.listener = listener;
    }
    @Override
    public GameEntity getGameEntity() {
        return this;
    }

    public static interface OnBrokenListener{
        void onBroken();
    }

    @Override
    protected void onShow() {
        super.onShow();
        graphic.addAction(WhiteBlinkUtils.getWhiteShortBlinkAction(game.gameAssets(),graphic.getSprite()));
    }
}
