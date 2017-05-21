package com.rockets.graphics.views;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.rockets.assets.Colr;
import com.rockets.utils.GraphicsFactory;

/**
 * name: HanTextField
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class HanTextField extends TextField {
    public HanTextField(String text) {
        super(text, VisUI.getSkin(),"default");
    }

    public HanTextField(String text, String fontName, String fontColor, Drawable background) {
        super(text, VisUI.getSkin(),"default");
        Drawable cursor = GraphicsFactory.solidNineDrawable(Colors.get(Colr.TEXT_LIGHT));
        TextFieldStyle style = getStyle();
        style.font = VisUI.getSkin().getFont(fontName);
        style.fontColor = Colors.get(fontColor);
        style.background = background;
        style.cursor = cursor;
    }

    public HanTextField(String text, TextFieldStyle style) {
        super(text, style);
    }
}
