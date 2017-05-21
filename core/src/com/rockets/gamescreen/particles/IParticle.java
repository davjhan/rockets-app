package com.rockets.gamescreen.particles;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;

/**
 * name: IParticle
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface IParticle extends Pool.Poolable, Disposable {
    public void setParticleListener(ParticleListener listener);

    boolean remove();
}
