package com.rockets.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rockets.utils.TextureColorer;

/**
 * name: res
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class MenuAssets extends AssetGroup{
    public NinePatch[]  btnGeneral;
    public NinePatch[]  btnPrimary;
    public TextureRegion[] bgTilesHome;
    public TextureRegion textfieldCursor;
    public NinePatch[][] hudbgs;
    public TextureRegion[] icons;
    public TextureRegion[] connectionStrength;
    public ViewAssetGroup cardBg;
    public BackgroundViewAssetGroup bgs;
    public NinePatch[] gameButton;
    public NinePatch[] scrollbar;
    public NinePatch[] levelBar;
    public NinePatch[] toolbarBG;
    public TextureRegion[] hearts;
    public TextureRegion[] menuIcons;

    public MenuAssets(AssetManager manager){
        final TextureAtlas atlas = manager.get(GameLoader.getAtlasFileName());

        NinePatch[][] buttons = cutNinesGroup2d(atlas,"button",31,21,6);
        btnGeneral = sliceNines(buttons,1,2);
        btnPrimary = sliceNines(buttons,0,2);

        TextureRegion[][] bgTiles = cut(atlas,"bgTiles",30,30);


        connectionStrength = cutLinear(atlas,"connectionstrength",7,7);
        bgTilesHome = sliceRegions(bgTiles,0,2);

        gameButton = cutNinesGroup(atlas,"gamebutton",48,48,16,22,14);
        scrollbar = cutNinesGroup(atlas,"scrollbar",4,8,2);
        toolbarBG = cutNinesGroup(atlas,"toolbarbg",12,12,5);

        textfieldCursor = cutSingle(atlas,"textfieldCursor");

        cardBg = new ViewAssetGroup(cutNinesGroup2d(atlas,"cardbg",32,32,6,16,6));
        bgs = new BackgroundViewAssetGroup(cutNinesGroup2d(atlas,"backgrounds",16,16,5));

        levelBar = cutNinesGroup(atlas,"levelbar",24,12,3);

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                TextureRegion darkIcons = TextureColorer.tintTexture(atlas.findRegion("icons"), Color.valueOf(Colr.TEXT_DARK));
                icons = flatten(darkIcons.split(12,12));
            }
        });
        hearts = cutLinear(atlas,"hearts",21,17);
        menuIcons = cutLinear(atlas,"menuicons",48,50);
        cutHud(atlas);
    }


    private void cutHud(TextureAtlas atlas) {

        hudbgs = cutNinesGroup2d(atlas,"hudbgs",16,16,6);
    }


}
