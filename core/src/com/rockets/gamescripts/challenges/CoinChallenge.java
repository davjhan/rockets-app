package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.rockets.gamescripts.CollectChallenge;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class CoinChallenge extends CollectChallenge {

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(2, 0),
                Grid.get(0, 3),
                Grid.get(2, 6),
                Grid.get(4, 3),
                Grid.get(2, 3),

        };
    }
}
