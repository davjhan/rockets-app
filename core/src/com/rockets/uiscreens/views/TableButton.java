package com.rockets.uiscreens.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Catalog;
import com.rockets.common.IApp;
import com.rockets.graphics.views.Clickable;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.listeners.SquishyButtonListener;

/**
 * name: ButtonTable
 * desc:
 * date: 2017-02-02
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class TableButton extends Table implements Clickable {
    private final NinePatchDrawable[] backgroundSet;

    public TableButton(IApp app, final NinePatchDrawable[] backgroundSet){

        this.backgroundSet = backgroundSet;
        setBackground(backgroundSet[Catalog.normal]);
        setTouchable(Touchable.enabled );
        setTransform(true);
        addListener(new SquishyButtonListener());
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(backgroundSet[Catalog.down]);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(backgroundSet[Catalog.normal]);
                super.touchUp(event, x, y, pointer, button);
            }
        });

    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setOrigin(Align.center);
    }


    @Override
    public void addClickListener(OnClickListener clickListener) {
        addListener(clickListener);
    }
}
