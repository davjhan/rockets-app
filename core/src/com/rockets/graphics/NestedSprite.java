package com.rockets.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

/**
 * name: NestedSprite
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class NestedSprite extends Sprite implements Nested {

    Vector2 parentPos = new Vector2();
    Vector2 temp = new Vector2();
    Vector2 parentScale = new Vector2(1,1);

    public NestedSprite() {
    }

    public NestedSprite(Texture texture) {
        super(texture);
    }

    public NestedSprite(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
    }

    public NestedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public NestedSprite(TextureRegion region) {
        super(region);
    }

    public NestedSprite(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public NestedSprite(Sprite sprite) {
        super(sprite);
    }



    @Override
    public void draw(Batch batch, float alphaModulation, float parentX, float parentY, float parentScaleX, float parentScaleY,float rotation) {
        temp.set(parentX-parentPos.x,parentY-parentPos.y);
        translate(temp.x,temp.y);
        temp.set(parentScaleX/parentScale.x,parentScaleY/parentScale.y);
        setScale(getScaleX()*temp.x,getScaleY()*temp.y);
        parentPos.set(parentX,parentY);
        parentScale.set(parentScaleX,parentScaleY);
        setRotation(rotation);
        super.draw(batch, alphaModulation);
    }

    public void setOrigin (int alignment) {
        float originX,originY;
        if ((alignment & left) != 0)
            originX = 0;
        else if ((alignment & right) != 0)
            originX = getWidth();
        else
            originX = getWidth() / 2;

        if ((alignment & bottom) != 0)
            originY = 0;
        else if ((alignment & top) != 0)
            originY = getHeight();
        else
            originY = getHeight() / 2;

        setOrigin(originX,originY);
    }


    @Override
    public void dispose() {

    }

}
