package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.WallBlock;
import com.rockets.gamescripts.CollectChallenge;
import com.rockets.graphics.DisposableList;

/**
 * name: TutorialChallenge
 * desc:
 * date: 2017-06-17
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class TutorialChallenge extends CollectChallenge {
    DisposableList<WallBlock> wallBlocks;
    @Override
    public void fresh() {
        super.fresh();
    }

    @Override
    protected void init() {
        super.init();
        wallBlocks = new DisposableList<>();
        for(int i = 0; i < 7; i ++){
            WallBlock block = new WallBlock(dir.game());
            dir.game().world().bodies().spawn(block,
                    -40+(WallBlock.SIZE*i),
                    20, Align.topLeft
                    );
            wallBlocks.add(block);
        }
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(2, 0)
        };
    }

    @Override
    public void dispose() {
        super.dispose();
        wallBlocks.dispose();
    }
}
