package com.rockets.uiscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.layout.GridGroup;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.graphics.views.HanIconButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.homescreen.ChallengeButton;
import com.rockets.uiscreens.modals.SettingsModal;
import com.rockets.uiscreens.views.ScrollingTileBG;

import java.util.List;
import java.util.Map;

/**
 * name: LaunchScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HomeScreen extends BaseUIScreen {
    private Table topBar;
    private Table contentTable;

    //Level bar
    GridGroup levelGrid;

    public HomeScreen(IApp game, Map<String,Object> extras) {
        super(game,extras);


        initTables();
        initTitle();
        initBG();

        stage.addActor(topBar);
        stage.addActor(contentTable);
    }



    private void initTables() {
        topBar = new Table();
        contentTable = new Table();
        topBar.setHeight(56);
        topBar.setWidth(Display.WIDTH);
        topBar.setPosition(0, Display.HEIGHT, Align.topLeft);
        contentTable.setWidth(Display.WIDTH);
        contentTable.setHeight(Display.HEIGHT - topBar.getHeight());
        contentTable.align(Align.top);

        initLevels();
    }

    private void initLevels() {
        int itemSize = 50;

        int index = 0;
        List<List<Challenges.ChallengeModel>> challenges = app.contentDB().challenges().getAllByDifficulty(Challenges.EASY);
        for(int dif =0;dif < Challenges.NUM_DIFFICULTIES;dif++){
            levelGrid = new GridGroup(5,Spacing.REG);
            levelGrid.setItemSize(itemSize);

            for(Challenges.ChallengeModel model:challenges.get(dif)) {
                ChallengeButton challengeButton = new ChallengeButton(app, index, model);
                challengeButton.setSize(itemSize, itemSize);
                levelGrid.addActor(challengeButton);
                index ++;
            }
            levelGrid.pack();
            contentTable.add(levelGrid).grow().center();
            contentTable.row();
        }

    }

    private void initBG() {
        ScrollingTileBG scrollingTileBG = new ScrollingTileBG(0.2f, app.menuAssets().bgTilesHome);
        stage.addActor(scrollingTileBG);
        scrollingTileBG.setZIndex(0);
    }

    private void initTitle() {
        Label titleLabel = new VisLabel("BIRD AND COIN GAME", Font.h2, Colors.get(Colr.TEXT_LIGHT));
        topBar.add(titleLabel);

        HanIconButton settingsButton = new HanIconButton(
                app,
                app.menuAssets().icons[1]);
        settingsButton.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                app.showModal(new SettingsModal(app,null));
            }
        });
        settingsButton.setPosition(Display.WIDTH-Spacing.SMALL,Display.HEIGHT-Spacing.SMALL,Align.topRight);
        stage.addActor(settingsButton);


    }

    private void startSPGame() {
        //app.screenManager().pushScreen(new GameScreen(app,"coin_1"));
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            startSPGame();
        }
    }

}
