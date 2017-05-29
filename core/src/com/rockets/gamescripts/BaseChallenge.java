package com.rockets.gamescripts;

import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.hud.ChallengeHud;
import com.rockets.gamescreen.modals.GameOverModal;
import com.rockets.gamescreen.modals.OptionsModal.OptionsModalListener;
import com.rockets.gamescreen.objects.Player;
import com.rockets.gamescreen.world.StateListener;
import com.rockets.modal.Modal;

/**
 * name: Challenge
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class BaseChallenge extends BaseSceneScript {
    protected Challenges.ChallengeModel challengeModel;
    protected ChallengeHud hud;


    protected Player player;

    public void create(SceneDirector director, Challenges.ChallengeModel challengeModel, ChallengeHud hud){
        super.create(director);
        this.challengeModel = challengeModel;
        this.hud = hud;
    }

    @Override
    protected void init() {
        super.init();
        player = new Player(dir.game());
        player.addStateListener(new StateListener(){
            @Override
            public void onStateChanged(String oldState, String newState) {
                if(newState.equals(Player.STATE_DEAD)){
                    endGame();
                    return;
                }
                if(oldState.equals(Player.STATE_READY) && !newState.equals(Player.STATE_READY)){
                    setState(STATE_RUNNING);
                }
            }
        });
    }

    @Override
    public void fresh() {
        super.fresh();
        dir.gameWorld().bodies().clearChildren();
        hud.fresh();
        player.fresh();
        dir.gameWorld().bodies().spawn(
                player,
                Display.HALF_WIDTH,Display.HALF_HEIGHT, Align.center);
        initHUD();
    }

    @Override
    public void update(float delta) {
        if(!isState(STATE_PAUSED)) {
            if(player.getTop() < -24){
                endGame();
            }
        }
    }

    protected void endGame() {
        if(!isState(STATE_END)){
            setState(STATE_END);
            GameOverModal gameOverModal = new GameOverModal(dir.app(),new OptionsModalListener(){

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
        dir.gameWorld().setPaused(isState(STATE_PAUSED) );
    }

    @Override
    public void dispose() {

        player.dispose();
        super.dispose();
    }

    @Override
    public void onStateChanged(String oldState, String newState) {
        hud.setInstructionsVisible(newState.equals(STATE_READY));
    }

    protected abstract void initHUD();
}
