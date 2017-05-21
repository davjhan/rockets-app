package com.rockets.uiscreens.views;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
public class SquareScreenGotoButton extends TableButton {
    public SquareScreenGotoButton(IApp app, String title, TextureRegion icon){
        super(app,app.menuAssets().bgs.getGroup(Catalog.Backgrounds.solid));

        Image image = new Image(icon);
        HanLabel text = new HanLabel(title, Font.h1, Colr.TEXT_DARK);
        text.setAlignment(Align.top);
        pad(Spacing.REG);
        add(image).expandX().spaceBottom(Spacing.SMALL);
        row();
        add(text);
        pack();
    }

}
