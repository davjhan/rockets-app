package com.rockets.gamescreen.world;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.StringBuilder;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.SpriteActor;

/**
 * name: GameEntity
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class GameEntity extends SpriteActor implements Stateable,Freshable {
    public static final String STATE_READY = "ready";
    public IGame game;
    private String state;
    private StateListener stateListener;

    public GameEntity(IGame game) {
        super();
        this.game = game;
    }
    protected abstract void init();
    @Override
    protected void setStage(Stage stage) {
        if(stage != null){
            onShow();
        }
        super.setStage(stage);
    }

    protected void onShow() {
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
        this.stateListener = null;
    }

    public String getState() {
        return state;
    }
    public boolean isState(String state) {
        return state.equals(this.state);
    }
    public float getAngle(){
        return getRotation()+90;
    }
    public void setState(String state) {
        String oldstate = this.state;
        this.state = state;
        if(stateListener != null) {
            stateListener.onStateChanged(oldstate, state);
        }

    }

    @Override
    public void addStateListener(StateListener listener) {
        this.stateListener = listener;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class: ").append(getClass().toString()).append("/n");
        sb.append("eid: ").append(getName()).append("/n");
        sb.append("pos: x: ").append(getX()).append(" y: ").append(getY()).append("/n");
        return sb.toString();
    }

    @Override
    public void fresh() {
        this.state = STATE_READY;
        setRotation(0);
    }
}
