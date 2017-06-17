package com.rockets.gamescripts.challenges;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.rockets.gamescreen.objects.WoodenBox;
import com.rockets.gamescripts.CollectChallenge;

/**
 * name: CoinChallege
 * desc:
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class LockboxChallenge extends CollectChallenge {
    WoodenBox woodenBox;

    @Override
    protected void init() {
        super.init();
        woodenBox = new WoodenBox(dir.game());
        woodenBox.setBrokenListener(new WoodenBox.OnBrokenListener() {
            @Override
            public void onBroken() {
                spawnCoin(woodenBox.getX(Align.center),woodenBox.getY(Align.center));
            }
        });
        Gdx.app.log("tttt LockboxChallenge", "at init()");
    }

    @Override
    public void fresh() {
        super.fresh();
        coin.remove();
    }

    @Override
    protected void initSequence() {
        sequence = new Vector2[]{
                Grid.get(2, 1),
                Grid.get(0, 7),
                Grid.get(4, 7),
                Grid.get(2, 6),
                Grid.get(2, 7),

        };
    }
    protected void spawnWoodenBox(Vector2 loc){
        woodenBox.fresh();
        dir.gameWorld().bodies().spawn(woodenBox,loc.x,loc.y, Align.center);
    }
    @Override
    protected void nextPart() {
        spawnWoodenBox(getNextSeqSpot());

    }

    @Override
    public void dispose() {
        super.dispose();
        woodenBox.dispose();
    }
}
