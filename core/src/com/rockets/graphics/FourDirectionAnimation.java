package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * name: FourDirectionAnimation
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class FourDirectionAnimation extends Animation<TextureRegion>  {
    protected List<TextureRegion[]> frameSets;
    private int facing;

    public FourDirectionAnimation(float frameDuration,
                                  Array<? extends TextureRegion> down,
                                  Array<? extends TextureRegion> up,
                                  Array<? extends TextureRegion> right) {
        this(frameDuration,up,down,right,PlayMode.LOOP,false);
    }
    public FourDirectionAnimation(float frameDuration,
                                  Array<? extends TextureRegion> down,
                                  Array<? extends TextureRegion> up,
                                  Array<? extends TextureRegion> right, PlayMode playMode
                                  ) {
        this(frameDuration,up,down,right,PlayMode.LOOP,false);
    }

    public FourDirectionAnimation(float frameDuration,

                                  Array<? extends TextureRegion> down,
                                  Array<? extends TextureRegion> up,
                                  Array<? extends TextureRegion> right, PlayMode playMode,
                                  boolean forceNew) {
        super(frameDuration);
        setPlayMode(playMode);
        frameSets = new ArrayList<>();

        TextureRegion[] upFrames = new TextureRegion[up.size];
        for (int i = 0, n = up.size; i < n; i++) {
            if(forceNew) {
                upFrames[i] = new TextureRegion(up.get(i));
            }else{
                upFrames[i] = up.get(i);
            }
        }


        TextureRegion[] downFrames = new TextureRegion[down.size];
        for (int i = 0, n = down.size; i < n; i++) {
            if(forceNew){
                downFrames[i] = new TextureRegion(down.get(i));
            }else{
                downFrames[i] = down.get(i);
            }

        }

        TextureRegion[] rightFrames = new TextureRegion[right.size];
        for (int i = 0, n = right.size; i < n; i++) {
            if(forceNew){

                rightFrames[i] = new TextureRegion(right.get(i));
            }else{
                rightFrames[i] = right.get(i);
            }
        }

        TextureRegion[] leftFrames = new TextureRegion[right.size];
        TextureRegion reg;
        for (int i = 0, n = right.size; i < n; i++) {
            reg = new TextureRegion(right.get(i));
            reg.flip(true,false);
            leftFrames[i] = reg;
        }

        frameSets.add(com.rockets.gamescreen.world.Facing.DOWN,downFrames);
        frameSets.add(com.rockets.gamescreen.world.Facing.UP,upFrames);
        frameSets.add(com.rockets.gamescreen.world.Facing.RIGHT,rightFrames);
        frameSets.add(com.rockets.gamescreen.world.Facing.LEFT,leftFrames);

        setFacing(com.rockets.gamescreen.world.Facing.DOWN);
    }

    public void setFacing(int facing) {
        this.facing = facing;
        setKeyFrames(frameSets.get(facing));
    }

    public int getFacing() {
        return facing;
    }

    public List<TextureRegion[]> getFrameSets() {
        return frameSets;
    }

}
