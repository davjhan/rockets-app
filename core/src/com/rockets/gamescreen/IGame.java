package com.rockets.gamescreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rockets.assets.GameAssets;
import com.rockets.assets.MenuAssets;
import com.rockets.common.IApp;
import com.rockets.gamescreen.world.GameWorld;

/**
 * name: IGame
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IGame {
    Stage stage();
    IApp iApp();
    GameWorld world();
    GameAssets gameAssets();

    MenuAssets menuAssets();

    void crash(Exception e);

    void quit();
}
