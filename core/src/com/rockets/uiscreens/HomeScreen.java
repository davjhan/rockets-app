package com.rockets.uiscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.GameScreen;
import com.rockets.graphics.views.HanIconButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.modals.SettingsModal;
import com.rockets.uiscreens.views.ScrollingTileBG;

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
    private Table leftTable;
    private Table rightTable;

    public HomeScreen(IApp game) {
        super(game);


        initTables();
        initTitle();
        initBG();
        initGraphic();

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

        leftTable = new Table();
        leftTable.padRight(0);
        leftTable.align(Align.top);
        rightTable = new Table();
        rightTable.pad(Spacing.REG);
        rightTable.padLeft(0);
        contentTable.add(leftTable).width(220).right().spaceRight(Spacing.REG);
        contentTable.add(rightTable).width(180);

    }

    private void initBG() {
        ScrollingTileBG scrollingTileBG = new ScrollingTileBG(0.2f, app.menuAssets().bgTilesHome);
        stage.addActor(scrollingTileBG);
        scrollingTileBG.setZIndex(0);
    }

    private void initGraphic() {

    }

    private void initTitle() {
        Label titleLabel = new VisLabel("FRUITBALL", Font.num1, Colors.get(Colr.TEXT_LIGHT));
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
        //HorizontalGroup buttonGroup = new HorizontalGroup();
       // buttonGroup.space(Spacing.REG);
//        Button startSPGame = new HanTextButton(app.getString("start_sp_game"));
//        Button startMpGame = new HanTextButton(app.getString("start_mp_game"));
//        Button startUOfTMP = new HanTextButton("David's IP");
//        Button changeCharacters = new HanTextButton(app.getString("change_skin"));
//        buttonGroup.addActor(startSPGame);
//        buttonGroup.addActor(startMpGame);
//        buttonGroup.addActor(startUOfTMP);
//        buttonGroup.addActor(changeCharacters);
//        rootTable.add(buttonGroup).expand();
//
//        startMpGame.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                app.screenManager().pushScreen(new JoinMPGameScreen(app));
//
//                super.clicked(event, x, y);
//            }
//        });
//
//        startSPGame.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                startSPGame();
//
//                super.clicked(event, x, y);
//            }
//        });
//        changeCharacters.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                changeCharacters();
//
//                super.clicked(event, x, y);
//            }
//        });
//        startUOfTMP.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                app.screenManager().pushScreen(new JoinMPGameScreen(app, "100.65.124.54"));
//
//                super.clicked(event, x, y);
//            }
//        });
//        rootTable.pack();


    }

    private void startSPGame() {
        app.screenManager().pushScreen(new GameScreen(app));
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            startSPGame();
        }
    }

}
