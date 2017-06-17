package com.rockets.modal;

import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;

/**
 * name: ErrorModal
 * desc:
 * date: 2017-01-12
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ErrorModal extends BasicModal {
    String message;

    public ErrorModal(IApp app, String message) {
        super(app, null, true, false);
        this.message = message;
        init();
    }

    @Override
    protected void initTitle() {
        setTitle("Error");
    }

    @Override
    protected void initContents() {
        HanLabel msg = HanLabel.text(message)
                .font(Font.p1)
                .color(Colr.TEXT_LIGHT)
                .build();
        HanTextButton okay = new HanTextButton("okay");
        okay.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
            }
        });
        msg.setWrap(true);
        contents.add(msg).spaceBottom(Spacing.SMALL).minWidth(160).grow();
        contents.row();
        contents.add(okay);
        contents.pack();
    }
}
