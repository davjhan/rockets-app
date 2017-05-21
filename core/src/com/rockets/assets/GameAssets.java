package com.rockets.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rockets.constants.Display;
import com.rockets.graphics.FourFacingFramesGroup;
import com.rockets.utils.TextureColorer;

import java.util.Arrays;
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

    public Array<TextureRegion> minion_down_idle;
    public Array<TextureRegion> minion_up_idle;
    public Array<TextureRegion> minion_right_idle;

    public TextureRegion youTag;
    public TextureRegion[][] walls;
    public TextureRegion[] floor01;
    public TextureRegion[] shadow;
    public TextureRegion[] crates;
    public TextureRegion[] fruit;
    public Array<TextureRegion> sparkles;
    public Array<TextureRegion> glisten;

    public NinePatch namePlate;
    public NinePatch clockFrame;
    public Array<TextureRegion> crosshair;
    public Texture whiteTexture;
    public Texture normalTexture;

    public GameAssets(AssetManager manager){
        TextureAtlas atlas = manager.get(GameLoader.getAtlasFileName());
        walls = cut(atlas,"wall",Display.UNIT,Display.UNIT);
        TextureRegion[] floors = cutLinear(atlas,"floor",Display.UNIT,Display.UNIT);
        floor01 = Arrays.copyOfRange(floors,0,4);
        shadow = cutLinear(atlas,"shadow",Display.UNIT,Display.UNIT);
        crates = cutLinear(atlas,"crates",Display.UNIT,54);
        namePlate = cutNinesGroup(atlas,"nameplate",12,8,3)[0];
        clockFrame = cutNinesGroup(atlas,"clockframe",30,16,4)[0];
        crosshair = getKeyFrames(cut(atlas,"crosshair",38,38),0,1,3);
        youTag = cutSingle(atlas,"youtag");
        fruit = cutLinear(atlas,"fruit",42,42);
        normalTexture = fruit[0].getTexture();

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                whiteTexture = TextureColorer.getWhiteGameTexture(fruit[0].getTexture());
            }
        });
        cutMinion(atlas);

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
        String[] names = new String[]{"bird","pig","frog"};
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

    private void cutMinion(TextureAtlas atlas) {
        TextureRegion[][] minion = cut(atlas,"minion", Display.UNIT,Display.UNIT);
        minion_down_idle = getKeyFrames(minion,0,0,3);
        minion_up_idle = getKeyFrames(minion,2,0,3);
        minion_right_idle = getKeyFrames(minion,1,0,3);
    }
}
