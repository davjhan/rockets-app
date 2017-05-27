package com.rockets.gamescripts;

import com.rockets.gamescreen.GameScreen;
import com.rockets.gamescreen.modals.ChallengeCompleteModal;
import com.rockets.gamescreen.modals.OptionsModalListener;
import com.rockets.modal.Modal;

/**
 * name: CollectChallenge
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class CollectChallenge extends BaseChallenge {
    protected int score;

    @Override
    public void fresh() {
        score = 0;
        super.fresh();
    }

    protected boolean didReachGoal(){
        return(score >= challengeModel.goal);
    }
    protected void evaluateProgress(){
        if(didReachGoal()){
            setState(STATE_PAUSED);
            ChallengeCompleteModal challengeCompleteModal = new ChallengeCompleteModal(
                    dir.app(), challengeModel, new OptionsModalListener() {
                @Override
                public void onLeaveGame() {

                }

                @Override
                public void onDissmiss(Modal modal) {
                    dir.app().screenManager().pushScreen(
                            GameScreen.class,GameScreen.getChallengeExtras("coin_1")
                    );
                }
            }
            );
            dir.gameWorld().showModal(challengeCompleteModal);
        }
    }
}
