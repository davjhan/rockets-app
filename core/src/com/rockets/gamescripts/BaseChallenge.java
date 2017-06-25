package com.rockets.gamescripts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.constants.Display;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.hud.ChallengeHud;
import com.rockets.gamescreen.modals.GameOverModal;
import com.rockets.gamescreen.modals.PauseModal.OptionsModalListener;
import com.rockets.gamescreen.objects.Player;
import com.rockets.gamescreen.objects.Spike;
import com.rockets.gamescreen.world.StateListener;
import com.rockets.graphics.DisposableList;
import com.rockets.modal.Modal;

import static com.rockets.gamescripts.BaseChallenge.WallType.block;

/**
 * s
 * name: Challenge
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class BaseChallenge extends BaseSceneScript {
    protected Challenges.ChallengeModel challengeModel;
    protected ChallengeHud hud;

    protected WallType topWall = block;
    protected WallType leftWall = block;
    protected WallType rightWall = block;
    protected DisposableList<Disposable> wallsBlocks;
    protected Player player;

    public void create(SceneDirector director, Challenges.ChallengeModel challengeModel, ChallengeHud hud) {
        super.create(director);
        this.challengeModel = challengeModel;
        this.hud = hud;
    }

    @Override
    protected void init() {
        super.init();
        wallsBlocks = new DisposableList();
        for (int i = 0; i < 7; i++) {
            makeWallBlock(topWall, CollectChallenge.Grid.get(i-1, -1));
        }

        for (int i = 0; i < 10; i++) {
            makeWallBlock(leftWall, CollectChallenge.Grid.get(-1, i));
        }

        for (int i = 0; i < 10; i++) {
            makeWallBlock(rightWall, CollectChallenge.Grid.get(5, i));
        }
        player = new Player(dir.game());
        player.addStateListener(new StateListener() {
            @Override
            public void onStateChanged(String oldState, String newState) {
                if (newState.equals(Player.STATE_DEAD)) {
                    endGame();
                    return;
                }
                if (oldState.equals(Player.STATE_READY) && !newState.equals(Player.STATE_READY)) {
                    setState(STATE_RUNNING);
                }
            }
        });

    }

    private void makeWallBlock(WallType type, Vector2 pos) {
        if (type.equals(WallType.block)) {
            Image block = new Image(dir.app().gameAssets().specialObjects[2]);
            block.setAlign(Align.center);
            dir.gameWorld().bodies().spawn(block, pos, Align.center);
        } else if (type.equals(WallType.spike)) {
            Spike spike = new Spike(dir.game());
            wallsBlocks.add(spike);
            dir.gameWorld().bodies().spawn(spike, pos, Align.center);
        }
    }

    @Override
    public void fresh() {
        super.fresh();
        dir.gameWorld().fresh();
        hud.fresh();
        player.fresh();
        dir.gameWorld().bodies().spawn(
                player,
                playerSpawnLoc(), Align.center);
        initHUD();
    }

    protected Vector2 playerSpawnLoc() {
        return CollectChallenge.Grid.get(2, 4);
    }

    @Override
    public void update(float delta) {
        if (!isState(STATE_PAUSED)) {
            if (player.getTop() < Display.CONTENT_BOTPAD - 24) {
                endGame();
            }
        }
    }

    protected void endGame() {
        if (!isState(STATE_END)) {
            setState(STATE_END);
            GameOverModal gameOverModal = new GameOverModal(dir.game(), new OptionsModalListener() {

                @Override
                public void onLeaveGame() {
                    dir.gameWorld().goHome();
                }

                @Override
                public void onDismiss(Modal modal) {
                    fresh();
                }
            });
            dir.gameWorld().showModal(gameOverModal);
            dir.gameWorld().shakeScreen(5);
        }

    }

    @Override
    public void setState(String state) {
        super.setState(state);
        dir.gameWorld().setPaused(isState(STATE_PAUSED) || isState(STATE_READY) || isState(STATE_BACKGROUND) || isState(STATE_END));
    }

    @Override
    public void dispose() {
        player.dispose();
        wallsBlocks.dispose();
        super.dispose();
    }

    @Override
    public void onStateChanged(String oldState, String newState) {
        hud.setInstructionsVisible(newState.equals(STATE_READY));
    }

    public static enum WallType {
        block, spike
    }


    protected abstract void initHUD();
}
