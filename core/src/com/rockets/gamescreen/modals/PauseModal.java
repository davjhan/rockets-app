package com.rockets.gamescreen.modals;

import com.rockets.assets.Font;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;
import com.rockets.uiscreens.views.ViewFactory;

/**
 * name: OptionsModal
 * desc:
 * date: 2017-01-01
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class PauseModal extends BasicModal {
    OptionsModalListener modalListener;
    IGame game;
    public PauseModal(IGame game, OptionsModalListener modalListener){
        super(game.iApp(), modalListener, game.iApp().menuAssets().bgs.getWhiteFrameBg());
        this.modalListener = modalListener;
        this.game = game;
        init();
    }

    @Override
    protected void init() {
        //setMinWidth(200);
        super.init();
    }

    @Override
    public String getTitleString() {
        return "Paused";
    }

    @Override
    protected void initContents() {
        HanButton resume = HanButton.with(app)
                .text(app.getString("resume"))
                .fontName(Font.h1)
                .style(HanButton.PRIMARY)
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        animatedCloseModal();
                    }
                }).build();
        HanButton leaveButton = ViewFactory.getQuitButton(game, new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
                modalListener.onLeaveGame();
            }
        });
        HanButton settingsButton = ViewFactory.getSettingsButton(game);
        contents.add(resume).spaceBottom(Spacing.SMALL).grow().colspan(2).row();
        contents.add(leaveButton).spaceRight(Spacing.SMALL).grow();
        contents.add(settingsButton).grow();
    }

    public interface OptionsModalListener extends ModalListener {
        void onLeaveGame();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
    }
}
