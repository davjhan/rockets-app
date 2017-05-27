package com.rockets.gamescripts;

import com.rockets.gamescreen.world.StateListener;
import com.rockets.gamescreen.world.Stateable;

import java.util.ArrayList;
import java.util.List;

/**
 * name: BaseSceneScript
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class BaseSceneScript implements SceneScript,Stateable,StateListener {
    protected SceneDirector dir;
    private String gameState;

    public static final String STATE_BACKGROUND = "background";
    public static final String STATE_READY = "ready";
    public static final String STATE_RUNNING = "running";
    public static final String STATE_PAUSED = "paused";
    public static final String STATE_END = "end";
    private List<StateListener> stateListeners;


    @Override
    public void create(SceneDirector director) {
        this.dir = director;
        init();
    }

    protected abstract void init();

    @Override
    public void fresh() {
        stateListeners  = new ArrayList<>();
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
}
