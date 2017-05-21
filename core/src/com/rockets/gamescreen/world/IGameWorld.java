package com.rockets.gamescreen.world;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.objects.IPlayer;
import com.rockets.gamescreen.physics.CollisionManager;

/**
 * name: IGameWorld
 * desc:
 * date: 2016-12-27
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IGameWorld {
    IGame game();
    GameGroup<Actor> background();
    GameGroup<Actor> bodies();
    GameGroup<Actor> markers();

    CollisionManager collisionManager();

    void ensureInBounds(Actor actor);
    public void showOptionsMenu();
    public IPlayer getLocalPlayer();
    public void shakeScreen(int intensity);
}
