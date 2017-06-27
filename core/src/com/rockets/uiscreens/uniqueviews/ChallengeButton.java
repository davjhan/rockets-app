package com.rockets.uiscreens.uniqueviews;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.graphics.views.HanLabel;
import com.rockets.uiscreens.listeners.SquishyButtonListener;
import com.rockets.uiscreens.views.Selectable;

/**
 * name: LevelButton
 * desc:
 * date: 2017-05-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeButton extends GameGroup<Actor> implements Selectable {
    HanLabel indexLabel;
    Image image;
    boolean selected;
    Drawable bgNormal;
    Drawable bgSelected;
    int isCompleted;
    public static final int WIDTH = 38;
    public static final int HEIGHT = 52;
    Challenges.ChallengeModel model;
    IApp app;
    public ChallengeButton(final IApp app, int index, final Challenges.ChallengeModel model) {
        this.app = app;
        this.model = model;
        setSize(WIDTH, HEIGHT);
        refresh();
        image = new Image(bgNormal);
        image.setFillParent(true);
        indexLabel = HanLabel.text(String.valueOf(index + 1))
                .font(Font.c1)
                .color(Colr.TEXT_BROWN)
                .build();
        spawn(image);
        spawn(indexLabel, WIDTH / 2, 13, Align.center);
        addListener(new SquishyButtonListener());
        setOrigin(Align.center);
    }


    @Override
    protected void sizeChanged() {
        super.sizeChanged();
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            image.setDrawable(bgSelected);
        } else {
            image.setDrawable(bgNormal);
        }
    }

    @Override
    public void refresh() {
        isCompleted = app.backend().challenges().didComplete(model.id)? 1: 0;
        bgNormal = new TextureRegionDrawable(app.menuAssets().levelButtons[isCompleted][0]);
        bgSelected = new TextureRegionDrawable(app.menuAssets().levelButtons[isCompleted][1]);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.app =null;
        this.model = null;
    }

    @Override
    public boolean isSelectable() {
        return true;
    }
}
