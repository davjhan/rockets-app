package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * name: FourFacingFramesGroup
 * desc:
 * date: 2017-01-05
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class FourFacingFramesGroup {
    public final String id;
    public final Array<TextureRegion> up;
    public final Array<TextureRegion> down;
    public final Array<TextureRegion> right;

    public FourFacingFramesGroup(
            String id,
            Array<TextureRegion> down,
            Array<TextureRegion> up,
            Array<TextureRegion> right){

        this.id = id;
        this.up = up;
        this.down = down;
        this.right = right;
    }
}
