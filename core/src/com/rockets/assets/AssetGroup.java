package com.rockets.assets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * name: AssetGroup
 * desc:
 * date: 2016-05-20
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class AssetGroup {
    public TextureRegion cutSingle(TextureAtlas atlas, String regionName){
        return atlas.findRegion(regionName);
    }
    public TextureRegion[][] cut(TextureAtlas atlas, String regionName, int width, int height){
        return atlas.findRegion(regionName).split(width,height);
    }

    protected TextureRegion[] flatten(TextureRegion[][] regs) {
        int len = regs[0].length*regs.length;
        TextureRegion[] ret = new TextureRegion[len];
        for(int i = 0; i < len; i ++){
            ret[i] = regs[i/regs[0].length][i%regs[0].length];
        }
        return ret;
    }


    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], (width/2)-1, (width/2)-1, (height/2)-1, (height/2)-1);
                count ++;
            }
        }
        return ret;
    }
    public NinePatchDrawable[] getDrawables(NinePatch[] patches){
        NinePatchDrawable[] drawables = new NinePatchDrawable[patches.length];
        for (int i = 0; i < patches.length; i++) {
            drawables[i] = new NinePatchDrawable(patches[i]);
        }
        return drawables;
    }
    public NinePatch[][] cutNinesGroup2d(TextureAtlas atlas, String regionName, int width, int height
            ,int cornerSize){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[][] ret = new NinePatch[regs.length][regs[0].length];
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[r][c] = new NinePatch(regs[r][c], cornerSize,cornerSize,cornerSize,cornerSize);
            }
        }
        return ret;
    }
    public NinePatch[][] cutNinesGroup2d(TextureAtlas atlas, String regionName, int width, int height
            ,int sides,int top,int bottom){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[][] ret = new NinePatch[regs.length][regs[0].length];
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[r][c] = new NinePatch(regs[r][c], sides,sides,top,bottom);
            }
        }
        return ret;
    }
    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height
            ,int cornerSize){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], cornerSize,cornerSize,cornerSize,cornerSize);
                count ++;
            }
        }
        return ret;
    }
    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height
            ,int sides,int top,int bottom){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], sides,sides,top,bottom);
                count ++;
            }
        }
        return ret;
    }
    public TextureRegionDrawable[] getDrawables(TextureRegion[] regions){
        TextureRegionDrawable[] drawables = new TextureRegionDrawable[regions.length];
        for (int i = 0; i < regions.length; i++) {
            drawables[i] = new TextureRegionDrawable(regions[i]);
        }
        return drawables;
    }
    public TextureRegion[] cutLinear(TextureAtlas atlas, String regionName,int width, int height) {
        TextureRegion[][] regs = cut(atlas,regionName,width,height);
        int len = regs[0].length*regs.length;
        TextureRegion[] ret = new TextureRegion[len];
        for(int i = 0; i < len; i ++){
            ret[i] = regs[i/regs[0].length][i%regs[0].length];
        }
        return ret;
    }

    public NinePatch[] sliceNines(NinePatch[][] nines2d, int row, int length) {
        NinePatch[] ret = new NinePatch[length];
        for(int i = 0; i < length; i ++){
            ret[i] = nines2d[row][i];
        }
        return ret;
    }

    public TextureRegion[] sliceRegions(TextureRegion[][] nines2d, int row, int length) {
        TextureRegion[] ret = new TextureRegion[length];
        for(int i = 0; i < length; i ++){
            ret[i] = nines2d[row][i];
        }
        return ret;
    }
    public Array<TextureRegion> getKeyFrames(TextureRegion[][] regs, int row, int col, int length) {
        Array<TextureRegion> frames = new Array<>(length);
        for(int r = row; r < regs.length; r ++) {
            for(int c = col; c < regs[0].length; c ++) {
                if(length > 0){
                    frames.add(regs[r][c]);
                    length --;
                }else{
                    break;
                }

            }
        }
        return frames;
    }
}
