package com.rockets.uiscreens;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
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
import com.rockets.uiscreens.uniqueviews.SkinDisplayItem;
import com.rockets.uiscreens.views.DiamondsDisplay;
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
    private WidgetGroup floatingContent;
    private PagedScrollpane<String> scrollPane;
    private HanButton actionButton;
    private HanButton backButton;
    private DiamondsDisplay diamondsDisplay;
    private HanLabel pageIndex;
    private List<SkinModel> allSkins;
    private final SelectionManager<String> selectionManager = new SelectionManager<>(false);

    private float topBarPad = Spacing.REG;
    private float underPageY;

    public UnlocksScreen(IApp game, Map<String, Object> extras) {
        super(game, extras);
        initData();
        initTables();
        initBG();

        initTopBar();
        initScrollpane();


        rootTable.add(topBar).growX().fillY();
        rootTable.row();
        rootTable.add(scrollPane).grow();
        rootTable.pack();
        rootTable.addActor(floatingContent);
        String selectedId = app.saves().read().getCurrentSkinId();
        selectionManager.select(selectedId);



        underPageY = scrollPane.getY(Align.center) - 56;

        initFixed();
        initBehaviors();
    }

    private void initHelp() {
        HanLabel helpTextTop = HanLabel.text(app.getString("help_skins_overview"))
                .font(Font.h1)
                .color(Colr.TEXT_NAVY)
                .wrap(true)
                .align(Align.center)
                .build();

        HanLabel helpTextBottom = HanLabel.text(app.getString("help_skins_cosmetic"))
                .font(Font.c1)
                .color(Colr.TEXT_NAVY)
                .wrap(true)
                .align(Align.center)
                .build();

        Table helpTable = new Table();
        helpTable.background(app.menuAssets().bgs.getWhiteNametag());
        helpTable.pad(Spacing.DOUBLE_REG);
        helpTable.add(helpTextTop).grow().spaceBottom(Spacing.REG);
        helpTable.row();
        helpTable.add(helpTextBottom).grow();
        helpTable.pack();

        helpTable.setWidth(Display.CONTENT_WIDTH-(Spacing.REG*2));
        helpTable.setHeight(helpTable.getPrefHeight());
        helpTable.setPosition(Display.SCREEN_WIDTH_HALF,topBar.getY(),Align.top);
        floatingContent.addActor(helpTable);
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
        floatingContent = new Table();
    }

    @Override
    public void show() {
        super.show();
        selectionManager.refresh();
    }

    private void initScrollpane() {
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
        initButtons();
        initHelp();
        pageIndex = HanLabel.text("10")
                .font(Font.c1)
                .color(Colors.get(Colr.TEXT_LIGHT))
                .build();
        pageIndex.setPosition(Display.SCREEN_WIDTH / 2, Spacing.XXLARGE, Align.bottom);
        floatingContent.addActor(pageIndex);
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
        actionButton.setPosition(Display.SCREEN_WIDTH_HALF, underPageY, Align.top);
        floatingContent.addActor(actionButton);
    }

    private void initTopBar() {
        diamondsDisplay = new DiamondsDisplay(app);
        diamondsDisplay.setPosition(Display.SCREEN_WIDTH - topBarPad, Display.SCREEN_HEIGHT - topBarPad, Align.topRight);
        stage.addActor(diamondsDisplay);

        backButton = ViewFactory.getBackButton(app, new OnClickListener() {
            @Override
            public void onClick() {
                app.screenManager().restoreScreen(HomeScreen.class);
            }
        });
        backButton.pack();
        backButton.setPosition(topBarPad, Display.SCREEN_HEIGHT - topBarPad, Align.topLeft);
        stage.addActor(backButton);

        Label titleLabel = HanLabel.text("UNLOCKS")
                .font(Font.grand2)
                .build();
        topBar.add(titleLabel).pad(Spacing.REG).spaceBottom(Spacing.REG);


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
        diamondsDisplay.dispose();
    }
}
