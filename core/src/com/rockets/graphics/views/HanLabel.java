package com.rockets.graphics.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rockets.common.IApp;

/**
 * name: HanLabel
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanLabel extends VisLabel {
    private boolean isForceAllCaps = true;
    public HanLabel(boolean isForceAllCaps) {
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, Color textColor, boolean isForceAllCaps) {
        this(text, textColor);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, int alignment, boolean isForceAllCaps) {
        this(text, alignment);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, boolean isForceAllCaps) {
        this(text);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, LabelStyle style, boolean isForceAllCaps) {
        this(text, style);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, String styleName, boolean isForceAllCaps) {
        this(text, styleName);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, String fontName, Color color, boolean isForceAllCaps) {
        super(text, fontName, color);
        this.isForceAllCaps = isForceAllCaps;
    }

    public HanLabel(CharSequence text, String fontName, String colorName, boolean isForceAllCaps) {
        super(text, fontName, Colors.get(colorName));
        this.isForceAllCaps = isForceAllCaps;
        checkTextCase();
    }

    public HanLabel(CharSequence text, Color textColor) {
        super(text, textColor);
    }

    public HanLabel(CharSequence text, int alignment) {
        super(text, alignment);
    }

    public HanLabel(CharSequence text) {
        super(text);
    }

    public HanLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public HanLabel(CharSequence text, String styleName) {
        super(text, styleName);
    }

    public HanLabel(CharSequence text, String fontName, Color color) {
        super(text, fontName, color);
        checkTextCase();
    }

    public HanLabel(CharSequence text, String fontName, String colorName) {
        super(text, fontName, Colors.get(colorName));
        checkTextCase();
    }

    public HanLabel(IApp iApp, CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public HanLabel(String text, String fontName, String fontColor) {
        this(text, fontName, Colors.get(fontColor));
        checkTextCase();
    }

    public void forceAllCaps(boolean force){
        this.isForceAllCaps = force;
        checkTextCase();
    }

    @Override
    public void setText(CharSequence newText) {
        if(isForceAllCaps){
            super.setText(((String)newText).toUpperCase());
        }else {
            super.setText(newText);
        }
    }
    private void checkTextCase(){
        if(isForceAllCaps){
            super.setText(getText().toString().toUpperCase());
        }
    }

    public void setFontColor(Color fontColor) {
        LabelStyle style = new LabelStyle(getStyle());
        style.fontColor = fontColor;
        setStyle(style);
    }
    public void setBackground(Drawable bg){
        LabelStyle style = getStyle();
        style.background = bg;
        setStyle(style);
    }

}
