package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.modal.Modal;
import com.rockets.modal.ModalListener;

/**
 * name: WelcomeModal
 * desc:
 * date: 2017-01-10
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class WelcomeModal extends Modal {
    public WelcomeModal(IApp app, ModalListener modalListener) {
        super(app, modalListener, false, false, app.menuAssets().bgs.getModalBg());
    }

    protected void init() {
        super.init();

        HanTextButton startButton = new HanTextButton("Start");
//        startButton.addClickListener(new OnClickListener() {
//            @Override
//            public void onClick() {
//                room.sendEvent(Events.join_game);
//                closeModal();
//            }
//        });
        Table imageTable = new Table();
        imageTable.setBackground(app.menuAssets().bgs.getModalBg());

        root.add(imageTable).height(100).width(160).spaceBottom(Spacing.SMALL);
        root.row();
        root.add(startButton).growX();
        root.pack();


        setRootPosition();
    }
}
