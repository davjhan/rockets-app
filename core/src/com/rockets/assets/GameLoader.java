package com.rockets.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.rockets.common.IAppInitializer;

/**
 * name: GameLoader
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameLoader extends AssetManager {
    private static final String ATLAS = "packed_assets.atlas";
    private static final String I18N = "strings/strings";

    public GameLoader(){
        setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(getFileHandleResolver()));
        setLoader(BitmapFont.class, ".ttf",
                new FreetypeFontLoader(getFileHandleResolver()));
        FontLoader.queueLoader(this);

        load(getAtlasFileName(),TextureAtlas.class);
        load(I18N, I18NBundle.class);
    }

    public void initAssets(IAppInitializer gameInitializer){

        MenuAssets menuAssets = new MenuAssets(this);
        GameAssets gameAssets = new GameAssets(this);
        VisUILoader.loadVisUI(this,menuAssets);
        I18NBundle strings = get(I18N,I18NBundle.class);

        gameInitializer.setMenuAssets(menuAssets);
        gameInitializer.setGameAssets(gameAssets);
        gameInitializer.setStrings(strings);

    }

    public static String getAtlasFileName(){
        return "default_"+ATLAS;
    }

}
