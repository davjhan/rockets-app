package com.rockets.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * name: GraphicsFactory
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GraphicsFactory {
    private static Texture solidTexture(Color color){
        Pixmap pix = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(color);
        pix.fill();
        return new Texture(pix);
    }
    public static Drawable solidDrawable(Color color){
        return new TextureRegionDrawable(new TextureRegion(solidTexture(color)));
    }
    public static Image solidImage(Color color){
        return solidImage(1,1,color);
    }
    public static Image solidImage(int width,int height, Color color){
        return new Image(new TextureRegion(solidTexture(color),width,height));
    }

    public static Drawable solidNineDrawable(Color color) {
        return new NinePatchDrawable(new NinePatch(new TextureRegion(solidTexture(color))));
    }
}
