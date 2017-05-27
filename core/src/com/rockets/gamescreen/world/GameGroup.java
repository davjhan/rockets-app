package com.rockets.gamescreen.world;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.rockets.gamescreen.ActorGroup;

/**
 * name: GameGroup
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameGroup<T extends Actor>  extends Group {
    private boolean paused = false;
    public void spawn(T entity){
        addActor(entity);
    }
    public void spawn(T entity, float x, float y){
        entity.setPosition(x,y);
        addActor(entity);
    }
    public void spawn(T entity,float x, float y,int align){
        entity.setPosition(x,y,align);
        addActor(entity);
    }

    public void spawn(ActorGroup<T> actorGroup) {
        for(T a:actorGroup){
            addActor(a);
        }
    }

    public void remove(ActorGroup<T> actorGroup) {
        for(T a:actorGroup){
            removeActor(a);
        }
    }

    @Override
    public void act(float delta) {
        if(!paused) {
            super.act(delta);
        }
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
