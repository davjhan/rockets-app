package com.rockets.gamescripts;

import com.rockets.common.IApp;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.world.IGameWorld;

/**
 * name: SceneDirector
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface SceneDirector {
    void finish();
    IGameWorld gameWorld();
    IGame game();

    IApp app();
}
