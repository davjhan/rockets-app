package com.rockets.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rockets.constants.Display;
import com.rockets.graphics.FourFacingFramesGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * name: res
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameAssets extends AssetGroup{
    public Map<String,FourFacingFramesGroup> animals;
    public TextureRegion[] coin;
    public TextureRegion[] specialObjects;
    public Array<TextureRegion> sparkles;
    public Array<TextureRegion> glisten;

    public NinePatch namePlate;
    public NinePatch clockFrame;
    public Array<TextureRegion> crosshair;
    public Texture whiteTexture;
    public Texture normalTexture;

    public GameAssets(AssetManager manager){
        TextureAtlas atlas = manager.get(GameLoader.getAtlasFileName());
        coin = cutLinear(atlas,"coin",Display.UNIT,Display.UNIT);
        specialObjects = cutLinear(atlas,"specialObjects",50,50);
        namePlate = cutNinesGroup(atlas,"nameplate",12,8,3)[0];
        clockFrame = cutNinesGroup(atlas,"clockframe",30,16,4)[0];
        crosshair = getKeyFrames(cut(atlas,"crosshair",38,38),0,1,3);

//        Gdx.app.postRunnable(new Runnable() {
//            @Override
//            public void run() {
////                whiteTexture = TextureColorer.getWhiteGameTexture(fruit[0].getTexture());
//            }
//        });

        cutAnimals(atlas);
        cutParticles(atlas);
        cutVfx(atlas);
    }

    private void cutVfx(TextureAtlas atlas) {
       TextureRegion[][] vfx = cut(atlas,"vfx",42,42);
        glisten = getKeyFrames(vfx,1,0,4);
    }

    private void cutAnimals(TextureAtlas atlas) {
        animals = new HashMap<>();
        String[] names = new String[]{"bird"};
        for(String name:names){
            TextureRegion[][] allFrames = cut(atlas,name, Display.UNIT,Display.UNIT);
            animals.put(name,new FourFacingFramesGroup(
                    name,
                    getKeyFrames(allFrames,0,0,3),
            getKeyFrames(allFrames,2,0,3),
            getKeyFrames(allFrames,1,0,3)
            ));
        }
    }

    private void cutParticles(TextureAtlas atlas) {
        TextureRegion[][] sparklesPage = cut(atlas,"sparkles",16,16);
        sparkles = getKeyFrames(sparklesPage,0,0,4);
    }
}
