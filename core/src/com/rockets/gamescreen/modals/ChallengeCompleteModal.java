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


/**
 * name: GameOverModal
 * desc:
 * date: 2017-01-04
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeCompleteModal extends BasicModal {
    Table rightTable;
    OptionsModalListener modalListener;
    Challenges.ChallengeModel challenge;
    public ChallengeCompleteModal(IApp app, Challenges.ChallengeModel challenge, OptionsModalListener modalListener) {
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
        setTitle(app.getString("challenge_completed"));
    }

    @Override
    protected void initContents() {

        HanTextButton readyButton = new HanTextButton("NEXT CHALLENGE", new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
            }
        });
        readyButton.setStyle(VisUI.getSkin().get("primary", VisTextButton.VisTextButtonStyle.class));

        HanTextButton leaveButton = new HanTextButton("EXIT", new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        contents.align(Align.bottom);
        contents.add(readyButton).spaceBottom(Spacing.REG);
        contents.row();
        contents.add(leaveButton).fill();
    }

}
