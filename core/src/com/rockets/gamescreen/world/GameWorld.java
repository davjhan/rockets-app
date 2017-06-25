package com.rockets.gamescreen.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.hud.Hud;
import com.rockets.gamescreen.modals.PauseModal;
import com.rockets.gamescreen.physics.CollisionManager;
import com.rockets.gamescreen.physics.Side;
import com.rockets.graphics.ActionFactory;
import com.rockets.modal.Modal;
import com.rockets.modal.ModalListener;
import com.rockets.uiscreens.HomeScreen;
import com.rockets.uiscreens.modals.SettingsModal;
import com.rockets.utils.GraphicsFactory;

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
    private String state;
    Actor solid;
    protected GameGroup<Actor> background;
    protected GameGroup<Actor> bodies;
    protected GameGroup<Actor> markers;
    protected GameGroup<Actor> overtop;
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

        initExtraSpaceBg();
        initHud();
        initInput();

    }

    protected void initExtraSpaceBg() {
        if (Display.CONTENT_BOTPAD > 0) {
            Image bottomBlocker = GraphicsFactory.solidImage(Display.SCREEN_WIDTH,Display.CONTENT_BOTPAD+1, Color.BLACK);
            Image topBlocker = GraphicsFactory.solidImage(Display.SCREEN_WIDTH, Display.CONTENT_BOTPAD+1, Color.BLACK);
            overtop.addActor(bottomBlocker);
            overtop.addActor(topBlocker);
            topBlocker.setPosition(0, Display.SCREEN_HEIGHT-Display.CONTENT_BOTPAD, Align.topLeft);
            bottomBlocker.setPosition(0, -Display.CONTENT_BOTPAD);
        }
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
        overtop = new GameGroup<>();
        gameContainer.addActor(background);
        gameContainer.addActor(bodies);
        gameContainer.addActor(markers);
        gameContainer.setPosition(Display.CONTENT_LEFTPAD, Display.CONTENT_BOTPAD);
        overtop.setPosition(Display.CONTENT_LEFTPAD, Display.CONTENT_BOTPAD);
        stage.addActor(gameContainer);
        stage.addActor(overtop);
    }

    protected void initBG() {
        solid = GraphicsFactory.solidImage(Display.CONTENT_WIDTH, Display.CONTENT_HEIGHT, Color.valueOf("#141518"));
        background.addActor(solid);
    }

    private void initWalls() {

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
    public GameGroup<Actor> overtop() {
        return overtop;
    }

    @Override
    public CollisionManager collisionManager() {
        return collisionManager;
    }

    @Override
    public void ensureInBounds(Actor actor) {
        actor.setX(Math.max(Display.WORLD_BORDER_PAD, actor.getX()));
        actor.setX(Math.min(Display.WORLD_BORDER_PAD + Display.CONTENT_WIDTH - actor.getWidth(), actor.getX()));
        actor.setY(Math.max(Display.CONTENT_BOTPAD, actor.getY()));
        actor.setY(Math.min(Display.CONTENT_BOTPAD + Display.CONTENT_HEIGHT - actor.getHeight(), actor.getY()));
    }

    @Override
    public void ensureInBounds(PhysicalEntity actor) {
        if (actor.getX() < Display.WORLD_BORDER_PAD) {
            actor.setX(Display.WORLD_BORDER_PAD);
            actor.onCollision(Side.LEFT);
        }
        if (actor.getX() > -Display.WORLD_BORDER_PAD + Display.CONTENT_WIDTH - actor.getWidth()) {
            actor.setX(-Display.WORLD_BORDER_PAD + Display.CONTENT_WIDTH - actor.getWidth());
            actor.onCollision(Side.RIGHT);
        }
//        if(actor.getY()< Display.BOT_PAD){
//            actor.setY(Display.BOT_PAD);
//            actor.onCollision(Side.BOTTOM);
//        }
        if (actor.getY() > Display.WORLD_TOP - actor.getHeight()) {
            actor.setY(Display.WORLD_TOP - actor.getHeight());
            actor.onCollision(Side.TOP);
        }
    }


    private ClickListener clickListener = new ClickListener(-1) {
        private long lastTouchTime;
        private Vector2 lastTouchPos;

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (x < Display.CONTENT_WIDTH + Display.WORLD_BORDER_PAD) {

            }
            return super.touchDown(event, x, y, pointer, button);
        }


        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            return super.keyDown(event, keycode);
        }
    };

    @Override
    public abstract Hud getHud();

    @Override
    public void shakeScreen(int intensity) {
        Action action = ActionFactory.shake(gameContainer, intensity);
        gameContainer.addAction(Actions.sequence(action,
                Actions.moveTo(Display.CONTENT_LEFTPAD, Display.CONTENT_BOTPAD)));
    }

    public void update(float delta) {

    }

    @Override
    public void setPaused(boolean paused) {
        gameContainer.setPaused(paused);
    }

    @Override
    public void showModal(Modal modal) {
        overtop().addActorAt(overtop.getChildren().size - 1, modal);
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public boolean isState(String state) {
        return false;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    public void showSettingsModal() {


        showModal(new SettingsModal(iApp, new ModalListener() {
            @Override
            public void onDismiss(Modal modal) {

            }
        }));

    }

    @Override
    public void pauseGame() {
        if (sceneScript().isPauseable()) {
            setPaused(true);
            sceneScript().setPaused(true);
            showModal(new PauseModal(game, new PauseModal.OptionsModalListener() {
                @Override
                public void onLeaveGame() {
                    goHome();
                }

                @Override
                public void onDismiss(Modal modal) {
                    setPaused(false);
                    sceneScript().setPaused(false);
                }
            }));
        }
    }

    @Override
    public void fresh() {
        gameContainer.clearActions();
        gameContainer.setPosition(Display.CONTENT_LEFTPAD, Display.CONTENT_BOTPAD);
    }

    @Override
    public void goHome() {
        iApp.screenManager().restoreScreen(HomeScreen.class);
    }

}