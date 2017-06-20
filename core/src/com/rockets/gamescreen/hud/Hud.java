package com.rockets.gamescreen.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.gamescreen.ActorGroup;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.world.Freshable;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.utils.GraphicsFactory;

/**
 * name: Hud
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class Hud extends GameGroup<Actor> implements Disposable,Freshable {
    Image bg;
    IApp app;
    IGame game;
    HanLabel pauseButton;
    Table centerGroup;
    Table instructions;
    public Hud(IGame game){
        this.game = game;
        this.app = game.iApp();
        init();
    }

    public void init(){
        bg = GraphicsFactory.solidImage(Display.CONTENT_WIDTH,Display.WORLD_BORDER_PAD, Color.BLACK);
        spawn(bg,0,Display.SCREEN_HEIGHT-Display.CONTENT_BOTPAD*2, Align.topLeft);

        initCenterLabel();

        instructions = new Table();
        initInstructions();

    }

    protected abstract void initInstructions();

    private void initCenterLabel() {
        pauseButton =HanLabel.text(app.getString("pause"))
                .font(Font.c1)
                .color(Colr.TEXT_MID)
                .build();
        pauseButton.setHeight(10);
        pauseButton.addListener(new OnClickListener() {
            @Override
            public void onClick() {
                if(game.world().sceneScript().isPauseable()){
                    game.world().pauseGame();
                }
            }
        });
        centerGroup = new Table();
        centerGroup.setHeight(10);
        spawn(pauseButton,0,Display.SCREEN_HEIGHT-Display.CONTENT_BOTPAD*2,Align.topLeft);
        spawn(centerGroup,Display.SCREEN_WIDTH/2,Display.SCREEN_HEIGHT-Display.CONTENT_BOTPAD*2,Align.top);
    }


    public void setInstructionsVisible(boolean visible){
        instructions.setVisible(visible);
    }
    @Override
    public void remove(ActorGroup<Actor> actorGroup) {
        super.remove(actorGroup);
    }

    @Override
    public void dispose() {
        game = null;
        app = null;
    }

    @Override
    public void fresh() {
        instructions.setVisible(true);
    }
}
