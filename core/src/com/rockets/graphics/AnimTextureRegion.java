package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * name: AnimSprite
 * desc:
 * date: 2015-11-15
 * author: david
 * Copyright (c) 2015 David Han
 **/
public class AnimTextureRegion {
    private boolean isAnimated;
    private Animation<TextureRegion> animation;
    private TextureRegion textureRegion;
    private boolean looping;
    public int state;

    public AnimTextureRegion(Animation animation, int state, boolean looping) {
        this.isAnimated = true;
        this.animation = animation;
        this.looping = looping;
        this.state = state;
    }
    public AnimTextureRegion(Animation animation) {
        this.isAnimated = true;
        this.animation = animation;
        this.looping = true;
        this.state = 0;
    }
    public AnimTextureRegion(TextureRegion textureRegion) {
        this.isAnimated = false;
        this.textureRegion = textureRegion;
        this.looping = true;
        this.state = state;
    }

    public AnimTextureRegion(TextureRegion textureRegion, int state) {
        this.isAnimated = false;
        this.textureRegion = textureRegion;
        this.state = state;
    }

    public TextureRegion getTextureRegion() {
        return getTextureRegion(0);
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public TextureRegion getTextureRegion(float stateTime) {
        if (textureRegion != null) return textureRegion;
        return animation.getKeyFrame(stateTime, looping);
    }

    public boolean isOver(float stateTime) {
        if (animation != null) {
            return animation.isAnimationFinished(stateTime);
        }
        return true;
    }
}
