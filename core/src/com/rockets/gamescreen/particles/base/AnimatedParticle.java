package com.rockets.gamescreen.particles.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rockets.constants.AnimConst;
import com.rockets.graphics.NestedAnimatedSprite;
import com.rockets.graphics.SingleSpriteActor;

/**
 * name: AnimatedParticle
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class AnimatedParticle extends SingleSpriteActor{
    NestedAnimatedSprite sprite;
    public AnimatedParticle(Array<TextureRegion> frames){
        sprite = new NestedAnimatedSprite(new Animation<>(AnimConst.SHORT,frames));
        setSprite(sprite);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(sprite.isAnimationFinished()){
            remove();
        }
    }
}
