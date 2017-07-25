package com.rockets.gamescreen.modals;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.constants.AnimConst;
import com.rockets.constants.Spacing;
import com.rockets.data.ChallengeCompleteReceipt;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.ActionFactory;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;
import com.rockets.uiscreens.views.ViewFactory;


/**
 * name: GameOverModal
 * desc:
 * date: 2017-01-04
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeCompleteModal extends BasicModal {
    private ChallengeCompleteModalListener modalListener;
    private ChallengeCompleteReceipt receipt;
    private Table completionGraphic;
    private Image coinImg;
    private float showDelay = 0.5f;
    private IGame game;

    public ChallengeCompleteModal(IGame game, ChallengeCompleteReceipt receipt, ChallengeCompleteModalListener modalListener) {
        super(game.iApp(), modalListener, true, false, game.iApp().menuAssets().bgs.getGoldFrameBg());
        this.game = game;
        this.modalListener = modalListener;
        this.receipt = receipt;
        init();
    }

    @Override
    protected void init() {
        super.init();
        // if(receipt.showMedalEarned()){
        showMedalEarned();
        // }
    }

    private void showMedalEarned() {
        Table medalEarnedGroup = new Table();
        medalEarnedGroup.setTransform(true);
        Vector2 medalEarnedGroupPos = new Vector2(contents.getX(Align.center), contents.getY()-36);

        Image medalImage = new Image(app.menuAssets().medalsLarge);

        HanLabel medalText = HanLabel.text("+1 DIAMOND")
                .color(Colr.MEDAL_GREEN)
                .align(Align.left)
                .build();

        medalEarnedGroup.add(medalImage).spaceRight(Spacing.SMALL);
        medalEarnedGroup.add(medalText).center();
        medalEarnedGroup.pack();

        medalEarnedGroup.setPosition(medalEarnedGroupPos.x, medalEarnedGroupPos.y, Align.top);

        float medalsShowDelay = 1.6f;

        medalEarnedGroup.addAction(
                Actions.sequence(
                        Actions.alpha(0),
                        Actions.delay(medalsShowDelay),
                        ActionFactory.fadeUp(),
                        Actions.delay(0.2f),
                        ActionFactory.springPulsate()
                )
        );
        medalEarnedGroup.setOrigin(Align.center);
        root.addActor(medalEarnedGroup);
    }

    @Override
    public String getTitleString() {
        return "STAGE CLEAR!";
    }

    @Override
    protected Drawable getTitleBG() {
        return app.menuAssets().bgs.getGoldNameTag();
    }

    @Override
    protected void initContents() {
        completionGraphic = new Table();
        completionGraphic.setBackground(app.menuAssets().bgs.getFrameBg());

        coinImg = new Image(app.gameAssets().coinLarge[1]);
        coinImg.invalidate();
        coinImg.setOrigin(Align.center);
        completionGraphic.pad(16);
        completionGraphic.add(coinImg).center().spaceBottom(Spacing.REG).spaceTop(Spacing.REG);

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
        HanButton leaveButton = ViewFactory.getQuitButton(game, new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.goHome();
                closeModal();
            }
        });
        HanButton playAgain = HanButton.with(app)
                .text("PLAY AGAIN")
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        modalListener.playAgain();
                        closeModal();
                    }
                }).build();

        HanButton settingsButton = ViewFactory.getSettingsButton(game);

        contents.align(Align.bottom);
        contents.add(completionGraphic).growY().spaceBottom(Spacing.LARGE).center().colspan(2);
        contents.row();
        contents.add(nextChallengeButton).grow().spaceBottom(Spacing.SMALL).colspan(2);
        contents.row();
        contents.add(playAgain).fill().spaceBottom(Spacing.SMALL).colspan(2);
        contents.row();
        contents.add(leaveButton).fill().spaceRight(Spacing.SMALL);
        contents.add(settingsButton).fill();

    }

    public interface ChallengeCompleteModalListener extends ModalListener {
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
                Actions.scaleTo(1.1f, 1.1f, AnimConst.SHORT, Interpolation.pow3Out),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        coinImg.setDrawable(new TextureRegionDrawable(app.gameAssets().coinLarge[0]));
                        root.addAction(ActionFactory.shake(root, 5));
                        completionGraphic.setBackground(app.menuAssets().bgs.getCoinDisplayBgGold());
                    }
                }),
                Actions.scaleTo(1, 1, AnimConst.MEDIUM, Interpolation.elasticOut),
                ActionFactory.pulsate()
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
