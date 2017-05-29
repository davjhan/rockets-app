package com.rockets.gamescripts;

import com.badlogic.gdx.utils.Disposable;
import com.rockets.gamescreen.world.Freshable;
import com.rockets.gamescreen.world.Stateable;

/**
 * name: SceneScript
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface SceneScript extends Disposable,Freshable,Stateable {
    void create(SceneDirector director);
    void update(float delta);
    void setState(String state);
    void setPaused(boolean paused);
    boolean isPauseable();
}
