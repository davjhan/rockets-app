package com.rockets.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.HanTextField;
import com.rockets.graphics.views.OnClickListener;


/**
 * name: TextInputModal
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class TextInputModal extends Modal{
    HanTextField textField;
    TextInputListener listener;
    String titleText;
    String inputText;

    public TextInputModal(IApp app,
                          String
             titleText, String text, TextInputListener listener) {
        super(app, listener, true, true,app.menuAssets().bgs.getModalBg());
        this.listener = listener;
        this.inputText = text;
        this.titleText = titleText;
        init();
        root.pack();
    }
    public interface TextInputListener extends ModalListener{
        void onTextReceived(String text);
    }

    @Override
    protected void closeModal() {

        listener.onTextReceived(textField.getText());
        listener = null;
        Gdx.input.setOnscreenKeyboardVisible(false);
        super.closeModal();
    }

    protected void init() {
        super.init();
        HanLabel titleLabel = HanLabel.text(titleText)
                .font(Font.h1)
                .color(Colr.TEXT_LIGHT)
                .build();
        textField = new HanTextField(inputText);

        textField.setCursorPosition(textField.getText().length());

        HanTextButton doneButton = new HanTextButton(app.getString("done"));
        doneButton.addClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                closeModal();
            }
        });

        root.add(titleLabel).spaceBottom(Spacing.REG).colspan(2);
        root.row();
        root.add(textField).grow().spaceRight(Spacing.SMALL);
        root.add(doneButton);
        root.pack();
        setRootPosition();
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if(stage != null){
            stage.setKeyboardFocus(textField);
        }
    }

    protected void setRootPosition() {
        root.setPosition(Display.HALF_WIDTH,Display.SCREEN_HEIGHT - Spacing.LARGE, Align.top);
    }

}
