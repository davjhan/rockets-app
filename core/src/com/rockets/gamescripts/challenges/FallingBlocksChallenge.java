package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.FallingSpike;
import com.rockets.gamescreen.objects.WallBlock;
import com.rockets.gamescreen.world.GameEntity;
import com.rockets.gamescripts.CollectChallenge;
import com.rockets.graphics.DisposableList;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class FallingBlocksChallenge extends CollectChallenge {
    DisposableList<WallBlock> blockers;
    DisposableList<FallingSpike> spikes;

    @Override
    protected void init() {
        super.init();
        spikes = new DisposableList<>();
        blockers = new DisposableList<>();
        for(int i = 0; i < 4; i ++){
            spikes.add(new FallingSpike(dir.game()));
        }
        for(int i = 0; i < 5; i ++){
            blockers.add(new WallBlock(dir.game(),dir.game().gameAssets().specialObjects[5]));
            dir.gameWorld().bodies().spawn(blockers.get(i),Grid.get(i,0),Align.center);
        }
    }

    @Override
    public void fresh() {
        coin.remove();
        for(FallingSpike spike:spikes){
            spike.remove();
        }
        super.fresh();

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(!isState(STATE_END) && spikes.get(0).isState(GameEntity.STATE_DEAD)){
            if(incrementScore()){
               dir.gameWorld().shakeScreen(2);
            }
        }
    }

    @Override
    protected void nextPart() {
        for(FallingSpike spike:spikes){
            spike.remove();
        }
        spawnSpikes(score+1);
    }

    @Override
    protected void initSequence() {

    }

    private void spawnSpikes(int amount) {
        int start = MathUtils.random(5-amount);
        int i = 0;
        for(int s = start; s < start+amount; s ++){
            Gdx.app.log("tttt FallingBlocksChallenge", "s: " +s+ "  "+amount);
                spikes.get(i).fresh();
                dir.gameWorld().bodies().spawn(spikes.get(i),Grid.get(s,0), Align.center);
                i++;
        }
    }

    @Override
    public int getGoal() {
        return 4;
    }

    @Override
    public void dispose() {
        super.dispose();
        spikes.dispose();
        blockers.dispose();
    }

    @Override
    public void onStateChanged(String oldState, String newState) {
        super.onStateChanged(oldState, newState);
    }
}
