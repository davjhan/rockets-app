package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.data.readonly.Challenges;
import com.rockets.graphics.views.HanTextButton;
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
    public ChallengeCompleteModal(IApp app, Challenges.ChallengeModel challenge, ChallengeCompleteModalListener modalListener) {
        super(app, modalListener,true,false);
        this.challenge = challenge;
        this.modalListener = modalListener;
        init();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitle(app.getString("challenge_completed"));
    }

    @Override
    protected void initContents() {

        HanTextButton nextChallengeButton = new HanTextButton("NEXT CHALLENGE", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.nextChallenge();
                closeModal();
            }
        });
        nextChallengeButton.setStyle(VisUI.getSkin().get("primary", VisTextButton.VisTextButtonStyle.class));

        HanTextButton playAgainButton = new HanTextButton("PLAY AGAIN", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.playAgain();
                closeModal();
            }
        });
        HanTextButton leaveButton = new HanTextButton("QUIT TO HOME", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.goHome();
                closeModal();
            }
        });
        contents.align(Align.bottom);
        contents.add(nextChallengeButton).spaceBottom(Spacing.REG);
        contents.row();
        contents.add(playAgainButton).fill();
        contents.row();
        contents.add(leaveButton).fill();

    }
    public static interface ChallengeCompleteModalListener extends ModalListener{
        void goHome();
        void playAgain();
        void nextChallenge();
    }

}
