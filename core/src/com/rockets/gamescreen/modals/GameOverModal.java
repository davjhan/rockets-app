package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.assets.Font;
import com.rockets.assets.VisUILoader;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.modals.PauseModal.OptionsModalListener;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.uiscreens.views.ViewFactory;

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
    IGame game;
    public GameOverModal(IGame game, OptionsModalListener modalListener) {
        super(game.iApp(), modalListener,true,false);
        this.modalListener = modalListener;
        this.game = game;
        init();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public String getTitleString() {
        return "Game Over";
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitle(app.getString("game_over"));
    }

    @Override
    protected void initContents() {

        HanButton replay = HanButton.with(app)
                .text(app.getString("play_again"))
                .fontName(Font.h2)
                .style(HanButton.PRIMARY)
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        closeModal();
                    }
                }).build();
        replay.setStyle(VisUI.getSkin().get(VisUILoader.PRIMARY_LG, VisTextButton.VisTextButtonStyle.class));

        HanButton leaveButton = ViewFactory.getQuitButton(game,new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        contents.align(Align.bottom);
        contents.add(replay).spaceBottom(Spacing.REG);
        contents.row();
        contents.add(leaveButton).fill();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.game = null;
    }
}
