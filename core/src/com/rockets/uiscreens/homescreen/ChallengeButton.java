package com.rockets.uiscreens.homescreen;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.kotcrab.vis.ui.VisUI;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.GameScreen;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.graphics.views.HanTextButton;

/**
 * name: LevelButton
 * desc:
 * date: 2017-05-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeButton extends GameGroup<Actor> {
    HanTextButton textButton;
    public ChallengeButton(final IApp app, int index,final Challenges.ChallengeModel model){
        textButton = new HanTextButton(String.valueOf(index));
        TextButton.TextButtonStyle textButtonStyle = textButton.getNormalStyle();
        NinePatch[] drawables = app.menuAssets().levelButtonsRegular;
        textButtonStyle.up = new NinePatchDrawable(drawables[0]);
        textButtonStyle.down = new NinePatchDrawable(drawables[1]);
        textButtonStyle.checked = new NinePatchDrawable(drawables[0]);
        textButtonStyle.font = VisUI.getSkin().getFont(Font.h2);

        textButton.setNormalStyle(textButtonStyle);
        spawn(textButton);
        textButton.pack();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.screenManager().pushScreen(GameScreen.class,GameScreen.getChallengeExtras(model.id));
            }
        });
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        textButton.setSize(getWidth(),getHeight());
    }
}
