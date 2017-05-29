package com.rockets.gamescreen.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.world.PhysicalEntity;
import com.rockets.graphics.Nested;
import com.rockets.graphics.NestedSprite;

/**
 * name: Player
 * desc:
 * date: 2017-05-21
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Player extends PhysicalEntity implements IPlayer{
    private static final float STRENGTH = 0.5f;
    private static final float ROTATION_STRENGTH = 30;
    private static final int MAX_ROT_VEL = 640;
    private float rotationVel;
    private int rotationDire = 1;
    private boolean fingerDown;
    Actor touchPad;

    public static final String STATE_FALLING = "falling";
    public static final String STATE_THRUSTING = "thrusting";
    Nested sprite;
    public Player(IGame game) {
        super(game);

        init();
        touchPad = new Actor();
        touchPad.setSize(Display.WORLD_WIDTH,Display.WORLD_HEIGHT);
        game.world().background().addActorAt(0,touchPad);
        touchPad.addListener(clickListener);
    }
    @Override
    protected void init(){
        collisionGroup = CollisionGroup.player;
        sprite = new NestedSprite(game.gameAssets().animals.get("bird").down.first());
        addSprite(sprite);
        setSizeTo(sprite);
        setFriction(10);
        setBounciness(0.5f);
        setOrigin(Align.center);
    }


    private final EventListener clickListener = new ClickListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if(y < Display.HEIGHT-Display.TOPBAR_HEIGHT){
                fingerDown = true;
                setState(STATE_THRUSTING);
                return true;
            }
            return super.touchDown(event,x,y,pointer,button);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            fingerDown = false;
            setState(STATE_FALLING);
            rotationDire *= -1;
        }
    };



    @Override
    public void onCollision(int side) {
        super.onCollision(side);
        if(vel.len() >7){
            game.world().shakeScreen(3);
        }
    }

    @Override
    public void act(float delta) {
        if(isAsleep()){
            return;
        }
        if(fingerDown){
            getVel().add(new Vector2(0,STRENGTH).setAngle(getAngle()));
            rotationVel = 0;
        }else {
            rotationVel += rotationDire * ROTATION_STRENGTH;
            rotationVel = MathUtils.clamp(rotationVel, -MAX_ROT_VEL, MAX_ROT_VEL);
            rotateBy(delta * rotationVel);
        }
        super.act(delta);
    }

    @Override
    protected void calcGravity(float delta) {
        if(isState(STATE_FALLING)) {
            super.calcGravity(delta);
        }
    }

    @Override
    public void dispose() {
        game.stage().removeListener(clickListener);
        super.dispose();
    }

    @Override
    public boolean remove() {
        touchPad.remove();
        return super.remove();
    }

    @Override
    public void onHit(Collidable gameEntity, int side) {

    }
    public void getKilled(){
        setState(STATE_DEAD);
    }
}
