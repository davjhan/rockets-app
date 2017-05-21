package com.rockets.assets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * name: CardBg
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ViewAssetGroup {


    protected final NinePatch[][] patches;

    public ViewAssetGroup(NinePatch[][] patches){
        this.patches = patches;
    }

    public NinePatchDrawable get(int type,int state){
        return new NinePatchDrawable(patches[type][state]);
    }
    public NinePatchDrawable get(int type){

        return new NinePatchDrawable(patches[type][Catalog.normal]);
    }
    public NinePatchDrawable[] getGroup(int type){
        NinePatchDrawable[] ret = new NinePatchDrawable[4];
        for(int i = 0; i < ret.length; i ++){
            ret[i] = new NinePatchDrawable(patches[type][i]);
        }
        return ret;
    }
}
