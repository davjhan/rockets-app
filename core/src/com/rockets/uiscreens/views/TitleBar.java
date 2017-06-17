package com.rockets.uiscreens.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanIconButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.modals.SettingsModal;

/**
 * name: TitleBar
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class TitleBar extends Table implements Disposable {
    private HanIconButton backButton;
    private HanLabel title;

    public TitleBar(IApp app, String titleText){

        background(new NinePatchDrawable(app.menuAssets().toolbarBG[0]));
        backButton = new HanIconButton(
                app,
                new TextureRegionDrawable(app.menuAssets().icons[0]),
                new NinePatchDrawable(app.menuAssets().toolbarBG[1]),
                new NinePatchDrawable(app.menuAssets().toolbarBG[2]));
        title =  HanLabel.text(titleText)
                .font(Font.h1)
                .color(Colr.TEXT_LIGHT)
                .build();

        Actor settingsButton = getSettingsButton(app);
        pad(2);
        padBottom(4);
        add(backButton).height(28).width(28).spaceRight(Spacing.XSMALL);
        add(title).left().expandX();
        add(settingsButton).height(28).width(28);

        pack();
        setWidth(Display.SCREEN_WIDTH);
    }

    public HanIconButton getBackButton() {
        return backButton;
    }

    @Override
    public void dispose() {
        backButton.clearListeners();
    }

    private Actor getSettingsButton(final IApp app) {
        HanIconButton button = new HanIconButton(
                app,
                new TextureRegionDrawable(app.menuAssets().icons[1]),
                new NinePatchDrawable(app.menuAssets().toolbarBG[1]),
                new NinePatchDrawable(app.menuAssets().toolbarBG[2]));
        button.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                app.showModal(new SettingsModal(app,null));
            }
        });
        return button;
    }
}
