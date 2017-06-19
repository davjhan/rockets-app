package com.rockets.gamescripts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.gamescreen.GameScreen;
import com.rockets.gamescreen.modals.ChallengeCompleteModal;
import com.rockets.gamescreen.objects.Coin;
import com.rockets.modal.Modal;

/**
 * name: CollectChallenge
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class CollectChallenge extends BaseChallenge {
    protected Coin coin;
    protected int score;
    protected Vector2[] sequence;

    public static class Grid {
        private static int pad = 10;
        private static int rowSize = 5;
        private static int colSize = 8;
        private static int unitSize = 50;

        public static Vector2 get(int x, int y) {
            if (x >= rowSize) {
                throw new IllegalArgumentException();
            }
            if (y >= colSize) {
                throw new IllegalArgumentException();
            }

            return new Vector2(
                    (pad + x * unitSize + (unitSize / 2)),
                    Display.WORLD_TOP -(pad + y * unitSize + (unitSize / 2))
            );
        }
    }

    @Override
    protected void init() {
        super.init();
        coin = new Coin(dir.game());
        coin.setCoinListener(coinListener);

        initSequence();
    }


    protected Coin.CoinListener coinListener = new Coin.CoinListener() {
        @Override
        public void collected() {
            if(!incrementScore()){
                dir.gameWorld().shakeScreen(2);
            }

        }
    };

    @Override
    public void fresh() {
        score = 0;
        super.fresh();
        nextPart();
        coin.fresh();
    }

    protected boolean didReachGoal() {
        return (score >= getGoal());
    }

    protected void onGoalReached() {
        setState(STATE_END);
        ChallengeCompleteModal challengeCompleteModal = new ChallengeCompleteModal(
                dir.game(), challengeModel, new ChallengeCompleteModal.ChallengeCompleteModalListener() {
            @Override
            public void goHome() {
                dir.gameWorld().goHome();
            }

            @Override
            public void playAgain() {
                dir.app().screenManager().clearAndPushScreen(
                        GameScreen.class, GameScreen.getChallengeExtras(challengeModel.id)
                );
            }

            @Override
            public void nextChallenge() {
                dir.app().screenManager().clearAndPushScreen(
                        GameScreen.class, GameScreen.getChallengeExtras(
                                dir.app().contentDB().challenges().getNextChallengeId(challengeModel.id)
                        )
                );
            }

            @Override
            public void onDismiss(Modal modal) {

            }
        }
        );
        dir.gameWorld().showModal(challengeCompleteModal);
    }

    @Override
    protected void initHUD() {
        hud.updateScore(score);
        hud.updateGoal(getGoal());
    }

    protected void spawnCoin(Vector2 loc){
       spawnCoin(loc.x,loc.y);
    }
    protected void spawnCoin(float x, float y){
        coin.fresh();
        dir.gameWorld().bodies().spawn(coin,x,y, Align.center);
    }
    public boolean incrementScore() {
        score++;
        hud.updateScore(score);
        if (didReachGoal()) {
            onGoalReached();
            return false;
        }
        nextPart();
        return true;
    }

    protected void nextPart(){
        spawnCoin(getNextSeqSpot());
    }

    protected abstract void initSequence();

    protected Vector2 getNextSeqSpot() {
        return sequence[score];
    }

    public int getGoal() {
        return sequence.length;
    }

    @Override
    public void dispose() {
        super.dispose();
        coin.dispose();
    }
}
