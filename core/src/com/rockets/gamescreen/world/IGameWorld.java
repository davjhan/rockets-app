package com.rockets.gamescreen.world;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.hud.Hud;
import com.rockets.gamescreen.physics.CollisionManager;
import com.rockets.gamescripts.SceneScript;
import com.rockets.modal.Modal;

/**
 * name: IGameWorld
 * desc:
 * date: 2016-12-27
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IGameWorld extends Freshable{
    IGame game();
    GameGroup<Actor> background();
    GameGroup<Actor> bodies();
    GameGroup<Actor> markers();
    GameGroup<Actor> overtop();
    Hud getHud();
    void setPaused(boolean paused);
    CollisionManager collisionManager();

    void ensureInBounds(Actor actor);
    void ensureInBounds(PhysicalEntity actor);
    void showSettingsModal();
    void pauseGame();
    void shakeScreen(int intensity);

    void showModal(Modal modal);

    String getState();
    boolean isState(String state);
    void setState(String state);
    void newGame();


    SceneScript sceneScript();
    void goHome();
}
