package com.rockets.common;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rockets.assets.GameAssets;
import com.rockets.assets.MenuAssets;
import com.rockets.data.Backend;
import com.rockets.data.ContentDB;
import com.rockets.data.Saves;
import com.rockets.modal.Modal;

/**
 * name: IGame
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IApp {
    ScreenManager screenManager();
    GameAssets gameAssets();
    MenuAssets menuAssets();
    ContentDB contentDB();
    Stage modalStage();

    String getString(String key);

    void showModal(Modal optionsModal);
    Backend backend();
    Saves saves();
}
