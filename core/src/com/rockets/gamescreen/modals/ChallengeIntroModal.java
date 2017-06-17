package com.rockets.gamescreen.modals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.Icons;
import com.rockets.assets.VisUILoader;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.modals.OptionsModal.OptionsModalListener;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.IconAndLabel;
import com.rockets.graphics.views.OnClickListener;
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
        iconAndLabel.setPosition(root.getX()+root.getWidth()/2,root.getTop(),Align.center);
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
        addActor(iconAndLabel);
    }


    @Override
    protected void initContents() {

        HanTextButton readyButton = new HanTextButton("START GAME", new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
            }
        });
        Label readyButtonLabel = readyButton.getLabel();
        Image playButtonSymbol = new Image(app.menuAssets().bigPlaySymbol);
        readyButton.pad(Spacing.LARGE);
        readyButton.clearChildren();
        readyButton.add(playButtonSymbol).spaceTop(Spacing.REG);
        readyButton.row();
        readyButton.add(readyButtonLabel).spaceTop(Spacing.REG);

        readyButton.setStyle(VisUI.getSkin().get(VisUILoader.PRIMARY_LG, VisTextButton.VisTextButtonStyle.class));

        HanTextButton settingsButton = new HanTextButton("SETTINGS", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        settingsButton.setLeftIcon(app.menuAssets().icons[Icons.SETTINGS]);
        HanTextButton leaveButton = new HanTextButton("QUIT", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        leaveButton.setLeftIcon(app.menuAssets().icons[Icons.BACK]);
        contents.padTop(Spacing.LARGE);
        contents.align(Align.bottom);
        contents.add(readyButton).colspan(2).spaceBottom(Spacing.SMALL).grow();
        contents.row();
        contents.add(leaveButton).fill().spaceRight(Spacing.SMALL);
        contents.add(settingsButton).fill();
    }

}
