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
public class EdgeSpikeChallenge extends CollectChallenge {
    DisposableList<Spike> spikes;
    public EdgeSpikeChallenge(){
        leftWall = WallType.spike;
        rightWall = WallType.spike;
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
                Grid.get(1, 0),
                Grid.get(3, 1),
                Grid.get(0, 2),
                Grid.get(4, 3),
                Grid.get(0, 4),
                Grid.get(4, 5),
                Grid.get(0, 6),
                Grid.get(2, 0),
                Grid.get(4, 7),

        };
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
