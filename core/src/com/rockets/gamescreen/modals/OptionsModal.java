package com.rockets.gamescreen.modals;

import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.assets.Font;
import com.rockets.assets.VisUILoader;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;

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
        super(app, modalListener, app.menuAssets().bgs.getModalBg());
        this.modalListener = modalListener;

        init();
    }

    @Override
    protected void init() {
        //setMinWidth(200);
        super.init();
    }

    @Override
    protected void initTitle() {
        super.initTitle();
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
        HanTextButton resumeButton = new HanTextButton(app.getString("resume"),new OnClickListener(){
            @Override
            public void onClick() {
                animatedCloseModal();
            }
        });
        resumeButton.setStyle(VisUI.getSkin().get(VisUILoader.PRIMARY_LG,VisTextButton.VisTextButtonStyle.class));
        HanTextButton settingsButton = new HanTextButton(app.getString("settings"), Font.p1,new OnClickListener(){

            @Override
            public void onClick() {
                animatedCloseModal();
            }
        });
        contents.add(resumeButton).spaceBottom(Spacing.REG).grow().row();
        contents.add(settingsButton).spaceBottom(Spacing.REG).grow().row();
        contents.add(leaveButton).spaceBottom(Spacing.REG).grow().row();
    }

    public interface OptionsModalListener extends ModalListener {
        void onLeaveGame();
    }


}
