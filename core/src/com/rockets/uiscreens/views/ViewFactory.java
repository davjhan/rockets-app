package com.rockets.uiscreens.views;

import com.rockets.assets.Icons;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.OnClickListener;

/**
 * name: ViewFactory
 * desc:
 * date: 2017-06-18
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ViewFactory {
    public static final HanButton getSettingsButton(final IGame game){
        return HanButton.with(game.iApp())
                .text(game.iApp().getString("settings"))
                .leftIcon(game.iApp().menuAssets().icons[Icons.SETTINGS])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        game.world().showSettingsModal();
                    }
                })
                .build();
    }
    public static final HanButton getSettingsButtonSmall(IApp app,OnClickListener onClickListener){
        return HanButton.with(app)
                .leftIcon(app.menuAssets().icons[Icons.SETTINGS])
                .onClick(onClickListener)
                .build();
    }
    public static final HanButton getBackButton(IApp app,OnClickListener onClickListener){
        return HanButton.with(app)
                .leftIcon(app.menuAssets().icons[Icons.BACK])
                .pad(Spacing.REG)
                .onClick(onClickListener)
                .build();
    }
    public static final HanButton getQuitButton(final IGame game,OnClickListener onClick){
        return HanButton.with(game.iApp())
                .text("QUIT")
                .leftIcon(game.iApp().menuAssets().icons[Icons.BACK])
                .onClick(onClick).build();
    }
}
