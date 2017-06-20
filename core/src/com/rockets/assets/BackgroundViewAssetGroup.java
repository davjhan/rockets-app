package com.rockets.assets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * name: CardBg
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class BackgroundViewAssetGroup{
    NinePatch[][] reg;
    NinePatch[][] special;
    public BackgroundViewAssetGroup(NinePatch[][] reg, NinePatch[][] special) {
        super();
        this.reg = reg;
        this.special = special;
    }

    public NinePatchDrawable getModalBg() {
        return getSpecial(0,0);
    }
    public NinePatchDrawable getInlay() {
        return getReg(0,0);
    }
    public NinePatchDrawable getGoldFrameBg() {
        return getSpecial(1,0);
    }
    public NinePatchDrawable getWhiteFrameBg() {
        return getSpecial(0,2);
    }
    public NinePatchDrawable getCoinDisplayBgGold() {
        return getSpecial(1,1);
    }

    private NinePatchDrawable getReg(int type,int state){
        return new NinePatchDrawable(reg[type][state]);
    }
    private NinePatchDrawable getReg(int type){

        return new NinePatchDrawable(reg[type][Catalog.normal]);
    }

    private NinePatchDrawable getSpecial(int type, int state) {
        return new NinePatchDrawable(special[type][state]);
    }
    private NinePatchDrawable getSpecial(int type){

        return new NinePatchDrawable(special[type][Catalog.normal]);
    }

    public Drawable getFrameBg() {
        return getSpecial(0,0);
    }

    public NinePatchDrawable getWhiteNametag() {
        return getReg(0,2);
    }

    public Drawable getStartButtonBg() {
        return getSpecial(0,1);
    }

    public NinePatchDrawable getGoldNameTag() {
        return getReg(0,3);
    }

    public Drawable getLevelHeaderBg() {
         return getReg(1,0);
    }
}
