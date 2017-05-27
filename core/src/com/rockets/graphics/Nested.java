package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

/**
 * name: NestedDrawable
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Nested extends Disposable{
    public void draw(Batch batch, float alphaModulation, float parentX, float parentY,float parentScaleX,float parentscaleY,float rotation);
    public void setPosition(float x, float y);
    public void setOrigin(int align);

    float getHeight();

    float getWidth();
}
