package com.rockets.graphics.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * name: ClickListener
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class OnClickListener extends com.badlogic.gdx.scenes.scene2d.utils.ClickListener{
    public abstract void onClick();

    public OnClickListener(){
        super(-1);
    }
    @Override
    public void clicked(InputEvent event, float x, float y) {
        onClick();
        super.clicked(event, x, y);
    }
}
