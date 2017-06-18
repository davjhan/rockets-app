package com.rockets.gamescreen.modals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.Icons;
import com.rockets.common.IApp;
import com.rockets.constants.AnimConst;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.modals.OptionsModal.OptionsModalListener;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.IconAndLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.graphics.views.SquishyButton;
import com.rockets.modal.BasicModal;

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
    Image completion;
    IconAndLabel iconAndLabel;
    protected Label title;
    public ChallengeIntroModal(IApp app, Challenges.ChallengeModel challenge, OptionsModalListener modalListener) {
        super(app, modalListener,true,false);
        this.challenge = challenge;
        this.modalListener = modalListener;
        init();
    }

    @Override
    protected void init() {
        super.init();
        Gdx.app.log("tttt ChallengeIntroModal", "width: " +root.getTop());

       // placard.setPosition(root.getX()+root.getWidth()/2,root.getTop()+8,Align.bottom);
        iconAndLabel.setPosition(root.getWidth()/2,root.getHeight(),Align.center);
    }

    @Override
    protected void initTitle() {
        NinePatchDrawable titleBG = new NinePatchDrawable(app.menuAssets().bgs.getWhiteNametag());

        title = HanLabel.text(challenge.getName())
                .font(Font.h1)
                .color(Colr.TEXT_DARK)
                .background(titleBG)
                .build();
        title.setAlignment(Align.center);
        completion = new Image(app.menuAssets().completion[1]);
        iconAndLabel = new IconAndLabel(completion,title);
        root.addActor(iconAndLabel);
    }


    @Override
    protected void initContents() {

        Table startButton = new SquishyButton();

        Label startButtonLabel = HanLabel.text("Start game")
                .color(Colr.TEXT_DARK)
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
        HanButton leaveButton = HanButton.with(app)
                .text("Quit")
                .leftIcon(app.menuAssets().icons[Icons.SETTINGS])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.onLeaveGame();
                        closeModal();
                    }
                })
                .build();
        HanButton settingsButton = HanButton.with(app)
                .text("Options")
                .leftIcon(app.menuAssets().icons[Icons.SETTINGS])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.onLeaveGame();
                        closeModal();
                    }
                })
                .build();
        contents.padTop(Spacing.LARGE);
        contents.align(Align.bottom);
        contents.add(startButton).colspan(2).spaceBottom(Spacing.SMALL).grow();
        contents.row();
        contents.add(leaveButton).fill().spaceRight(Spacing.SMALL);
        contents.add(settingsButton).fill();
    }

    @Override
    protected void animatedCloseModal() {
        super.animatedCloseModal(Actions.scaleBy(0.9f,0.9f, AnimConst.SHORT, Interpolation.bounceOut));
    }
}
