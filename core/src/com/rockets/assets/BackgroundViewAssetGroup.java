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
public class BackgroundViewAssetGroup extends ViewAssetGroup{

    public BackgroundViewAssetGroup(NinePatch[][] patches) {
        super(patches);
    }

    public NinePatchDrawable misc(int type) {
       return new NinePatchDrawable(patches[Catalog.Backgrounds.misc][type]);
    }
}
