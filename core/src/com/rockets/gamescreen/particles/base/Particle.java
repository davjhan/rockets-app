package com.rockets.gamescreen.particles.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pools;
import com.rockets.graphics.Nested;
import com.rockets.graphics.NestedAnimatedSprite;
import com.rockets.graphics.SingleSpriteActor;

/**
 * name: Particle
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Particle extends SingleSpriteActor implements IParticle{
   private Action dyingAction;
    private ParticleListener listener;
    private boolean destroyOnAnimEnd = false;
    private boolean isAnimated = false;
    private float dz;
    private float z;
    private Vector2 vel;
    private float friction;
    private float gravity;

    public Particle() {

    }
    public Particle initTexture(boolean destroyOnAnimEnd,NestedAnimatedSprite nested) {
        setSprite(nested);
        isAnimated = true;
        nested.getAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        this.destroyOnAnimEnd = destroyOnAnimEnd;
        return this;
    }
    public Particle initTexture(Nested nested) {
        setSprite(nested);
        return this;
    }

    public Particle setDyingAction(Action dyingAction) {
        this.dyingAction = dyingAction;
        return this;
    }

    public Particle initAction(Action livingAction) {

        clearActions();
        addAction(livingAction);
        addAction(Actions.after(Actions.run(new Runnable() {
            @Override
            public void run() {
                if (dyingAction == null) {
                    remove();
                } else {
                    addAction(dyingAction);
                    addAction(Actions.after(Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            remove();
                        }
                    })));
                }
            }
        })));
        return this;
    }

    @Override
    public void act(float delta) {
        if(destroyOnAnimEnd && isAnimated){
            NestedAnimatedSprite animatedSprite = (NestedAnimatedSprite) sprite;
            if(animatedSprite.isAnimationFinished()){
                remove();
            }
        }
        if(vel != null) {
            moveBy(vel.x, vel.y);
            vel.setLength(Math.max(0, vel.len() - friction * delta));

            z += dz * delta;
            dz += gravity * delta;
            z = Math.max(0, z);
        }
        super.act(delta);
    }

    public void setListener(ParticleListener listener) {
        this.listener = listener;
    }

    @Override
    public void dispose() {
        if(listener != null) {
            listener.onDestroyed(this);
        }
        listener = null;
        Pools.free(this);
    }

    @Override
    public void reset() {
        clearActions();
        listener = null;
        isAnimated = true;
        destroyOnAnimEnd = false;
        gravity = 0;
        z = 0;
        dz = 0;
    }

    @Override
    public float getDrawingY() {
        return getY()+z;
    }

    public void initZ(float gravity, float z) {
        this.gravity = gravity;
        this.dz =  z;
    }

    @Override
    public void setParticleListener(ParticleListener listener) {
        this.listener = listener;
    }
}
