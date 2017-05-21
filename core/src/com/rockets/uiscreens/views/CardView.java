package com.rockets.uiscreens.views;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.Catalog;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;

/**
 * name: CardView
 * desc:
 * date: 2017-01-07
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class CardView extends Table {

    protected NinePatchDrawable[] backgrounds;
    private HanLabel titleBarLabel;
    public CardView(IApp app){
        super();
        backgrounds = app.menuAssets().cardBg.getGroup(Catalog.CardBg.titlebar);
        setBackground(backgrounds[Catalog.normal]);
        initTitlebarLabel();
    }
    protected void setTitle(String title){
        this.titleBarLabel.setText(title);
    }
    private void initTitlebarLabel() {
        pad(Spacing.SMALL);
        padTop(0);
        titleBarLabel = new HanLabel("", Font.c1, Colr.TEXT_DARK);
        titleBarLabel.setAlignment(Align.bottom);
        add(titleBarLabel).height(11).colspan(2).growX().spaceBottom(3);
        row();
    }
    protected void flashFrame(){
        setBackground(new NinePatchDrawable(backgrounds[Catalog.selected]));
        addAction(Actions.sequence(Actions.delay(0.2f),Actions.run(new Runnable() {
            @Override
            public void run() {
                setBackground(new NinePatchDrawable(backgrounds[Catalog.normal]));
            }
        })));
    }
}
