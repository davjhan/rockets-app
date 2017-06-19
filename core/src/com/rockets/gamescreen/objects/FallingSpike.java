package com.rockets.gamescreen.objects;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.world.GameEntity;
import com.rockets.gamescreen.world.PhysicalEntity;
import com.rockets.graphics.NestedSingleSpriteActor;
import com.rockets.utils.WhiteBlinkUtils;

/**
 * name: Coin
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class FallingSpike extends PhysicalEntity implements Collidable {
    public static final String STATE_STILL = "still";
    public static final String STATE_SHAKING = "shaking";
    public static final String STATE_FALLING = "falling";
    private static final float STILL_DUR = 0.5f;
    private static final float SHAKE_DUR = 1.5f;
    private NestedSingleSpriteActor graphic;
    private float timer = 0;

    public FallingSpike(IGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        graphic = new NestedSingleSpriteActor(game.gameAssets().specialObjects[4]);
        addSprite(graphic);
        graphic.setPosition(-8,-8);
        setSize(34,34);
        setOrigin(Align.center);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        graphic.act(delta);
        if(isState(STATE_READY)){
            setState(STATE_STILL);
        }else if (isState(STATE_STILL)) {
            if (timer < 0) {
                setState(STATE_SHAKING);
                timer = SHAKE_DUR;
            } else {
                timer -= delta;
            }
        } else if (isState(STATE_SHAKING)) {
            if (timer < 0) {
                setState(STATE_FALLING);
            } else {
                timer -= delta;
            }
        }
        if (getTop() < 0) {
            remove();
            setState(STATE_DEAD);
        }
    }

    @Override
    public void fresh() {
        super.fresh();
        setState(STATE_READY);
        graphic.clearActions();
        timer = STILL_DUR;
    }

    @Override
    public CollisionGroup getCollisionGroup() {
        return CollisionGroup.touchable;
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {
        if (gameEntity.getCollisionGroup().equals(CollisionGroup.player)) {
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

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void calcGravity(float delta) {
        if (isState(STATE_FALLING)) {
            super.calcGravity(delta);
        }
    }

    @Override
    public void setState(String state) {
        super.setState(state);
        if (state.equals(STATE_READY)) {
            setVisible(false);
        }else if (state.equals(STATE_STILL)) {
            setVisible(true);
            graphic.addAction(WhiteBlinkUtils.getWhiteShortBlinkAction(game.gameAssets(),graphic.getSprite()));
            timer = STILL_DUR;
        } else if (state.equals(STATE_SHAKING)) {
            graphic.addAction(Actions.repeat(999,
                    Actions.sequence(
                            Actions.moveTo(graphic.getX()+3,graphic.getY(), 0.05f),
                            Actions.moveTo(graphic.getX()-3,graphic.getY(), 0.05f)
                    )));
        }else if (state.equals(STATE_FALLING)){

            graphic.clearActions();
            graphic.setPosition(-8,-8);
        }
    }
}
