package com.rockets.assets;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import static com.rockets.assets.Font.c1;
import static com.rockets.assets.Font.c2;
import static com.rockets.assets.Font.c3;
import static com.rockets.assets.Font.grand1;
import static com.rockets.assets.Font.grand2;
import static com.rockets.assets.Font.grand3;
import static com.rockets.assets.Font.h1;
import static com.rockets.assets.Font.h2;
import static com.rockets.assets.Font.h3;
import static com.rockets.assets.Font.num1;
import static com.rockets.assets.Font.num2;
import static com.rockets.assets.Font.outlined1;
import static com.rockets.assets.Font.outlined2;
import static com.rockets.assets.Font.outlined3;
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
    private static String SLKSCRB = "slkscrb.ttf";

    public static void queueLoader(AssetManager manager){

        String born2bSportyV2 = "Born2bSportyV2.ttf";
        manager.load(h1,BitmapFont.class,getLoaderParams(born2bSportyV2,16));
        manager.load(h2,BitmapFont.class,getLoaderParams(born2bSportyV2,16));
        manager.load(h3,BitmapFont.class,getLoaderParams(born2bSportyV2,16*2));

        manager.load(outlined1,BitmapFont.class, makeOutlined(getLoaderParams(born2bSportyV2,16),1));
        manager.load(outlined2,BitmapFont.class, makeOutlined(getLoaderParams(born2bSportyV2,16),1.5f));
        manager.load(outlined3,BitmapFont.class, makeOutlined(getLoaderParams(born2bSportyV2,16*2),2));

        manager.load(grand1,BitmapFont.class,makeGrand(getLoaderParams(born2bSportyV2,16),1));
        manager.load(grand2,BitmapFont.class,makeGrand(getLoaderParams(born2bSportyV2,16),1.5f));
        manager.load(grand3,BitmapFont.class,makeGrand(getLoaderParams(born2bSportyV2,16),2));

        String SGK100 = "pixelmix.ttf";
        manager.load(p1,BitmapFont.class,getLoaderParams(SGK100,8));
        manager.load(p2,BitmapFont.class,getLoaderParams(SGK100,8));
        manager.load(p3,BitmapFont.class,getLoaderParams(SGK100,8*2));

        String SLKSCR = "slkscr.ttf";
        manager.load(c1,BitmapFont.class,width(getLoaderParams(SLKSCR,8),-1));
        manager.load(c2,BitmapFont.class,width(getLoaderParams(SLKSCR,8),-1));
        manager.load(c3,BitmapFont.class,width(getLoaderParams(SLKSCR,8*2),-1));

        String VCR = "VCR_OSD_MONO_1.001.ttf";
        manager.load(num1,BitmapFont.class,width(getLoaderParams(VCR,20),0));
        manager.load(num2,BitmapFont.class,width(getLoaderParams(VCR,20*2),0));

    }

    private static AssetLoaderParameters<BitmapFont> makeOutlined(FreetypeFontLoader.FreeTypeFontLoaderParameter params,
                                                                  float scale) {
        params.fontParameters.borderWidth = 1/scale;
        params.fontParameters.borderColor = Color.valueOf(Colr.TEXT_DARK);
        params.fontParameters.color = Color.valueOf(Colr.TEXT_LIGHT);
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter width(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, int width) {
        params.fontParameters.spaceX = width;
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter makeGrand(
            FreetypeFontLoader.FreeTypeFontLoaderParameter params, float scale) {
        params.fontParameters.borderWidth = 1/scale;
        params.fontParameters.color = Color.valueOf(Colr.TEXT_MID);
        params.fontParameters.borderColor = Color.valueOf(Colr.TEXT_DARK);
        params.fontParameters.shadowOffsetY = 1;
        params.fontParameters.shadowColor = Color.valueOf("178ce1");
        return params;
    }

    private static FreetypeFontLoader.FreeTypeFontLoaderParameter getLoaderParams(String filename, int size) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = "fonts/src/"+filename;
        params.fontParameters.size = size;
        params.fontParameters.mono = true;
        params.fontParameters.hinting = FreeTypeFontGenerator.Hinting.AutoFull;
        return params;
    }
}
