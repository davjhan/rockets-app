package com.rockets.gamescreen.modals;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.Icons;
import com.rockets.constants.AnimConst;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.ActionFactory;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;


/**
 * name: GameOverModal
 * desc:
 * date: 2017-01-04
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeCompleteModal extends BasicModal {
    Table rightTable;
    ChallengeCompleteModalListener modalListener;
    Challenges.ChallengeModel challenge;
    Table completionGraphic;
    Image coinImg;
    private float showDelay = 0.5f;
    IGame game;
    public ChallengeCompleteModal(IGame game, Challenges.ChallengeModel challenge, ChallengeCompleteModalListener modalListener) {
        super(game.iApp(), modalListener,true,false,game.iApp().menuAssets().bgs.getGoldFrameBg());
        this.game = game;
        this.challenge = challenge;
        this.modalListener = modalListener;
        init();
    }

    @Override
    protected void init() {
        super.init();
        title.setPosition(root.getWidth()/2,root.getHeight(),Align.center);
    }

    @Override
    protected void initTitle() {
        NinePatchDrawable titleBG = new NinePatchDrawable(app.menuAssets().bgs.getGoldNameTag());

        title = HanLabel.text("STAGE CLEARED!")
                .font(Font.h1)
                .color(Colr.TEXT_BROWN)
                .background(titleBG)
                .build();
        title.setAlignment(Align.center);
        root.addActor(title);
    }

    @Override
    protected void initContents() {
        completionGraphic = new Table();
        completionGraphic.setBackground(app.menuAssets().bgs.getFrameBg());
        HanLabel challengeCompleted = HanLabel.text("STAGE CLEARED!")
                .build();

        coinImg = new Image(app.gameAssets().coinLarge[1]);
        coinImg.invalidate();
        coinImg.setOrigin(Align.center);
        completionGraphic.pad(16);
        completionGraphic.add(coinImg).center().spaceBottom(Spacing.REG).spaceTop(Spacing.REG);
        //completionGraphic.row();
       // completionGraphic.add(challengeCompleted);
        HanButton nextChallengeButton = HanButton.with(app)
                .text("NEXT STAGE")
                .fontName(Font.h2)
                .style(HanButton.PRIMARY)
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.nextChallenge();
                        closeModal();
                    }
                }).build();
        HanButton leaveButton = HanButton.with(app)
                .text("QUIT")
                .leftIcon(app.menuAssets().icons[Icons.BACK])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.goHome();
                        closeModal();
                    }
                }).build();
        HanButton playAgain = HanButton.with(app)
                .text("PLAY AGAIN")
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.playAgain();
                        closeModal();
                    }
                }).build();

        HanButton settingsButton = HanButton.with(app)
                .text("Options")
                .leftIcon(app.menuAssets().icons[Icons.SETTINGS])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        game.world().showOptionsMenu();
                    }
                })
                .build();

        contents.align(Align.bottom);
        contents.add(completionGraphic).growY().padTop(Spacing.LARGE).spaceBottom(Spacing.LARGE).center().colspan(2);
        contents.row();
        contents.add(nextChallengeButton).grow().spaceBottom(Spacing.SMALL).colspan(2);
        contents.row();
        contents.add(playAgain).fill().spaceBottom(Spacing.SMALL).colspan(2);
        contents.row();
        contents.add(leaveButton).fill().spaceRight(Spacing.SMALL);
        contents.add(settingsButton).fill();

    }
    public static interface ChallengeCompleteModalListener extends ModalListener{
        void goHome();
        void playAgain();
        void nextChallenge();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
    }

    @Override
    protected void onShow() {
        super.onShow();
        coinImg.addAction(Actions.sequence(
                Actions.delay(1.1f),
                Actions.scaleTo(1.1f,1.1f,AnimConst.SHORT, Interpolation.pow3Out),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        coinImg.setDrawable(new TextureRegionDrawable(app.gameAssets().coinLarge[0]));
                        root.addAction(ActionFactory.shake(root,5));
                        completionGraphic.setBackground(app.menuAssets().bgs.getCoinDisplayBgGold());
                    }
                }),
                Actions.scaleTo(1,1,AnimConst.MEDIUM, Interpolation.elasticOut)
        ));
    }

    @Override
    protected Action getRootEnterAction() {
        return Actions.sequence(
                Actions.delay(showDelay),
                super.getRootEnterAction()
        );
    }
    protected Action getDimEnterAction() {
        return Actions.sequence(
                Actions.delay(0.2f),
                Actions.visible(true),
                Actions.alpha(0),
                Actions.fadeIn(AnimConst.FAST)
        );
    }

}
