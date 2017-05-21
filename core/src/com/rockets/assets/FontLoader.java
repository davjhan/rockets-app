package com.rockets.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import static com.rockets.assets.Font.c1;
import static com.rockets.assets.Font.num1;
import static com.rockets.assets.Font.c2;
import static com.rockets.assets.Font.num2;
import static com.rockets.assets.Font.c3;
import static com.rockets.assets.Font.h1;
import static com.rockets.assets.Font.h2;
import static com.rockets.assets.Font.h3;
import static com.rockets.assets.Font.p1;
import static com.rockets.assets.Font.p2;
import static com.rockets.assets.Font.p3;

/**
 * name: FontLoader
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class FontLoader {
    private static String Born2bSportyV2 = "Born2bSportyV2.ttf";
    private static String SGK100 = "pixelmix.ttf";
    private static String SLKSCR = "slkscr.ttf";
    private static String SLKSCRB = "slkscrb.ttf";
    private static String VCR = "VCR_OSD_MONO_1.001.ttf";
    public static void queueLoader(AssetManager manager){

        manager.load(h1,BitmapFont.class,getLoaderParams(Born2bSportyV2,16));
        manager.load(h2,BitmapFont.class,getLoaderParams(Born2bSportyV2,16*2));
        manager.load(h3,BitmapFont.class,getLoaderParams(Born2bSportyV2,16*3));

        manager.load(p1,BitmapFont.class,getLoaderParams(SGK100,8));
        manager.load(p2,BitmapFont.class,getLoaderParams(SGK100,8*2));
        manager.load(p3,BitmapFont.class,getLoaderParams(SGK100,8*3));

        manager.load(c1,BitmapFont.class,width(getLoaderParams(SLKSCR,8),-1));
        manager.load(c2,BitmapFont.class,width(getLoaderParams(SLKSCR,8*2),-1));
        manager.load(c3,BitmapFont.class,width(getLoaderParams(SLKSCR,8*3),-1));

        manager.load(num1,BitmapFont.class,width(getLoaderParams(VCR,20),0));
        manager.load(num2,BitmapFont.class,width(getLoaderParams(VCR,20*2),0));
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter width(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, int width) {
        params.fontParameters.spaceX = width;
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter getLoaderParams(String filename, int size) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = "fonts/src/"+filename;
        params.fontParameters.size = size;
        params.fontParameters.hinting = FreeTypeFontGenerator.Hinting.AutoFull;
        return params;
    }
}
