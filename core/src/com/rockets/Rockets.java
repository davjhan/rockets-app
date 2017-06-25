package com.rockets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import com.rockets.assets.GameAssets;
import com.rockets.assets.MenuAssets;
import com.rockets.common.IApp;
import com.rockets.common.IAppInitializer;
import com.rockets.common.ScreenManager;
import com.rockets.constants.Display;
import com.rockets.data.Backend;
import com.rockets.data.ContentDB;
import com.rockets.data.Saves;
import com.rockets.modal.Modal;
import com.rockets.modal.ModalStageManager;
import com.rockets.uiscreens.LaunchScreen;

public class Rockets extends Game implements IApp, IAppInitializer {
    ScreenManager screenManager;
    I18NBundle bundleText;
    MenuAssets menuAssets;
    GameAssets gameAssets;
    ModalStageManager modalStageManager;
    Stage modalStage;
    ContentDB contentDB;
    Saves saves;
    Backend backend;
    public InputMultiplexer inputMultiplexer;
    private boolean modalDebugAll = false;
    private boolean currentScreenDebug = false;

    public static boolean DEBUG = false;
    public static final String SHARED_PREFS_NAME="rockets_save";

    @Override
    public void create() {
        if (!VisUI.isLoaded()) {
            VisUI.load();
        }
        inputMultiplexer = new InputMultiplexer();
        modalStage = new Stage(new FitViewport(Display.SCREEN_WIDTH, Display.SCREEN_HEIGHT));
        screenManager = new ScreenManager(this);
        screenManager.setScreen(new LaunchScreen(this));
        modalStageManager = new ModalStageManager(modalStage);
        saves = new Saves();
        backend = new Backend(this);
        initInput();
    }

    private void initInput() {

        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);
        inputMultiplexer.addProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.M){
                    modalDebugAll = !modalDebugAll;
                    modalStage().setDebugAll(modalDebugAll);
                }
                if(keycode == Input.Keys.D){
                    DEBUG = !DEBUG;
                    screenManager.getCurrentScreen().toggleDebug();

                }
                if(keycode == Input.Keys.ESCAPE ||keycode == Input.Keys.BACK){
                    if(!modalStageManager.onBackPressed()) {
                        screenManager.getCurrentScreen().onBackPressed();
                    }
                    return true;

                }
                return super.keyDown(keycode);
            }
        });
        inputMultiplexer.addProcessor(modalStage);
        


    }
    @Override
    public void resume() {
        super.resume();
        if(inputMultiplexer.getProcessors().contains(modalStage,false)){

            inputMultiplexer.addProcessor(
                    inputMultiplexer.getProcessors().size-1,
                    modalStage);
        }
    }

    @Override
    public ScreenManager screenManager() {
        return screenManager;
    }

    @Override
    public GameAssets gameAssets() {
        return gameAssets;
    }

    @Override
    public MenuAssets menuAssets() {
        return menuAssets;
    }

    @Override
    public ContentDB contentDB() {
        return contentDB;
    }

    @Override
    public void setMenuAssets(MenuAssets assets) {
        this.menuAssets = assets;
    }

    @Override
    public void setGameAssets(GameAssets assets) {
        this.gameAssets = assets;
    }

    @Override
    public void setContentDB(ContentDB contentDB) {
        this.contentDB = contentDB;
    }

    @Override
    public void setStrings(I18NBundle bundleText) {
        this.bundleText = bundleText;
    }

    @Override
    public String getString(String key) {
        return bundleText.get(key);
    }

    @Override
    public void showModal(Modal modal) {
        modalStageManager.showModal(modal);
    }

    @Override
    public Backend backend() {
        return backend;
    }

    @Override
    public Saves saves() {
        return saves;
    }

    @Override
    public Stage modalStage() {
        return modalStage;
    }

    @Override
    public void render() {
        modalStage.act(Gdx.graphics.getDeltaTime());
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());

        modalStage.draw();
    }
}
