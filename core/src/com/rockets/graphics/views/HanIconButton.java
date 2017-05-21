package com.rockets.graphics.views;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.rockets.common.IApp;

/**
 * name: HanImageButton
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanIconButton extends Table implements Clickable {
    private Drawable bgUp;
    private Drawable bgDown;
    private Drawable iconUp;
    private Drawable iconDown;
    private float offsetY = 1;
    private float pressedOffsetY = 1;
    private Image image;

    public HanIconButton(IApp app,Drawable iconUp,Drawable bgUp,Drawable bgDown){
        super();
        this.iconUp = iconUp;
        this.iconDown = iconUp;
        this.bgDown = bgDown;
        this.bgUp = bgUp;
        init();
    }
    public HanIconButton(IApp app, TextureRegion iconUp){
        this(app,iconUp,
                app.menuAssets().btnGeneral[0],
                app.menuAssets().btnGeneral[1]);
    }
    public HanIconButton(IApp app, TextureRegion iconUp, NinePatch bgUp,NinePatch bgDown){
        this(app,new TextureRegionDrawable(iconUp),
                new NinePatchDrawable(bgUp),
                new NinePatchDrawable(bgDown));
    }

    public void init() {
        image = new Image(iconUp);
        image.setScaling(Scaling.none);
        add(image).center().grow().padBottom(offsetY);
        setTouchable(Touchable.enabled);
        pad(8);
        pack();
        setBackground(bgUp);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(bgDown);
                image.setDrawable(iconDown);
                image.moveBy(0,-pressedOffsetY);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setBackground(bgUp);
                image.setDrawable(iconUp);
                image.moveBy(0,pressedOffsetY);
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
