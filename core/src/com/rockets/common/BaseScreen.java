package com.rockets.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rockets.Rockets;
import com.rockets.constants.Display;
import com.rockets.graphics.DisposableList;

import java.util.Map;

/**
 * name: BaseScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class BaseScreen implements Screen {
    protected IApp app;
    public Stage stage;
    protected DisposableList<Disposable> disposables = new DisposableList<>();

    public BaseScreen(IApp app,Map<String,Object> extras){
        this.app = app;
        Viewport vp = getViewPort();
        stage = new Stage(vp);

    }

    public abstract void update(float delta);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        disposables.dispose();
        Gdx.app.log("tttt BaseScreen", "Disposing Screen: "+this.getClass().getName());
    }

    public void toggleDebug() {
        stage.setDebugAll(Rockets.DEBUG);
    }

    public boolean onBackPressed() {
        Gdx.app.log("tttt BaseScreen", "app is null: " + (app == null));
        app.screenManager().popScreen();
        return true;
    }

    protected Viewport getViewPort() {
        return  new FitViewport(Display.SCREEN_WIDTH,Display.SCREEN_HEIGHT);
    }
}
