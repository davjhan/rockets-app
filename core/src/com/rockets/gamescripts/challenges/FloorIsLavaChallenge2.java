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
public class FloorIsLavaChallenge2 extends CollectChallenge {

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(4, 0),
                Grid.get(1, 1),
                Grid.get(3, 2),
                Grid.get(0, 3),
                Grid.get(2, 4),
                Grid.get(1, 7),
                Grid.get(2, 0),
                Grid.get(3, 8),

        };
    }
}
