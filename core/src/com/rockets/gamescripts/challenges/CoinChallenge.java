package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
import com.rockets.gamescreen.objects.Coin;
import com.rockets.gamescripts.CollectChallenge;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class CoinChallenge extends CollectChallenge {
    Coin coin;
    public static final int PAD = 60;
    public static final int BOT_PAD = 60;
    int stage = 4;
    private static final Vector2[] COIN_SPOTS = {
            new Vector2(PAD,BOT_PAD+PAD),
            new Vector2(Display.WORLD_WIDTH-PAD,BOT_PAD+PAD),
            new Vector2(Display.WORLD_WIDTH-PAD,Display.WORLD_HEIGHT-PAD),
            new Vector2(PAD,Display.WORLD_HEIGHT-PAD),
            new Vector2(Display.WORLD_WIDTH/2,BOT_PAD+(Display.WORLD_HEIGHT-BOT_PAD)/2),
    };

    @Override
    public void fresh() {
        super.fresh();
        spawnCoin(COIN_SPOTS[0]);
    }

    @Override
    protected void init() {
        super.init();
        coin = new Coin(dir.game());
        coin.setCoinListener(coinListener);
    }

    @Override
    protected void initHUD() {
        hud.updateScore(score);
        hud.updateGoal(challengeModel.goal);
    }

    private void spawnCoin(Vector2 loc){
        coin.fresh();
        dir.gameWorld().bodies().spawn(coin,loc.x,loc.y, Align.center);
    }

    private Coin.CoinListener coinListener = new Coin.CoinListener() {
        @Override
        public void collected() {
            int oldStage = stage;
            while(stage == oldStage) {
                stage = MathUtils.random(COIN_SPOTS.length - 1);
            }

            spawnCoin(COIN_SPOTS[stage]);
            player.getVel().y = Math.max(player.getVel().y,0);
            player.getVel().add(0,10);
            dir.gameWorld().shakeScreen(2);
            score++;
            hud.updateScore(score);
            evaluateProgress();
        }
    };



    @Override
    public void dispose() {
        coin.dispose();
        super.dispose();
    }
}
