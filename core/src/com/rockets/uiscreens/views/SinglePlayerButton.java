package com.rockets.uiscreens.views;

import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Catalog;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;

/**
 * name: ChangeSkinGroupButton
 * desc:
 * date: 2017-02-02
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SinglePlayerButton extends TableButton {
    public SinglePlayerButton(IApp app){
        super(app,app.menuAssets().bgs.getGroup(Catalog.Backgrounds.solid));

        HanLabel text = new HanLabel("SINGLE PLAYER", Font.h1, Colr.TEXT_DARK);
        text.setAlignment(Align.center);
        pad(Spacing.REG);
        add(text).grow();
        pack();
    }

}
