package com.rockets.gamescreen.particles.base;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.world.GameGroup;

import java.util.Random;

/**
 * name: RadialParticleEmitter
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class RadialParticleEmitter extends ParticleEmitter {
    private float strengthBase = 100;
    private float strengthVariance = 0;

    private float durationBase = 0;
    private float durationVariance = 0;
    private float angleVariance = 0;
    private float count = 4;
    private float dzBase = 0;
    private float dzVariance = 0;
    private float gravity = 0;

    private float friction = 1;

    public RadialParticleEmitter(int count) {
        this.count = count;
    }

    public RadialParticleEmitter setDurationVariance(float durationVariance) {
        this.durationVariance = durationVariance;
        return this;
    }

    public RadialParticleEmitter setStrengthVariance(float strengthVariance) {
        this.strengthVariance = strengthVariance;
        return this;
    }

    public RadialParticleEmitter setDurationBase(float baseDuration) {
        this.durationBase = baseDuration;
        return this;
    }

    public RadialParticleEmitter setStrengthBase(float baseStrength) {
        this.strengthBase = baseStrength;
        return this;
    }

    public RadialParticleEmitter setAngleVariance(float angleVariance) {
        this.angleVariance = angleVariance;
        return this;
    }

    public RadialParticleEmitter setDzVariance(float dzVariance) {
        this.dzVariance = dzVariance;
        return this;
    }

    public RadialParticleEmitter setDzBase(float dzBase) {
        this.dzBase = dzBase;
        return this;
    }

    public RadialParticleEmitter setGravity(float gravity) {
        this.gravity = gravity;
        return this;
    }

    public RadialParticleEmitter setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public RadialParticleEmitter setCount(float count) {
        this.count = count;
        return this;
    }

    public abstract PhysicsParticle getParticle();

    public void emit(GameGroup<Actor> gameGroup, float centerX, float centerY) {
        Vector2 dire = new Vector2(1, 0);
        float angle = 360 / count;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            dire.setAngle(i * angle + 90 + (random.nextFloat() - 0.5f) * angleVariance);
            dire.setLength(strengthBase + random.nextFloat() * strengthVariance);
            PhysicsParticle p = getParticle();
            p.initPhysics(
                    centerX,
                    centerY,
                    0,
                    dzBase + dzVariance * random.nextFloat(),
                    durationBase + durationVariance * random.nextFloat(),
                    dire,
                    friction

            );

            gameGroup.spawn(p, centerX, centerY, Align.center);
            p.setParticleListener(particleListener);
            particles.add(p);
        }
    }
}
