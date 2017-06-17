package com.rockets.gamescreen.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.gamescreen.ActorGroup;

/**
 * name: GameGroup
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameGroup<T extends Actor>  extends Group implements Disposable {
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
    public void spawn(T entity, Vector2 loc, int align){
        entity.setPosition(loc.x,loc.y,align);
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
        }else{
            Array<Action> actions = getActions();
            if (actions.size > 0) {
                if (getStage() != null && getStage().getActionsRequestRendering()) Gdx.graphics.requestRendering();
                for (int i = 0; i < actions.size; i++) {
                    Action action = actions.get(i);
                    if (action.act(delta) && i < actions.size) {
                        Action current = actions.get(i);
                        int actionIndex = current == action ? i : actions.indexOf(action, true);
                        if (actionIndex != -1) {
                            actions.removeIndex(actionIndex);
                            action.setActor(null);
                            i--;
                        }
                    }
                }
            }
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    @Override
    public void dispose() {
        for(Actor t:getChildren()){
            if(t instanceof Disposable){
                ((Disposable) t).dispose();
            }
        }
    }
}
