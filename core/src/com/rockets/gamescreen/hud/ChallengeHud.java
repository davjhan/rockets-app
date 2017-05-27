package com.rockets.gamescreen.hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.views.HanLabel;

/**
 * name: ChallengeHud
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeHud extends Hud {

    Label scoreLabel;
    Label goalLabel;
    Label instructionsLabel;
    public ChallengeHud(IGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        initCenterGroup();
    }

    private void initCenterGroup() {
        centerGroup.align(Align.center);
        scoreLabel = new HanLabel("11", Font.h1, Colr.TEXT_LIGHT);
        scoreLabel.setAlignment(Align.center);
        goalLabel = new HanLabel("", Font.c1, Colr.TEXT_LIGHT);
        goalLabel.setAlignment(Align.center);
        updateGoal(10);
        centerGroup.add(scoreLabel).align(Align.center).spaceBottom(Spacing.XSMALL);
        centerGroup.row();
        centerGroup.add(goalLabel).align(Align.center);
    }

    @Override
    protected void initInstructions() {
        instructions.setPosition(Display.HALF_WIDTH,80,Align.center);
        addActor(instructions);
        instructionsLabel  = new HanLabel("TAP TO THRUST", Font.p1, Colr.TEXT_LIGHT);
        instructionsLabel.setAlignment(Align.center);
        instructions.add(instructionsLabel);
    }

    private void initRightGroup(){
    }

    public void updateScore(int score){
        scoreLabel.setText(String.valueOf(score));
    }
    public void updateGoal(int goal){
        goalLabel.setText("GOAL : "+String.valueOf(goal));
    }
}
