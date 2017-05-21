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
public abstract class GameEntity extends SpriteActor {
    public IGame game;
    private String state;

    public GameEntity(IGame game) {
        super();
        this.game = game;
    }

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
        dispose();
        return super.remove();
    }
    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class: ").append(getClass().toString()).append("/n");
        sb.append("eid: ").append(getName()).append("/n");
        sb.append("pos: x: ").append(getX()).append(" y: ").append(getY()).append("/n");
        return sb.toString();
    }
}
