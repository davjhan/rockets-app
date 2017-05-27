package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.assets.VisUILoader;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
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
public class GameOverModal extends BasicModal {
    Table rightTable;
    OptionsModalListener modalListener;
    public GameOverModal(IApp app, OptionsModalListener modalListener) {
        super(app, modalListener,true,false);
        this.modalListener = modalListener;
        init();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initTitle() {
        setTitle(app.getString("game_over"));
    }

    @Override
    protected void initContents() {

        HanTextButton readyButton = new HanTextButton("Play Again",  Font.h2,Colr.TEXT_DARK,new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
            }
        });
        readyButton.setStyle(VisUI.getSkin().get(VisUILoader.PRIMARY_LG, VisTextButton.VisTextButtonStyle.class));

        HanTextButton leaveButton = new HanTextButton("Exit", new OnClickListener() {
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
