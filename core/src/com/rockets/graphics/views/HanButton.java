package com.rockets.graphics.views;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Spacing;

/**
 * name: HanButton
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HanButton extends Button implements Clickable {
    public static final String DEFAULT = "default";
    public static final String PRIMARY = "primary";
    Image leftIcon;
    HanLabel label;
    boolean enabled = true;

    private HanButton(Builder builder) {
        super(VisUI.getSkin());
        float padding = 4;
        VisTextButton.VisTextButtonStyle style;
        switch (builder.style){
            case PRIMARY:
                style = VisUI.getSkin().get(PRIMARY,VisTextButton.VisTextButtonStyle.class);
                break;
            default:
                style = VisUI.getSkin().get(DEFAULT,VisTextButton.VisTextButtonStyle.class);
                break;
        }
        setStyle(style);
        if(builder.leftIcon != null){
            leftIcon = new Image(builder.leftIcon);
            add(leftIcon).spaceRight(Spacing.SMALL);
        }
        if(builder.text != null){
            label = HanLabel.text(builder.text)
                    .align(Align.center)
                    .font(builder.fontName)
                    .forceAllCaps(builder.allCaps)
                    .color(style.fontColor)
                    .build();
            switch (builder.fontName){
                case Font.h1:
                    padding = 8;
                    break;
                case Font.h2:
                    padding = 12;
                    break;
            }
            add(label);
        }
        if(builder.onClick != null){
            addClickListener(builder.onClick);
        }
        pad(padding);
    }

    public void setText(CharSequence text){
        if(label != null){
            label.setText(text);
        }
    }

    public static Builder with(IApp app) {
        return new Builder(app);
    }

    @Override
    public void addClickListener(OnClickListener clickListener) {
        addListener(clickListener);
    }

    public static final class Builder {
        private String text = null;
        private Drawable leftIcon = null;
        private String style = "default";
        private IApp app;
        private String fontName = Font.h1;
        private OnClickListener onClick = null;
        private boolean allCaps = true;
        private Builder(IApp app) {
            this.app = app;
        }


        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder fontName(String fontName){
            this.fontName = fontName;
            return this;
        }

        public Builder leftIcon(Drawable leftIcon) {
            this.leftIcon = leftIcon;
            return this;
        }
        public Builder leftIcon(TextureRegion leftIcon) {
            this.leftIcon = new TextureRegionDrawable(leftIcon);
            return this;
        }
        public Builder style(String style) {
            this.style = style;
            return this;
        }
        public Builder onClick(OnClickListener listener){
            this.onClick = listener;
            return this;
        }
        public Builder allCaps(boolean allCaps){
            this.allCaps = allCaps;
            return this;
        }
        public HanButton build() {
            HanButton button = new HanButton(this);
            app = null;
            return button;
        }
    }
}
