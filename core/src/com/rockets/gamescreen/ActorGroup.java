package com.rockets.gamescreen;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * name: ActorGroup
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class ActorGroup<T extends Actor> extends ArrayList<T> {

    public ActorGroup(IGame game){
        init(game);
    }

    protected abstract void init(IGame game);


}
