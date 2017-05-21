package com.rockets.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.modals.GameOverModal;
import com.rockets.gamescreen.modals.OptionsModalListener;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.Modal;

/**
 * name: Hud
 * desc:
 * date: 2016-12-30
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Hud extends GameGroup<Actor>  implements Disposable{
    protected IGame game;
    protected HanTextButton optionsButton;
    protected GameGroup<Actor> topBar;
    protected float midHudY;
    public Hud(IGame game){
        this.game = game;
        initTables();
    }

    private void initTables() {

        topBar = new GameGroup<>();
        topBar.setSize(Display.WIDTH, Display.HEIGHT-Display.WORLD_HEIGHT);
        topBar.setPosition(0,Display.HEIGHT, Align.topLeft);

        //topBar.setBackground(GraphicsFactory.solidDrawable(Colors.get(Colr.BG)));
        Gdx.app.log("tttt Hud", "rightbar width: " + topBar.getWidth());
        midHudY = 40;
        addActor(topBar);

        //Image divider = new Image(GraphicsFactory.solidDrawable(Colors.get(Colr.HUD_DIVIDER)));

       // divider.setSize(2,Display.HEIGHT);
        //divider.setPosition(Display.HUD_X,0,Align.bottomLeft);
        //addActor(divider);
    }

    protected void init(){
        optionsButton = new HanTextButton("options", Font.c1,Colr.TEXT_DARK);
        optionsButton.padTop(Spacing.XSMALL+4);
        optionsButton.padBottom(Spacing.XSMALL);
        optionsButton.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                spawnOptionsModal();
            }
        });
        optionsButton.setPosition(Display.WIDTH,Display.HEIGHT,Align.topRight);
        addActor(optionsButton);
    }

    public void spawnOptionsModal() {
//        game.iApp().showModal(new OptionsModal(game.iApp(),new OptionsModalListener(){
//
//            @Override
//            public void onDissmiss(Modal modal) {
//
//            }
//
//            @Override
//            public void onLeaveGame() {
//                game.quit();
//            }
//        }));

        game.iApp().showModal(new GameOverModal(game.iApp(),new OptionsModalListener(){

            @Override
            public void onDissmiss(Modal modal) {

            }

            @Override
            public void onLeaveGame() {
                game.quit();
            }
        }));
    }

    @Override
    public void dispose() {

        game = null;
    }
}
