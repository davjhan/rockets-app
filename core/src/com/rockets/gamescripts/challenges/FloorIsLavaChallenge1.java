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
public class FloorIsLavaChallenge1 extends CollectChallenge {

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(0, 0),
                Grid.get(4, 0),
                Grid.get(1, 1),
                Grid.get(3, 1),
                Grid.get(5, 2),

        };
    }
}
