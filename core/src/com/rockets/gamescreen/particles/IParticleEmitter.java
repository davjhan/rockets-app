package com.rockets.gamescreen.particles;

import com.badlogic.gdx.utils.Disposable;

/**
 * name: ParticleEmitter
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface IParticleEmitter extends Disposable{
    public void act(float delta);
}
