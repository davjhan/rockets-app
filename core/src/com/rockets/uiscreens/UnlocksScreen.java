package com.rockets.uiscreens;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
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
import com.rockets.uiscreens.views.PagedScrollpane;
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
public class UnlocksScreen extends BaseUIScreen {
    private Table topBar;
    private Table contentTable;
    private PagedScrollpane<String> scrollPane;
    private HanButton actionButton;
    private HanButton backButton;
    private HanLabel medalsCount;
    private HanLabel pageIndex;
    private List<SkinModel> allSkins;
    private final SelectionManager<String> selectionManager = new SelectionManager<>(false);
    private float underPageY;

    public UnlocksScreen(IApp game, Map<String, Object> extras) {
        super(game, extras);
        initData();
        initTables();

        initTopBar();


        initBG();

        rootTable.add(topBar).growX();
        rootTable.row();
        rootTable.add(contentTable).grow();
        rootTable.pack();
        String selectedId = app.saves().read().getCurrentSkinId();
        selectionManager.select(selectedId);


        underPageY = contentTable.getY(Align.center) - 42;
        initFixed();
        initBehaviors();
    }

    private void initBehaviors() {
        selectionManager.setListener(new SelectionManager.SelectionChangedListener<String>() {
            @Override
            public void onSelectionChanged(Selectable selectable, String data) {
                int selectedIndex = allSkins.indexOf(app.contentDB().skins().getById(data));
                pageIndex.setText((selectedIndex + 1) + "/" + allSkins.size());

                refreshToSelection(data);
            }


        });
        scrollPane.selectNoAnimate(app.saves().read().getCurrentSkinId());
    }

    private void initData() {
        allSkins = app.contentDB().skins().getAllSkins();
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
        scrollPane = new PagedScrollpane<>(selectionManager);
        scrollPane.setupOverscroll(32, 20, 200);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setPageSpacing(24);
        scrollPane.setFlingTime(0.15f);
        scrollPane.addPagedScrollpaneListener(new PagedScrollpane.PagedScrollpaneListener() {
            @Override
            public void onStateChanged(boolean isMoving) {
                actionButton.setVisible(!isMoving);
            }
        });
        for (final SkinModel skinModel : allSkins) {
            final SkinDisplayItem skinDisplayItem = new SkinDisplayItem(app, skinModel);
            skinDisplayItem.addListener(new OnClickListener() {
                @Override
                public void onClick() {
                    scrollPane.select(skinModel.id);
                }
            });
            scrollPane.addPage(skinDisplayItem, skinModel.id);
            selectionManager.addSelectable(skinDisplayItem, skinModel.id);
        }
        scrollPane.pack();
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
        initButtons();

        pageIndex = HanLabel.text("10")
                .font(Font.c1)
                .color(Colors.get(Colr.TEXT_LIGHT))
                .build();
        pageIndex.setPosition(Display.SCREEN_WIDTH / 2, Spacing.XXLARGE, Align.bottom);
        contentTable.addActor(pageIndex);

        stage.addActor(backButton);
    }

    private void initButtons() {
        actionButton = HanButton.with(app)
                .text(app.getString("select"))
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {

                    }
                })
                .style(HanButton.PRIMARY)
                .build();
        actionButton.pack();
        actionButton.setWidth(100);
        actionButton.setVisible(false);
        actionButton.setPosition(Display.SCREEN_WIDTH / 2, underPageY, Align.top);
        stage.addActor(actionButton);
    }

    private void initTopBar() {


        Label titleLabel = HanLabel.text("UNLOCKS")
                .font(Font.grand2)
                .build();

        Table topTray = new Table();

        medalsCount = HanLabel.text("10")
                .font(Font.h2)
                .build();


        topTray.add(medalsCount);

        topBar.add(titleLabel).pad(Spacing.REG).spaceBottom(Spacing.REG);
        topBar.row();
        topBar.add(topTray).spaceBottom(Spacing.REG);
    }

    private void refreshToSelection(String data) {
        boolean isUnlocked = app.saves().read().isSkinUnlocked(data);
        SkinModel selectedModel = app.contentDB().skins().getById(data);
        actionButton.setVisible(true);
        actionButton.setDisabled(false);
        if (isUnlocked) {
            actionButton.setText(app.getString("select"));
        } else {
            if (selectedModel.type.equals(SkinModel.Type.IAP)) {
                actionButton.setText("BUY NOW");
            } else if (selectedModel.type.equals(SkinModel.Type.UNLOCK)) {
                if (app.saves().read().getMedalsCount() >= selectedModel.price) {
                    actionButton.setText("Unlock!");
                } else {
                    actionButton.setText("INSUFFICIENT");
                    actionButton.setDisabled(true);
                }
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
