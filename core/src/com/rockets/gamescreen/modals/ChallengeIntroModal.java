package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.modals.PauseModal.OptionsModalListener;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.graphics.views.SquishyButton;
import com.rockets.modal.BasicModal;
import com.rockets.uiscreens.views.ViewFactory;

/**
 * name: GameOverModal
 * desc:
 * date: 2017-01-04
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeIntroModal extends BasicModal {
    OptionsModalListener modalListener;
    Challenges.ChallengeModel challenge;
    IGame game;
    public ChallengeIntroModal(IGame game, Challenges.ChallengeModel challenge, OptionsModalListener modalListener) {
        super(game.iApp(), modalListener,true,false);
        this.game = game;
        this.challenge = challenge;
        this.modalListener = modalListener;
        init();
    }

    @Override
    public String getTitleString() {
        return challenge.getName();
    }


    @Override
    protected void initContents() {

        Table startButton = new SquishyButton();

        Label startButtonLabel = HanLabel.text("Start game")
                .color(Colr.TEXT_BROWN)
                .font(Font.h2)
                .build();

        Image startButtonIcon = new Image(app.menuAssets().bigPlaySymbol);

        startButton.pad(Spacing.LARGE);
        startButton.add(startButtonIcon).padTop(Spacing.REG);
        startButton.row();
        startButton.add(startButtonLabel).spaceTop(Spacing.REG);
        startButton.pack();

        startButton.setBackground(app.menuAssets().bgs.getStartButtonBg());
        startButton.addListener(new OnClickListener() {
            @Override
            public void onClick() {
                animatedCloseModal();
            }
        });
        HanButton leaveButton = ViewFactory.getQuitButton(game,new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        HanButton settingsButton = ViewFactory.getSettingsButton(game);
        contents.align(Align.bottom);
        contents.add(startButton).colspan(2).spaceBottom(Spacing.SMALL).grow();
        contents.row();
        contents.add(leaveButton).fill().spaceRight(Spacing.SMALL);
        contents.add(settingsButton).fill();
    }

    @Override
    protected Action getDimEnterAction() {
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
    }
}
