package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.WallBlock;
import com.rockets.gamescripts.CollectChallenge;
import com.rockets.graphics.DisposableList;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class MiddleWallChallenge extends CollectChallenge {
    DisposableList<WallBlock> wallBlocks;
    @Override
    protected void init() {
        super.init();
        wallBlocks = new DisposableList<>();
         for(int i = 0; i < 8; i ++) {
             WallBlock wallBlock = new WallBlock(dir.game());
             wallBlocks.add(wallBlock);
             dir.gameWorld().bodies().spawn(wallBlock, (Grid.get(2,i-1)), Align.center);
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

        };
    }

    @Override
    protected Vector2 playerSpawnLoc() {
        return Grid.get(4,0);
    }

    @Override
    public void dispose() {
        super.dispose();
        wallBlocks.dispose();
    }
}
