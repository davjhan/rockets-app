package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.rockets.gamescreen.objects.Spike;
import com.rockets.gamescripts.CollectChallenge;
import com.rockets.graphics.DisposableList;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class EdgeSpikeChallenge2 extends CollectChallenge {
    DisposableList<Spike> spikes;
    public EdgeSpikeChallenge2(){
        topWall = WallType.spike;
    }
    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(1, 6),
                Grid.get(3, 5),
                Grid.get(0, 1),
                Grid.get(4, 0),
                Grid.get(2, 8),
                Grid.get(0, 2),
                Grid.get(1, 0),
                Grid.get(2, 2),
                Grid.get(3, 0),
                Grid.get(4, 2),

        };
    }

    @Override
    protected Vector2 playerSpawnLoc() {
        return Grid.get(2,8);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
