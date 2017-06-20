package com.rockets.gamescreen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.AnimConst;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.world.PhysicalEntity;
import com.rockets.graphics.NestedAnimatedSprite;
import com.rockets.graphics.NestedSprite;

/**
 * name: Player
 * desc:
 * date: 2017-05-21
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Player extends PhysicalEntity implements IPlayer{
    private static final float STRENGTH = 0.55f;
    private static final float ROTATION_STRENGTH = 30;
    private static final int MAX_ROT_VEL = 540;
    private float rotationVel;
    private int rotationDire = -1;
    private boolean fingerDown;
    NestedSprite upArrow;
    Actor touchPad;
    NestedAnimatedSprite falling;
    NestedAnimatedSprite thrusting;

    public static final String STATE_FALLING = "falling";
    public static final String STATE_THRUSTING = "thrusting";
    public Player(IGame game) {
        super(game);

        init();
        touchPad = new Actor();
        touchPad.setSize(Display.CONTENT_WIDTH,Display.CONTENT_HEIGHT);
        game.world().background().addActorAt(game.world().background().getChildren().size,touchPad);
        touchPad.addListener(clickListener);
    }
    @Override
    protected void init(){
        collisionGroup = CollisionGroup.player;
        falling = new NestedAnimatedSprite(new Animation<>(AnimConst.MEDIUM,
                game.gameAssets().animals.get("bird").get(0), Animation.PlayMode.NORMAL));
        thrusting = new NestedAnimatedSprite(new Animation<>(AnimConst.MEDIUM,
                game.gameAssets().animals.get("bird").get(1), Animation.PlayMode.NORMAL));
        autoResize = false;
        setSprite(falling);
        setSize(34,34);
        falling.setPosition(-8,-8);
        thrusting.setPosition(-8,-8);
        setFriction(15f);
        setBounciness(0.4f);
        setOrigin(Align.center);

      //  upArrow = new NestedSprite(game.gameAssets().upArrow);
        //addSprite(upArrow);
       // upArrow.setPosition(12,40);
    }


    private final EventListener clickListener = new ClickListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if(y < Display.CONTENT_HEIGHT){
                if(!fingerDown){
                    thrusting.setTime(0);
                    setSprite(0,thrusting);
                    setState(STATE_THRUSTING);
                    fingerDown = true;
                }
                return true;
            }
            return super.touchDown(event,x,y,pointer,button);
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            if(fingerDown){
                rotationDire *= -1;
                falling.setTime(0);

                falling.flipFrames(true,false);
                thrusting.flipFrames(true,false);
                setSprite(0,falling);
                setState(STATE_FALLING);
                fingerDown = false;
                Gdx.app.log("tttt Player", "rot: " +falling.isFlipY());
            }
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
        Gdx.app.log("tttt Player", "vel: " +getVel());
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
