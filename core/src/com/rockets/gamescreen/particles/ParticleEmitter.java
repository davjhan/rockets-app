package com.rockets.gamescreen.particles;

import java.util.ArrayList;
import java.util.List;

/**
 * name: ParticleEmitter
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ParticleEmitter implements IParticleEmitter {
    List<IParticle> particles;
    public ParticleEmitter(){
        particles = new ArrayList<>();
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void dispose() {
        for(IParticle p:particles){
            p.remove();
        }
        particles = null;
    }

    protected ParticleListener particleListener = new ParticleListener() {
        @Override
        public void onDestroyed(IParticle particle) {
            particles.remove(particle);
        }
    };
}
