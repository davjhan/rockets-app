package com.rockets.uiscreens.uniqueviews;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.common.IApp;
import com.rockets.data.readonly.SkinModel;
import com.rockets.uiscreens.views.Selectable;

/**
 * name: SkinDisplayItem
 * desc:
 * date: 2017-06-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SkinDisplayItem extends Table implements Disposable,Selectable{
    private IApp app;
    private SkinModel skinModel;
    public SkinDisplayItem(IApp app,SkinModel skinModel){
        this.app = app;
        this.skinModel = skinModel;
        initView();

    }

    private void initView() {
        setBackground(app.menuAssets().bgs.getFrameBg());
    }

    @Override
    public float getPrefWidth() {
        return 64;
    }



    @Override
    public float getMinHeight() {
        return 64;
    }

    @Override
    public void dispose() {
        this.app = null;
        this.skinModel = null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public void setSelected(boolean selected) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public boolean isSelectable() {
        return false;
    }
}
