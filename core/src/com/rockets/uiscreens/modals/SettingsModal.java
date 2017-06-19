package com.rockets.uiscreens.modals;

import com.badlogic.gdx.Gdx;
import com.rockets.Rockets;
import com.rockets.common.IApp;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;
import com.rockets.uiscreens.views.InlineView;

/**
 * name: SettingsModal
 * desc:
 * date: 2017-01-07
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SettingsModal extends BasicModal {

    public SettingsModal(IApp app, ModalListener modalListener) {
        super(app, modalListener,true,true);
        init();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public String getTitleString() {
        return "Settings";
    }

    @Override
    protected void initContents() {
        InlineView reset = new InlineView(app, "reset", "This will reset everything.", new OnClickListener() {
            @Override
            public void onClick() {
                Gdx.app.getPreferences(Rockets.SHARED_PREFS_NAME).clear();
                Gdx.app.getPreferences(Rockets.SHARED_PREFS_NAME).flush();
                Gdx.app.exit();
                closeModal();
            }
        });
        reset.setBackground(app.menuAssets().bgs.getInlay());
        contents.add(reset).row();
    }
}
