package com.rockets.gamescripts;

import com.rockets.gamescreen.world.StateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * name: BaseSceneScript
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class BaseSceneScript implements SceneScript,StateListener {
    protected SceneDirector dir;
    private String gameState;

    public static final String STATE_BACKGROUND = "background";
    public static final String STATE_READY = "ready";
    public static final String STATE_RUNNING = "running";
    public static final String STATE_PAUSED = "paused";
    public static final String STATE_END = "end";

    private String cacheGameState;
    private List<StateListener> stateListeners;


    @Override
    public void create(SceneDirector director) {
        this.dir = director;
        init();
    }

    protected void init(){
        stateListeners = new ArrayList<>();
    }

    @Override
    public void fresh() {
        setState(STATE_READY);

        addStateListener(this);
    }


    @Override
    public String getState() {
        return gameState;
    }

    @Override
    public void setState(String state){
        String oldstate = this.gameState;
        this.gameState = state;
        for(StateListener l : stateListeners){
            l.onStateChanged(oldstate,state);
        }
    }

    @Override
    public boolean isState(String state) {
        return gameState.equals(state);
    }

    @Override
    public void dispose() {
        this.stateListeners.clear();
        this.stateListeners = null;
    }

    @Override
    public void addStateListener(StateListener listener) {
        stateListeners.add(listener);
    }

    @Override
    public void setPaused(boolean paused) {
        if(paused){
            if(isState(STATE_PAUSED)) return;
            cacheGameState = gameState;
            setState(STATE_PAUSED);
        }else{
            if(cacheGameState != null) {
                setState(cacheGameState);
                cacheGameState = null;
            }
        }
    }

    @Override
    public boolean isPauseable() {
        return isState(STATE_RUNNING) || isState(STATE_READY)|| isState(STATE_END);
    }
}
