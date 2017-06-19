package com.rockets.uiscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.layout.GridGroup;
import com.rockets.assets.Font;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.homescreen.BottomBar;
import com.rockets.uiscreens.homescreen.ChallengeButton;
import com.rockets.uiscreens.managers.SelectionManager;
import com.rockets.uiscreens.modals.SettingsModal;
import com.rockets.uiscreens.views.ScrollingTileBG;
import com.rockets.uiscreens.views.Selectable;
import com.rockets.uiscreens.views.ViewFactory;

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
    private BottomBar bottomBar;
    private Table levelsRack;
    private ScrollPane scrollPane;
    private HanTextButton playButton;
    //Level bar
    GridGroup levelGrid;
    SelectionManager<String> selectionManager;

    public HomeScreen(IApp game, Map<String,Object> extras) {
        super(game,extras);


        selectionManager = new SelectionManager<>(false);
        initTables();
        initTitle();
        initBG();

        stage.addActor(topBar);
        stage.addActor(contentTable);

        stage.addActor(bottomBar);
        bottomBar.setWidth(contentTable.getWidth());

        String selectedButtonId = app.contentDB().challenges().getAllByDifficulty(Challenges.EASY).get(0).get(0).id;
        selectionManager.select(selectedButtonId);
    }



    private void initTables() {
        topBar = new Table();
        contentTable = new Table();
        levelsRack = new Table();
        scrollPane = new ScrollPane(levelsRack);
        scrollPane.setScrollingDisabled(true,false);

        topBar.setHeight(56);
        topBar.setWidth(Display.SCREEN_WIDTH);
        topBar.setPosition(0, Display.SCREEN_HEIGHT, Align.topLeft);
        contentTable.setWidth(Display.SCREEN_WIDTH);
        contentTable.setHeight(Display.SCREEN_HEIGHT - topBar.getHeight());
        contentTable.align(Align.top);


        initLevels();

        initBottomBar();
        contentTable.add(scrollPane).grow();
        contentTable.row();

    }

    private void initBottomBar() {
        selectionManager.setListener(new SelectionManager.SelectionChangedListener<String>() {
            @Override
            public void onSelectionChanged(Selectable selectable, String data) {
                Challenges.ChallengeModel model = app.contentDB().challenges().getById(data);
                try {
                    bottomBar.onSelectionChanged(app.contentDB().challenges().getChallengeIndex(data),model);
                } catch (Challenges.ChallengeNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        bottomBar = new BottomBar(app,selectionManager);
    }

    private void initLevels() {
        int index = 0;
        List<List<Challenges.ChallengeModel>> challenges = app.contentDB().challenges().getAllByDifficulty(Challenges.EASY);
        for(int dif =0;dif < Challenges.NUM_DIFFICULTIES;dif++){
            levelGrid = new GridGroup(6,Spacing.SMALL);
            levelGrid.setItemSize(ChallengeButton.WIDTH,ChallengeButton.HEIGHT);

            for(Challenges.ChallengeModel model:challenges.get(dif)) {
                final ChallengeButton challengeButton = new ChallengeButton(app, index, model);
                selectionManager.addSelectable(challengeButton,model.id);
                challengeButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        if(challengeButton.isSelectable()){
                            selectionManager.select(challengeButton);
                        }
                    }
                });
                levelGrid.addActor(challengeButton);
                index ++;
            }
            levelGrid.pack();
            levelsRack.add(levelGrid).grow().center().padLeft(7);
            levelsRack.row();
        }

    }

    private void initBG() {
        ScrollingTileBG scrollingTileBG = new ScrollingTileBG(0.2f, app.menuAssets().bgTilesHome);
        stage.addActor(scrollingTileBG);
        scrollingTileBG.setZIndex(0);
    }

    private void initTitle() {
        Label titleLabel = HanLabel.text("GAME TITLE")
                .font(Font.grand2)
                .build();
        topBar.add(titleLabel);
        HanButton settingsButton = ViewFactory.getSettingsButtonSmall(app,new OnClickListener(){

            @Override
            public void onClick() {
                app.showModal(new SettingsModal(app,null));
            }
        });
        settingsButton.pack();
        settingsButton.setPosition(Display.SCREEN_WIDTH -Spacing.SMALL,Display.SCREEN_HEIGHT -Spacing.SMALL,Align.topRight);
        stage.addActor(settingsButton);


    }

    @Override
    public void dispose() {
        super.dispose();
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
