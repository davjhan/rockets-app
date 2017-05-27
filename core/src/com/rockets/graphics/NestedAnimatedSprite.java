package com.rockets.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

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
public class NestedAnimatedSprite extends AnimatedSprite implements Nested {
    Vector2 parentPos = new Vector2();
    Vector2 temp = new Vector2();
    Vector2 parentScale = new Vector2(1,1);

    public NestedAnimatedSprite(Animation<TextureRegion> animation) {
        super(animation);
    }

    public NestedAnimatedSprite(float dur, Array<TextureRegion> sparkles, Animation.PlayMode playmode) {
        super(new Animation<>(dur,sparkles,playmode));
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

    @Override
    public void setTexture(Texture texture) {
        if(getAnimation() instanceof FourDirectionAnimation){
            FourDirectionAnimation anim = (FourDirectionAnimation) getAnimation();
            for(TextureRegion[] frames: anim.getFrameSets()){
                for(TextureRegion frame:frames){
                    frame.setTexture(texture);
                }
            }
        }else{
            TextureRegion[] frames = (TextureRegion[])getAnimation().getKeyFrames();
            for(TextureRegion frame:frames){
                frame.setTexture(texture);
            }
        }

    }

    @Override
    public void setOrigin(int alignment) {
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
