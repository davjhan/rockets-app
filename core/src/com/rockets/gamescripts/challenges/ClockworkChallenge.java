package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.Spike;
import com.rockets.gamescripts.CollectChallenge;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ClockworkChallenge extends CollectChallenge {
    Spike spike;

    @Override
    protected void init() {
        super.init();
        spike = new Spike(dir.game());
        dir.gameWorld().bodies().spawn(spike,Grid.get(2,4), Align.center);
    }

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        Vector2 v = new Vector2(0,100);
        sequence = new Vector2[12];
        for(int i = 0; i < 12; i ++){
            v.setAngle(90-((360/12)*i));
            sequence[i] = Grid.get(2,4).add(v);
        }
    }

    @Override
    protected Vector2 playerSpawnLoc() {
        return Grid.get(4,4);
    }
}
