package com.rockets.uiscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rockets.assets.GameLoader;
import com.rockets.common.BaseUIScreen;
import com.rockets.common.IApp;
import com.rockets.common.IAppInitializer;
import com.rockets.constants.Display;
import com.rockets.data.ContentDB;
import com.rockets.utils.GraphicsFactory;

import de.tomgrill.gdxfacebook.core.GDXFacebook;
import de.tomgrill.gdxfacebook.core.GDXFacebookConfig;
import de.tomgrill.gdxfacebook.core.GDXFacebookSystem;

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
        initData();
    }

    private void initData() {
        GDXFacebookConfig config = new GDXFacebookConfig();
        config.APP_ID = "235441680306375"; // required
        config.PREF_FILENAME = ".facebookSessionData"; // optional
        config.GRAPH_API_VERSION = "v2.6"; // optional, default is v2.6
        GDXFacebook gdxFacebook = GDXFacebookSystem.install(config);

        if(gdxFacebook.isSignedIn()){
            Gdx.app.log("tttt LaunchScreen", "facebook: signed in");
        }
        gdxFacebook.signOut();
    }

    @Override
    protected Viewport getViewPort() {
        float ratio = (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
        float sixteenby9 = 9f/16f;
        if(ratio ==sixteenby9){
            Display.SCREEN_WIDTH = Display.CONTENT_WIDTH;
            Display.SCREEN_HEIGHT = Display.CONTENT_HEIGHT;
        }else if(ratio < sixteenby9){
            Display.SCREEN_WIDTH = Display.CONTENT_WIDTH;
            Display.SCREEN_HEIGHT = Display.CONTENT_WIDTH/ratio;
        }else{
            Display.SCREEN_HEIGHT = Display.CONTENT_HEIGHT;
            Display.SCREEN_WIDTH = Display.CONTENT_HEIGHT*ratio;
        }
        Display.SCREEN_WIDTH_HALF = Display.SCREEN_WIDTH/2;
        Display.SCREEN_HEIGHT_HALF = Display.SCREEN_HEIGHT/2;
        Display.CONTENT_LEFTPAD = (Display.SCREEN_WIDTH - Display.CONTENT_WIDTH)/2;
        Display.CONTENT_BOTPAD = (Display.SCREEN_HEIGHT - Display.CONTENT_HEIGHT)/2;

        Gdx.app.log("tttt LaunchScreen", "WIDTH: " +Display.SCREEN_WIDTH+ " HEIGHT"+Display.SCREEN_HEIGHT);
        return super.getViewPort();
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
        FileHandle skinsFH = Gdx.files.internal("data/skins.json");
        ((IAppInitializer) app).setContentDB(new ContentDB(challengesFH.readString(),skinsFH.readString()));
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
