package com.rockets.graphics.views;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.rockets.constants.Spacing;

/**
 * name: IconAndLabel
 * desc:
 * date: 2016-08-19
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class IconAndLabel extends Table {
    public IconAndLabel(Widget left, Widget right){
        this(left,right, Spacing.XSMALL);

    }
    public IconAndLabel(Widget left, Widget right, float spacing){
        super();
        add(left).expandY().spaceRight(spacing);
        add(right).expandY();
        pack();
    }
}
