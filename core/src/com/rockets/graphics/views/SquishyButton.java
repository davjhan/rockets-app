package com.rockets.graphics.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rockets.uiscreens.listeners.SquishyButtonListener;

/**
 * name: SquishButton
 * desc:
 * date: 2017-06-17
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SquishyButton extends Table {
    public SquishyButton(){
        setTouchable(Touchable.enabled);
        setTransform(true);
        addListener(new SquishyButtonListener());
    }

    @Override
    public void pack() {
        super.pack();

        setOrigin(Align.center);
    }
}
