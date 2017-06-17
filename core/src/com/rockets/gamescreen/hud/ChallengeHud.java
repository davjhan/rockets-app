package com.rockets.gamescreen.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.world.StateListener;
import com.rockets.gamescripts.BaseChallenge;
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
        scoreLabel = HanLabel.text("")
                .font(Font.h1)
                .color(Colr.TEXT_LIGHT)
                .build();
        scoreLabel.setAlignment(Align.center);
        goalLabel = HanLabel.text("")
                .font(Font.c1)
                .color(Colr.TEXT_LIGHT)
                .build();
        goalLabel.setAlignment(Align.center);
        updateGoal(10);
        centerGroup.add(scoreLabel).align(Align.center).spaceBottom(Spacing.XSMALL);
        centerGroup.row();
        centerGroup.add(goalLabel).align(Align.center);
    }

    @Override
    protected void initInstructions() {
        instructions.setPosition(Display.HALF_WIDTH, 80, Align.center);
        instructions.setTouchable(Touchable.disabled);
        addActor(instructions);
        instructionsLabel = HanLabel.text("tap to thrust")
                .font(Font.h1)
                .color(Colr.TEXT_LIGHT)
                .build();
        instructionsLabel.setAlignment(Align.center);
        instructions.add(instructionsLabel);
    }

    private void initRightGroup() {
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void updateGoal(int goal) {
        goalLabel.setText("GOAL : " + String.valueOf(goal));
    }

    public void attachChallenge(BaseChallenge challenge) {
        challenge.addStateListener(new StateListener() {
            @Override
            public void onStateChanged(String oldState, String newState) {
                Gdx.app.log("tttt ChallengeHud", "pauseable: " + game.world().sceneScript().isPauseable());
                pauseButton.setTouchable(game.world().sceneScript().isPauseable() ? Touchable.enabled : Touchable.disabled);
            }
        });
    }


}
