package com.rockets.uiscreens.modals;

import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Icons;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanButton;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;
import com.rockets.modal.ModalListener;

/**
 * name: FacebookConversionModal
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class InfoModal extends BasicModal {
    HanButton confirmButton;
    HanLabel body;
    String titleString;
    String bodyText;
    public InfoModal(IApp app, ModalListener modalListener,String title,String bodyText) {
        super(app, modalListener, true, true);
        this.titleString = title;
        this.bodyText = bodyText;
        init();
    }
    public InfoModal(IApp app,String title,String bodyText) {
        super(app, null, true, true);
        this.titleString = title;
        this.bodyText = bodyText;
        init();
    }
    @Override
    protected void initContents() {
        body = HanLabel.text(bodyText)
                .align(Align.left)
                .wrap(true)
                .build();

        confirmButton = HanButton.with(app)
                .text(app.getString("got_it"))
                .leftIcon(app.menuAssets().icons[Icons.CHECK_MARK])
                .onClick(new OnClickListener() {
                    @Override
                    public void onClick() {
                        closeModal();
                    }
                })
                .build();

        contents.add(body).width(196).grow();
        contents.row();
        contents.add(confirmButton).spaceTop(Spacing.REG).grow();
    }
    @Override
    public String getTitleString() {
        return titleString;
    }
}
