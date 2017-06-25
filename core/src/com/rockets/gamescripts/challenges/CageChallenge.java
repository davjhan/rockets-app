package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.Spike;
import com.rockets.gamescripts.CollectChallenge;
import com.rockets.graphics.DisposableList;

import java.util.ArrayList;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class CageChallenge extends CollectChallenge {
    DisposableList<Spike> spikes;
    int topY = 0;
    int botY = topY + 6;
    @Override
    protected void init() {
        super.init();
        spikes = new DisposableList<>();

        ArrayList<Vector2> spikeLoc = new ArrayList<>();
        for (int y = topY; y < botY; y++) {
            spikeLoc.add(Grid.get(0,y));
            spikeLoc.add(Grid.get(4,y));
        }
        for (int x = 0; x < 5; x++) {
            spikeLoc.add(Grid.get(x,topY));
            if(x != 2){
                spikeLoc.add(Grid.get(x,botY));
            }
        }

        for(Vector2 loc:spikeLoc){
            Spike spike = new Spike(dir.game());
            spikes.add(spike);
            dir.gameWorld().bodies().spawn(spike,loc, Align.center);
        }
    }

    @Override
    protected Vector2 playerSpawnLoc() {
        return Grid.get(2,3);
    }

    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(2, 1),
                Grid.get(3, botY-1),
                Grid.get(1, topY+1),
                Grid.get(1, botY-1),
                Grid.get(3, topY+1),
                Grid.get(0, 8),
                Grid.get(2, 3),

        };
    }

    @Override
    public void dispose() {
        super.dispose();
        spikes.dispose();
    }
}
