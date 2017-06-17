package com.rockets.graphics.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;

/**
 * name: HanLabel
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanLabel extends VisLabel {
    private boolean isForceAllCaps = true;

    public HanLabel(CharSequence text, String styleName) {
        this(text, new LabelStyle(VisUI.getSkin().get(styleName,LabelStyle.class)));
    }
    public HanLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        init();

    }

    private void init() {
        checkTextCase();

    }

    public void forceAllCaps(boolean force) {
        this.isForceAllCaps = force;
        checkTextCase();
    }



    @Override
    public void invalidateHierarchy() {
        super.invalidateHierarchy();
    }

    private void checkTextCase() {
        if (isForceAllCaps) {
            super.setText(getText().toString().toUpperCase());
        }
        setAlignment(Align.center);
    }

    public void setFontColor(Color fontColor) {
        getStyle().fontColor = fontColor;
    }

    public void setBackground(Drawable bg) {
       getStyle().background = bg;
    }

    public static LabelBuilder text(String text) {
        return new LabelBuilder(text);
    }

    public static class LabelBuilder {
        String text;
        private String fontName = Font.h1;
        private Drawable bg;
        private String colorName = Colr.TEXT_LIGHT;
        private boolean forceAllCaps = true;
        int alignment = Align.center;

        public LabelBuilder(String text) {
            this.text = text;
        }

        public LabelBuilder font(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public LabelBuilder color(String colorName) {
            this.colorName = colorName;
            return this;
        }

        public LabelBuilder background(Drawable bg) {
            this.bg = bg;
            return this;
        }

        public LabelBuilder forceAllCaps(boolean forceAllCaps) {
            this.forceAllCaps = forceAllCaps;
            return this;
        }
        public LabelBuilder align(int alignment) {
            this.alignment = alignment;
            return this;
        }
        public HanLabel build() {
            LabelStyle style = new LabelStyle();
            style.font = VisUI.getSkin().getFont(fontName);
            if (this.colorName != null) {
                style.fontColor = Colors.get(colorName);
            }
            if (this.bg != null) {
                if(fontName.equals(Font.h1)){
                    bg.setLeftWidth(8);
                    bg.setRightWidth(8);
                }else {
                    bg.setLeftWidth(12);
                    bg.setRightWidth(12);
                }
                style.background = bg;
            }
            HanLabel label = new HanLabel(text, style);
            label.setAlignment(alignment);
            label.isForceAllCaps = true;
            return label;
        }


    }

}
