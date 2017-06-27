package com.rockets.uiscreens.uniqueviews;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.VisUILoader;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.GameScreen;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.uiscreens.managers.SelectionManager;

/**
 * name: BottomBar
 * desc:
 * date: 2017-06-15
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class BottomBar extends Table {
    Table root;
    HanLabel challengeTitle;
    HanTextButton playButton;

    public BottomBar(final IApp app,final SelectionManager<String> selectionManager){
        root = new Table();
        pad(Spacing.REG);
        padTop(0);
        root.pad(Spacing.REG);
        root.setBackground(app.menuAssets().bgs.getFrameBg());
        challengeTitle = HanLabel.text("")
                .font(Font.h1)
                .color(Colr.TEXT_NAVY)
                .background(app.menuAssets().bgs.getWhiteNametag())
                .align(Align.center)
                .build();

        challengeTitle.setAlignment(Align.center);
        challengeTitle.setWidth(challengeTitle.getPrefWidth());
        playButton = new HanTextButton("PLAY", VisUILoader.PRIMARY_LG);
        playButton.setHeight(Spacing.XXLARGE);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.screenManager().pushScreen(GameScreen.class, GameScreen.getChallengeExtras(selectionManager.getSelectedData()));
            }
        });
        add(root).grow();
        root.add(playButton).grow().padTop(Spacing.REG);
        pack();
        addActor(challengeTitle);

    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        challengeTitle.setPosition(getWidth()/2,root.getTop()-Spacing.REG,Align.center);
    }

    public void onSelectionChanged(int index, Challenges.ChallengeModel model) {
        challengeTitle.setText(index+". "+model.getName());
        challengeTitle.pack();
        challengeTitle.setPosition(getWidth()/2,root.getTop(),Align.center);
    }
}
