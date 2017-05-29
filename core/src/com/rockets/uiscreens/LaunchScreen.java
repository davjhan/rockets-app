package com.rockets.uiscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.rockets.assets.GameLoader;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.common.IAppInitializer;
import com.rockets.data.ContentDB;
import com.rockets.utils.GraphicsFactory;

/**
 * name: LaunchScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class LaunchScreen extends BaseUIScreen{
    private GameLoader gameLoader;
    private boolean doneLoadingFromFile;

    public LaunchScreen(IApp game) {
        super(game,null);
        doneLoadingFromFile = false;
        gameLoader = new GameLoader();
        Texture.setAssetManager(gameLoader);
        initGraphics();
    }

    @Override
    public void update(float delta) {
        if(gameLoader.update() && !doneLoadingFromFile) {
            // DONE LOADING.
            finishLoadInBackground();
            doneLoadingFromFile = true;
        }
    }

    private void initModels(IApp app){
        FileHandle challengesFH = Gdx.files.internal("data/challenges.json");
        ((IAppInitializer) app).setContentDB(new ContentDB(challengesFH.readString()));
    }
    private void initGraphics() {
        Drawable bg = GraphicsFactory.solidDrawable(Color.YELLOW);
        rootTable.background(bg);

    }

    private void finishLoadInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoader.initAssets((IAppInitializer)app);
                initModels(app);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        onLoadDone();
                    }
                });
            }
        }).start();
    }

    private void onLoadDone() {
//        app.screenManager().pushScreen(GameScreen.class,GameScreen.getChallengeExtras("coin_1"));
        app.screenManager().pushScreen(HomeScreen.class);
    }
}
