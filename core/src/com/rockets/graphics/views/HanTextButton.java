package com.rockets.graphics.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * name: HanButton
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanTextButton extends VisTextButton implements Clickable {
    TextButtonStyle normalStyle;
    TextButtonStyle disabledStyle;

    public HanTextButton(String text, String styleName) {
        super(text, styleName);
        init();
    }

    public HanTextButton(String text) {
        super(text);
        init();
    }

    public HanTextButton(String text, OnClickListener listener) {
        super(text);
        addClickListener(listener);
        init();
    }

    public HanTextButton(String text, String fontname, OnClickListener listener) {
        super(text);
        getLabel().setStyle(new Label.LabelStyle(VisUI.getSkin().getFont(fontname),getLabel().getColor()));
        addClickListener(listener);
        init();
    }
    public HanTextButton(String text, String fontname, Color fontColor, OnClickListener listener) {
        super(text);
        getLabel().setStyle(new Label.LabelStyle(VisUI.getSkin().getFont(fontname), fontColor));
        addClickListener(listener);
        init();
    }
    public HanTextButton(String text, String fontname,String fontColor, OnClickListener listener) {
        super(text);
        getLabel().setStyle(new Label.LabelStyle(VisUI.getSkin().getFont(fontname), Colors.get(fontColor)));
        addClickListener(listener);
        init();
    }

    public HanTextButton(String text, VisTextButtonStyle buttonStyle) {
        super(text, buttonStyle);
        init();
    }

    public HanTextButton(String text, String fontname, String fontColor) {
        super(text);
        getLabel().setStyle(new Label.LabelStyle(VisUI.getSkin().getFont(fontname), Colors.get(fontColor)));
        init();
    }

    private void init() {
        getLabel().scaleBy(2);
        normalStyle = getStyle();
        //disabledStyle =  VisUI.getSkin().get(HanSkin.DISABLED,TextButtonStyle.class);
        getLabelCell().padTop(4);
        getLabelCell().padBottom(2);
        getLabelCell().padLeft(8);
        getLabelCell().padRight(8);
        pack();
    }

    @Override
    public void setDisabled(boolean isDisabled) {
        super.setDisabled(isDisabled);
    }

    public void setClickEnabled(boolean clickable){
        if(!clickable){
            setTouchable(Touchable.disabled);
            setStyle(disabledStyle);
        }else{
            setTouchable(Touchable.enabled);
            setStyle(normalStyle);
        }
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public void addClickListener(OnClickListener clickListener) {
        addListener(clickListener);
    }
}
