package com.rockets.gamescreen.modals;

import com.rockets.assets.Catalog;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;

/**
 * name: OptionsModal
 * desc:
 * date: 2017-01-01
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class OptionsModal extends BasicModal {
    OptionsModalListener modalListener;

    public OptionsModal(IApp app, OptionsModalListener modalListener){
        super(app, modalListener, app.menuAssets().bgs.get(Catalog.Backgrounds.bordered));
        this.modalListener = modalListener;

        init();
    }

    @Override
    protected void init() {
        setMinWidth(200);
        super.init();
    }

    @Override
    protected void initTitle() {
        setTitle(app.getString("options"));
    }

    @Override
    protected void initContents() {
        HanTextButton leaveButton = new HanTextButton(app.getString("leave_game"), Font.p1,new OnClickListener(){

            @Override
            public void onClick() {
                closeModal();
                modalListener.onLeaveGame();
            }
        });

        HanTextButton settingsButton = new HanTextButton(app.getString("settings"), Font.p1,new OnClickListener(){

            @Override
            public void onClick() {
                closeModal();
            }
        });

        contents.add(settingsButton).row();
        contents.add(leaveButton).row();
    }



}
