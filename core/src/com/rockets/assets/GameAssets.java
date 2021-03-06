package com.rockets.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rockets.constants.Display;
import com.rockets.utils.TextureColorer;

import java.util.HashMap;
import java.util.Map;

/**
 * name: res
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameAssets extends AssetGroup {
    public Array<Array<Array<TextureRegion>>> playerSkins;
    public TextureRegion[] coin;
    public TextureRegion[] coinLarge;
    public TextureRegion upArrow;
    public TextureRegion[] specialObjects;
    public Array<TextureRegion> sparkles;
    public Array<TextureRegion> glisten;

    public NinePatch namePlate;
    public NinePatch clockFrame;
    public Array<Array<TextureRegion>> crosshair;
    public Texture whiteTexture;
    public Texture normalTexture;

    private Map<Texture, Texture> whiteTexturesCache = new HashMap<>();
    public TextureRegion bg;

    public GameAssets(AssetManager manager) {
        final TextureAtlas atlas = manager.get(GameLoader.getAtlasFileName());
        coin = cutLinear(atlas, "coin", Display.UNIT, Display.UNIT);
        coinLarge = cutLinear(atlas, "coinLarge", 58, 58);
        specialObjects = cutLinear(atlas, "specialObjects", 50, 50);
        namePlate = cutNinesGroup(atlas, "nameplate", 12, 8, 3)[0];
        clockFrame = cutNinesGroup(atlas, "clockframe", 30, 16, 4)[0];
        upArrow = cutSingle(atlas, "upArrow");
        bg = cutSingle(atlas, "bg");
        //crosshair = getKeyFrames(cut(atlas,"crosshair",38,38),0,1,3);


        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                final double timeElapsed = System.currentTimeMillis();
                for (Texture t : atlas.getTextures()) {
                    cacheWhiteTexture(t);
                }
            }
        });

        cutPlayerSkins(atlas);
        cutParticles(atlas);
        cutVfx(atlas);
    }


    private void cacheWhiteTexture(Texture t) {
        whiteTexturesCache.put(t, TextureColorer.getWhiteGameTexture(t));
    }

    private void cutVfx(TextureAtlas atlas) {
        TextureRegion[][] vfx = cut(atlas, "vfx", 42, 42);
        glisten = getKeyFrames(vfx, 1, 0, 4);
    }

    private void cutPlayerSkins(TextureAtlas atlas) {
        TextureRegion[][] stills = cut(atlas, "playerSkins", 50, 50);
        Array<Array<Array<TextureRegion>>> frames = new Array<>();
        for(int i = 0; i < stills.length; i++){
            Array<Array<TextureRegion>>playerSkin = new Array<>();
            playerSkin.add(getKeyFrames(stills, i, 0, 2));
            playerSkin.add(getKeyFrames(stills, i, 2, 2));
            frames.add(playerSkin);
        }
        playerSkins = frames;
    }

    private void cutParticles(TextureAtlas atlas) {
        TextureRegion[][] sparklesPage = cut(atlas, "sparkles", 16, 16);
        sparkles = getKeyFrames(sparklesPage, 0, 0, 4);
    }


    public Texture getWhiteTexture(Texture texture) {
        return whiteTexturesCache.get(texture);
    }
}
