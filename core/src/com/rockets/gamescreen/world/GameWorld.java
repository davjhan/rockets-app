package com.rockets.gamescreen.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.gamescreen.CameraShaker;
import com.rockets.gamescreen.Hud;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.CollisionManager;

/**
 * name: GameWorld
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class GameWorld implements IGameWorld, Disposable {
    protected IApp iApp;
    protected IGame game;
    protected Stage stage;
    protected GameGroup<Actor> background;
    protected GameGroup<Actor> bodies;
    protected GameGroup<Actor> markers;
    protected GameGroup<Actor> gameContainer;
    protected CollisionManager collisionManager;


    public GameWorld(IApp iApp, IGame game) {
        this.iApp = iApp;
        this.game = game;
        this.stage = game.stage();
        this.collisionManager = new CollisionManager(this);
    }

    public void init() {
        initGroups();
        initBG();
        initWalls();
        initHud();
        initInput();
    }

    protected void initInput() {
        stage.addListener(clickListener);
    }

    protected abstract void initHud();


    private void initGroups() {
        gameContainer = new GameGroup<>();
        background = new GameGroup<>();
        bodies = new GameGroup<>();
        markers = new GameGroup<>();
        gameContainer.addActor(background);
        gameContainer.addActor(bodies);
        gameContainer.addActor(markers);
        stage.addActor(gameContainer);
    }

    protected void initBG() {
    }

    private void initWalls() {

    }

    public void update(float delta) {

    }


    public void dispose() {
        iApp = null;
        game = null;

        stage.removeListener(clickListener);
        stage = null;
        collisionManager.dispose();
    }

    @Override
    public IGame game() {
        return game;
    }

    @Override
    public GameGroup<Actor> background() {
        return background;
    }

    @Override
    public GameGroup<Actor> bodies() {
        return bodies;
    }

    @Override
    public GameGroup<Actor> markers() {
        return markers;
    }

    @Override
    public CollisionManager collisionManager() {
        return collisionManager;
    }

    @Override
    public void ensureInBounds(Actor actor) {
        actor.setX(Math.max(Display.LEFT_PAD, actor.getX()));
        actor.setX(Math.min(Display.LEFT_PAD + Display.WORLD_WIDTH - actor.getWidth(), actor.getX()));
        actor.setY(Math.max(Display.BOT_PAD, actor.getY()));
        actor.setY(Math.min(Display.BOT_PAD + Display.WORLD_HEIGHT - actor.getHeight(), actor.getY()));
    }



    private ClickListener clickListener = new ClickListener(-1) {
        private long lastTouchTime;
        private Vector2 lastTouchPos;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (x < Display.WORLD_WIDTH + Display.LEFT_PAD) {

            }
            return super.touchDown(event, x, y, pointer, button);
        }


        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            return super.keyDown(event, keycode);
        }
    };



    protected abstract Hud getHud();

    @Override
    public void shakeScreen(int intensity) {
        Action action = CameraShaker.getShakeAction(intensity);
        gameContainer.addAction(action);
    }

    public void showOptionsMenu() {
        getHud().spawnOptionsModal();
    }
}