package com.rockets.gamescreen.particles;

import com.badlogic.gdx.Gdx;
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
public class PhysicsParticle extends SingleSpriteActor implements IParticle {
    private Action dyingAction;
    private ParticleListener listener;
    private boolean destroyOnAnimEnd = false;
    private boolean isAnimated = false;
    private boolean rotting = false;
    private boolean dead = false;


    private float z = 0;
    private float dz = 0;
    private float gravity = 0;
    private float duration = 0;
    private Vector2 vel = new Vector2();
    private float friction = 1;

    public PhysicsParticle() {
    }

    public PhysicsParticle initTexture(boolean destroyOnAnimEnd, NestedAnimatedSprite nested) {
        setSprite(nested);
        isAnimated = true;
        nested.getAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        this.destroyOnAnimEnd = destroyOnAnimEnd;
        return this;
    }

    public PhysicsParticle initTexture(Nested nested) {
        setSprite(nested);
        return this;
    }

    public PhysicsParticle setDyingAction(Action dyingAction) {
        this.dyingAction = dyingAction;
        return this;
    }


    public void initPhysics(float beginX, float beginY, float z, float dz, float duration, Vector2 vel, float friction) {
        this.z = z;
        this.dz = dz;
        setPosition(beginX, beginY);
        this.duration = duration;
        this.vel.set(vel);
        this.friction = friction;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (dead) return;
        if (rotting) {
            duration -= delta;
            if (duration <= 0) {
                duration = 0;
                die();
            }
            return;
        }
        if (destroyOnAnimEnd && isAnimated) {
            NestedAnimatedSprite animatedSprite = (NestedAnimatedSprite) sprite;
            if (animatedSprite.isAnimationFinished()) {
                rotting = true;
            }
        }
        if (vel.isZero() && dz == 0) {
            rotting = true;
            return;
        }

        moveBy(vel.x * delta, vel.y * delta);
        vel.setLength(Math.max(0, vel.len() - friction * delta));

        z += dz * delta;
        dz += gravity * delta;
        z = Math.max(0, z);
    }

    public void die() {

        dead = true;
        if (dyingAction != null) {
            addAction(dyingAction);
            addAction(Actions.after(Actions.run(new Runnable() {
                @Override
                public void run() {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            remove();
                        }
                    });
                }
            })));
        } else {
            remove();
        }
    }

    @Override
    public void dispose() {
        if (listener != null) {
            listener.onDestroyed(this);
        }
        listener = null;
        Pools.free(this);
    }

    @Override
    public void reset() {
        clearActions();
        listener = null;
        isAnimated = false;
        destroyOnAnimEnd = false;
        sprite = null;
        rotting = false;
        dead = false;
        z = 0;
        dz = 0;
        gravity = 0;
        duration = 0;
        vel.setZero();
        friction = 1;
        dyingAction = null;
    }

    @Override
    public float getDrawingY() {
        return getY() + z;
    }

    @Override
    public void setParticleListener(ParticleListener listener) {
        this.listener = listener;
    }

}
