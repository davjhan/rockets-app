package com.rockets.common;

import com.badlogic.gdx.utils.I18NBundle;
import com.rockets.assets.GameAssets;
import com.rockets.assets.MenuAssets;

/**
 * name: IGame
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IAppInitializer {
    void setMenuAssets(MenuAssets assets);
    void setGameAssets(GameAssets assets);
    void setStrings(I18NBundle bundleText);
}
