package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.Display;
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
public class CageChallenge extends CollectChallenge {
    DisposableList<Spike> spikes;
    @Override
    protected void init() {
        super.init();
        spikes = new DisposableList<>();
        for(int x = 0; x < 2; x ++){
         for(int i = 0; i < 9; i ++) {
             Spike spike = new Spike(dir.game());
             spikes.add(spike);
             dir.gameWorld().bodies().spawn(spike,
                     -36+(x* (Display.CONTENT_WIDTH+30)),
                     Display.CONTENT_HEIGHT -(i*50), Align.topLeft);
         }
        }
    }

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(0, 0),
                Grid.get(4, 1),
                Grid.get(0, 2),
                Grid.get(4, 3),
                Grid.get(0, 4),
                Grid.get(4, 5),
                Grid.get(0, 6),
                Grid.get(4, 7),

        };
    }

    @Override
    public void dispose() {
        super.dispose();
        spikes.dispose();
    }
}
