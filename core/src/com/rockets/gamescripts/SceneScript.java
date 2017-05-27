package com.rockets.gamescripts;

import com.badlogic.gdx.utils.Disposable;
import com.rockets.gamescreen.world.Freshable;

/**
 * name: SceneScript
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface SceneScript extends Disposable,Freshable {
    void create(SceneDirector director);
    void update(float delta);
}
