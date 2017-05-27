package com.rockets.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rockets.assets.GameAssets;
import com.rockets.assets.MenuAssets;
import com.rockets.common.BaseScreen;
import com.rockets.common.IApp;
import com.rockets.gamescreen.singleplayer.ChallengeGameWorld;
import com.rockets.gamescreen.world.GameWorld;
import com.rockets.modal.ErrorModal;

import java.util.HashMap;
import java.util.Map;

/**
 * name: LaunchScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameScreen extends BaseScreen implements IGame {
    public Table hud;
    public GameWorld gameWorld;
    private int debugMode;
    private boolean crashed = false;
    public static final String CHALLENGE_ID = "challengeId";
    public GameScreen(IApp app, Map<String,Object> extras) {
        super(app,extras);
        gameWorld = new ChallengeGameWorld(app,this,(String)extras.get(CHALLENGE_ID));
        init();
    }

    private void init() {
        initHUD();
        initBG();
    }

    @Override
    public void show() {
        super.show();
        gameWorld.init();
        init();
    }

    private void initHUD() {
        hud = new Table();
        hud.setFillParent(true);
        stage.addActor(hud);
    }

    private void initBG() {

    }


    @Override
    public void update(float delta) {
        gameWorld.update(delta);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
        super.dispose();
    }

    @Override
    public Stage stage() {
        return stage;
    }

    @Override
    public IApp iApp() {
        return app;
    }

    @Override
    public GameWorld world() {
        return gameWorld;
    }

    @Override
    public GameAssets gameAssets() {
        return app.gameAssets();
    }

    @Override
    public MenuAssets menuAssets() {
        return app.menuAssets();
    }

    @Override
    public void crash(final Exception e) {
        e.printStackTrace();
        Gdx.app.error("tttt GameScreen", "ERROR: " +e.toString());
        if(!crashed) {
            crashed = true;
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    quit();
                    app.showModal(new ErrorModal(app,e.toString()));
                }
            });
        }
    }

    @Override
    public void quit() {
        app.screenManager().popScreen();
    }

    @Override
    public void toggleDebug() {
        debugMode++;
        if(debugMode == 3){
            debugMode = 0;
        }
        switch (debugMode) {
            case 0:

                stage.setDebugAll(false);
                break;
            case 1:
                stage.setDebugAll(false);
                gameWorld.bodies().debugAll();
                break;
            case 2:
                stage.setDebugAll(true);
                break;
        }
    }

    public boolean onBackPressed() {
        app.screenManager().pushScreen(
                GameScreen.class,GameScreen.getChallengeExtras("coin_1")
        );
        return true;
    }

    public static  Map<String,Object>  getChallengeExtras(String challengeId) {
        Map<String,Object> extras = new HashMap<>();
        extras.put(CHALLENGE_ID,challengeId);
        return extras;
    }
}
