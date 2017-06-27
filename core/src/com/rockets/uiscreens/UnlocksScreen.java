package com.rockets.uiscreens;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Font;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.SkinModel;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.uiscreens.managers.SelectionManager;
import com.rockets.uiscreens.modals.SettingsModal;
import com.rockets.uiscreens.uniqueviews.SkinDisplayItem;
import com.rockets.uiscreens.views.ScrollingTileBG;
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
public class UnlocksScreen extends BaseUIScreen {
    private Table topBar;
    private Table contentTable;
    private Table scrollPaneContent;
    private ScrollPane scrollPane;
    private HanButton facebookButton;
    private HanButton backButton;
    private HanLabel medalsCount;
    SelectionManager<String> selectionManager;

    public UnlocksScreen(IApp game, Map<String, Object> extras) {
        super(game, extras);


        selectionManager = new SelectionManager<>(false);
        initTables();

        initFixed();
        initTopBar();


        initBG();

        rootTable.add(topBar).growX();
        rootTable.row();
        rootTable.add(contentTable).grow();
        rootTable.pack();
        String selectedId = app.saves().read().getCurrentSkinId();
        selectionManager.select(selectedId);
    }

    private void initTables() {
        topBar = new Table();
        contentTable = new Table();

        initSkinDisplay();
        contentTable.align(Align.center);
        contentTable.add(scrollPane).grow();
        contentTable.row();

    }

    @Override
    public void show() {
        super.show();
        selectionManager.refresh();
    }

    private void initSkinDisplay() {
        HorizontalGroup skinsContainer = new HorizontalGroup();
        scrollPane = new ScrollPane(skinsContainer);
        scrollPane.setupOverscroll(32, 150, 200);
        scrollPane.setScrollingDisabled(false, true);

        skinsContainer.space(Spacing.LARGE);
        List<SkinModel> allSkins = app.contentDB().skins().getAllSkins();
        for(SkinModel skinModel: allSkins){
            SkinDisplayItem skinDisplayItem = new SkinDisplayItem(app,skinModel);
            skinsContainer.addActor(skinDisplayItem);
            selectionManager.addSelectable(skinDisplayItem,skinModel.id);
        }
        skinsContainer.pack();
    }

    private void initBG() {
        ScrollingTileBG scrollingTileBG = new ScrollingTileBG(0.2f, app.menuAssets().bgTilesHome);
        stage.addActor(scrollingTileBG);
        scrollingTileBG.setZIndex(0);
    }

    private void initFixed() {
        HanButton settingsButton = ViewFactory.getSettingsButtonSmall(app, new OnClickListener() {

            @Override
            public void onClick() {
                app.showModal(new SettingsModal(app, null));
            }
        });
        settingsButton.pack();
        settingsButton.setPosition(Display.SCREEN_WIDTH - Spacing.SMALL, Display.SCREEN_HEIGHT - Spacing.SMALL, Align.topRight);
        stage.addActor(settingsButton);


        backButton = ViewFactory.getBackButton(app, new OnClickListener() {
            @Override
            public void onClick() {
                app.screenManager().restoreScreen(HomeScreen.class);
            }
        });
        backButton.pack();
        backButton.setPosition(Spacing.SMALL, Display.SCREEN_HEIGHT - Spacing.SMALL, Align.topLeft);
        stage.addActor(backButton);
    }

    private void initTopBar() {


        Label titleLabel = HanLabel.text("UNLOCKS")
                .font(Font.grand2)
                .build();

        Table topTray = new Table();

        medalsCount = HanLabel.text("10")
                .font(Font.h2)
                .build();

        topTray.add(facebookButton).spaceRight(Spacing.REG);
        topTray.add(medalsCount);

        topBar.add(titleLabel).pad(Spacing.REG).spaceBottom(Spacing.REG);
        topBar.row();
        topBar.add(topTray).spaceBottom(Spacing.REG);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
