package com.rockets.graphics.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * name: HanImageButton
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanImageButton extends Button implements Clickable {
    Drawable bgUp;
    Drawable bgDown;

    public HanImageButton(Skin skin) {
        super(skin);
        init();
    }

    public HanImageButton(Skin skin, String styleName) {
        super(skin, styleName);
        init();
    }

    public HanImageButton(Actor child, Skin skin, String styleName) {
        super(child, skin, styleName);
        init();
    }

    public HanImageButton(Actor child, ButtonStyle style) {
        super(child, style);
        init();
    }

    public HanImageButton(ButtonStyle style) {
        super(style);
        init();
    }

    public HanImageButton() {
    }

    public HanImageButton(Drawable up) {
        super(up);
        init();
    }
    public HanImageButton(Drawable up, Drawable bgUp, Drawable bgDown) {
        super(up);
        this.bgUp = bgUp;
        this.bgDown = bgDown;
        init();
    }
    public HanImageButton(Drawable up, Drawable down, Drawable bgUp, Drawable bgDown) {
        super(up, down);
        this.bgUp = bgUp;
        this.bgDown = bgDown;
        init();
    }

    public HanImageButton(Actor child, Skin skin) {
        super(child, skin);
        init();
    }

    public void init() {
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(bgDown);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(bgUp);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    public void removeClickListener(OnClickListener listener) {
        removeListener(listener);
    }


    @Override
    public boolean remove() {
        clearListeners();
        return super.remove();
    }

    @Override
    public void addClickListener(OnClickListener clickListener) {
        addListener(clickListener);
    }
}
