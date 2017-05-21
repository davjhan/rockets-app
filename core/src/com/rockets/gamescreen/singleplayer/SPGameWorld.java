package com.rockets.gamescreen.singleplayer;

import com.badlogic.gdx.utils.Align;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.gamescreen.Hud;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.objects.IPlayer;
import com.rockets.gamescreen.objects.Player;
import com.rockets.gamescreen.world.GameWorld;

/**
 * name: MPGameWorld
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SPGameWorld extends GameWorld {
    Player player;
    public SPGameWorld(IApp iApp, IGame game) {
        super(iApp, game);

    }

    @Override
    public void init() {
        super.init();

        player = new Player(game);
        bodies.spawn(player, Display.HALF_WIDTH,Display.HALF_HEIGHT, Align.center);
    }

    @Override
    public IPlayer getLocalPlayer() {
        return player;
    }

    @Override
    protected void initHud() {

    }

    @Override
    protected Hud getHud() {
        return null;
    }

}
