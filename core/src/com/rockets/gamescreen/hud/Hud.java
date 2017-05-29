package com.rockets.gamescreen.hud;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Icons;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.gamescreen.ActorGroup;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.modals.OptionsModal;
import com.rockets.gamescreen.modals.OptionsModal.OptionsModalListener;
import com.rockets.gamescreen.world.Freshable;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.graphics.views.HanIconButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.Modal;

import static com.rockets.constants.Display.TOPBAR_HEIGHT;

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
    Table topTable;
    HanIconButton pauseButton;
    Table centerGroup;
    Table rightGroup;
    Table instructions;
    OptionsModal optionsModal;
    public Hud(IGame game){
        this.game = game;
        this.app = game.iApp();
        init();
    }

    public void init(){
        bg = new Image(game.menuAssets().hudbg);
        bg.setSize(Display.WIDTH,TOPBAR_HEIGHT);
        spawn(bg,0,Display.HEIGHT, Align.topLeft);

        topTable = new Table();
        topTable.setSize(Display.WIDTH,TOPBAR_HEIGHT);
        spawn(topTable,0,Display.HEIGHT,Align.topLeft);
        initCenterLabel();

        instructions = new Table();
        initInstructions();

        optionsModal = new OptionsModal(app, new OptionsModalListener() {
            @Override
            public void onLeaveGame() {
                game.world().goHome();
            }

            @Override
            public void onDismiss(Modal modal) {
                game.world().sceneScript().setPaused(false);
            }
        });

    }

    protected abstract void initInstructions();

    private void initCenterLabel() {
        pauseButton = new HanIconButton(app,app.menuAssets().icons[Icons.PAUSE]);

        pauseButton.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                if(game.world().sceneScript().isPauseable()){
                    spawnOptionsModal();
                }
            }
        });
        centerGroup = new Table();
        rightGroup = new Table();

        topTable.add(pauseButton).width(pauseButton.getHeight());
        topTable.add(centerGroup).align(Align.center).grow();
        topTable.add(rightGroup).align(Align.center).fill().minWidth(pauseButton.getHeight());
    }

    protected void spawnOptionsModal(){
        game.world().sceneScript().setPaused(true);
        game.world().showModal(optionsModal);
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
        optionsModal.dispose();
    }

    @Override
    public void fresh() {
        instructions.setVisible(true);
    }
}
