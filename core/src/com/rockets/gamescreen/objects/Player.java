package com.rockets.gamescreen.objects;

import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.world.PhysicalEntity;
import com.rockets.graphics.Nested;
import com.rockets.graphics.NestedSprite;

/**
 * name: Player
 * desc:
 * date: 2017-05-21
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Player extends PhysicalEntity implements IPlayer{
    Nested sprite;
    public Player(IGame game) {
        super(game);
        sprite = new NestedSprite(game.gameAssets().animals.get("bird").down.first());
        addSprite(sprite);
        setSizeTo(sprite);
        init();
    }
    private void init(){

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
