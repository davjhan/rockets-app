package com.rockets.uiscreens.views;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.IconAndLabel;

/**
 * name: DiamondsDisplay
 * desc:
 * date: 2017-07-21
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class DiamondsDisplay extends Table implements Disposable{
    IconAndLabel iconAndLabel;
    HanLabel label;
    Image icon;
    IApp app;
    public DiamondsDisplay(IApp app){
        this.app = app;
        label = HanLabel.text(getDiamondsCountString())
                .color(Colr.TEXT_LIGHT)
                .build();
        icon = new Image(app.menuAssets().diamondsSmall);
        iconAndLabel = new IconAndLabel(icon,label, Spacing.SMALL);
        add(iconAndLabel).padLeft(Spacing.REG).padRight(Spacing.REG);
        setBackground(app.menuAssets().bgs.getInlay());
        pack();
    }

    public String getDiamondsCountString() {
        return String.valueOf(app.saves().read().getMedalsCount());
    }

    @Override
    public void dispose() {
        this.app = null;
    }
}
