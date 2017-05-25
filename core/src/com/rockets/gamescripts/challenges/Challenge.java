package com.rockets.gamescripts.challenges;

import com.rockets.gamescreen.hud.ChallengeHud;
import com.rockets.gamescripts.BaseSceneScript;

/**
 * name: Challenge
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class Challenge extends BaseSceneScript {
    protected int score;
    protected int goal;
    protected ChallengeHud hud;

    public void setHud(ChallengeHud hud) {
        this.hud = hud;
    }

    @Override
    public void setup() {
        score = 0;
        super.setup();
        initHUD();
    }

    @Override
    public void onStateChanged(String oldState, String newState) {
        hud.setInstructionsVisible(newState.equals(STATE_READY));
    }

    protected abstract void initHUD();
}
