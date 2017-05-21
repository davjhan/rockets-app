package com.rockets.assets;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * name: AssetLoader
 * desc:
 * date: 2016-05-12
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TexturePackerManager {
    public static final String INPUT_DIR = "../preassets/";
    public static final String OUTPUT_DIR = "./";
    public static final String PACK_FILENAME = "packed_assets";
    public static TexturePacker.Settings settings;
    public static void packTextures(){

        settings = new TexturePacker.Settings();
        settings.silent = false;
        TexturePacker.processIfModified(settings,INPUT_DIR+"default/", OUTPUT_DIR, "default_"+PACK_FILENAME);

    }
}
