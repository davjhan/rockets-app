package com.rockets.uiscreens.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.listeners.SquishyButtonListener;

/**
 * name: InlineView
 * desc:
 * date: 2017-01-07
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class InlineView extends Table {
    HanLabel titleLabel;
    HanLabel subTitleLabel;
    HanLabel valueLabel;

    public InlineView(IApp app, String title, String subtitle, String value, OnClickListener clickListener){
        add(getTitle(title)).growX().left().expandY();
        if(value != null){

            add(getValue(value)).space(Spacing.SMALL).expandY();
          }

        if(subtitle != null){
            subTitleLabel = new HanLabel(subtitle,Font.c1, Colr.TEXT_LIGHT);
            row();
            add(subTitleLabel).colspan(getColumns()).grow().spaceTop(Spacing.XSMALL);
        }

        pack();
        if(clickListener != null){
            addListener(clickListener);
            addListener(new SquishyButtonListener());
            setTransform(true);
            setOrigin(Align.center);
        }
    }

    private Actor getValue(String value) {
        valueLabel = new HanLabel(value,Font.p1);
        return valueLabel;
    }

    public InlineView(IApp app,String title,String subtitle,OnClickListener clickListener){
        this(app,title,subtitle,null,clickListener);
    }

    private Actor getTitle(String title) {
        titleLabel = new HanLabel(title, Font.h1, Colr.TEXT_LIGHT);

        return titleLabel;
    }
}
