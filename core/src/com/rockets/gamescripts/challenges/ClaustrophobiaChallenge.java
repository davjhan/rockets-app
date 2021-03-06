package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
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
public class ClaustrophobiaChallenge extends CollectChallenge {
    DisposableList<Spike> spikes;
    @Override
    protected void init() {
        super.init();
        spikes = new DisposableList<>();
        for(int y = 2; y < 9; y ++) {
            Spike spike1 = new Spike(dir.game(), Spike.Type.skinnyTall);
            spikes.add(spike1);
            dir.gameWorld().bodies().spawn(spike1,Grid.get(1,y).sub(Spike.SKINNY_SIZE_REM,0), Align.center);

            Spike spike2 = new Spike(dir.game(), Spike.Type.skinnyTall);
            spikes.add(spike2);
            dir.gameWorld().bodies().spawn(spike2,Grid.get(3,y).add(Spike.SKINNY_SIZE_REM,0), Align.center);


        }
        for(int x = 1; x < 4; x ++) {
            Spike spike = new Spike(dir.game());
            spikes.add(spike);
            dir.gameWorld().bodies().spawn(spike,Grid.get(x,1), Align.center);
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
