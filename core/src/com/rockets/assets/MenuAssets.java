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
    public NinePatch hudbg;
    public TextureRegion[] completion;
    public TextureRegion[] icons;
    public TextureRegion[] connectionStrength;
    public BackgroundViewAssetGroup bgs;
    public NinePatch[] gameButton;
    public NinePatch[] scrollbar;
    public NinePatch[] levelBar;
    public TextureRegion[][] levelButtons;
    public NinePatch[] toolbarBG;
    public TextureRegion bigPlaySymbol;
    public TextureRegion[] hearts;
    public TextureRegion[] menuIcons;

    public MenuAssets(AssetManager manager){
        final TextureAtlas atlas = manager.get(GameLoader.getAtlasFileName());

        NinePatch[][] buttons = cutNinesGroup2d(atlas,"buttons",18,12,4);
        btnGeneral = sliceNines(buttons,0,2);
        btnPrimary = sliceNines(buttons,1,2);
        this.levelButtons = cut(atlas,"levelButtons",38,52);

        TextureRegion[][] bgTiles = cut(atlas,"bgTiles",30,30);


        connectionStrength = cutLinear(atlas,"connectionstrength",7,7);
        completion = cutLinear(atlas,"completion",24,24);
        bgTilesHome = sliceRegions(bgTiles,0,2);

        gameButton = cutNinesGroup(atlas,"gamebutton",48,48,16,22,14);
        scrollbar = cutNinesGroup(atlas,"scrollbar",4,8,2);
        toolbarBG = cutNinesGroup(atlas,"toolbarbg",12,12,5);

        textfieldCursor = cutSingle(atlas,"textfieldCursor");
        bigPlaySymbol = cutSingle(atlas,"bigPlaySymbol");

        bgs = new BackgroundViewAssetGroup(
                cutNinesGroup2d(atlas,"bgninepatches",16,16,4),
                cutNinesGroup2d(atlas,"specialBackgrounds",24,24,8));

        levelBar = cutNinesGroup(atlas,"levelbar",24,12,3);

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                TextureRegion darkIcons = TextureColorer.tintTexture(atlas.findRegion("icons"), Color.valueOf(Colr.TEXT_MID));
                icons = flatten(darkIcons.split(12,12));
            }
        });
        hearts = cutLinear(atlas,"hearts",21,17);
        menuIcons = cutLinear(atlas,"menuicons",48,50);
        cutHud(atlas);
    }


    private void cutHud(TextureAtlas atlas) {

        hudbg = cutNinesGroup(atlas,"hudbg",16,16,5)[0];
    }


}
