package com.rockets.gamescreen.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rockets.constants.Display;
import com.rockets.gamescreen.ActorGroup;
import com.rockets.gamescreen.IGame;
import com.rockets.graphics.NestedSprite;
import com.rockets.graphics.SpriteActor;

/**
 * name: Wall
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Wall extends ActorGroup<Actor> {
    private static final int TOP = 0;
    private static final int MID = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 0;
    private static final int RIGHT = 2;

    public Wall(IGame game) {
        super(game);
    }

    @Override
    protected void init(IGame game) {
        int w = 13;
        int h = 6;
        int size = Display.UNIT;
        float botPad = Display.BOT_PAD;
        float leftPad = -size + Display.LEFT_PAD;
        TextureRegion[][] regions = game.gameAssets().walls;
        TextureRegion tempRegion;

        SpriteActor top = new SpriteActor();
        top.setPosition(leftPad + size, botPad + (h-1) * size);
        for (int i = 0; i < w - 2; i++) {
            top.addSprite(new NestedSprite(regions[TOP][MID]), size * i, 0);
        }

        SpriteActor left = new SpriteActor();
        left.setPosition(leftPad, botPad);
        for (int i = 0; i < h; i++) {
            if (i == 0) {
                tempRegion = regions[BOTTOM][LEFT];
            } else if (i == h-1) {
                tempRegion = regions[TOP][LEFT];
            } else {
                tempRegion = regions[MID][LEFT];
            }
            left.addSprite(new NestedSprite(tempRegion), 0, i * size);

        }

        SpriteActor right = new SpriteActor();
        right.setPosition(leftPad + (w - 1) * size, botPad);
        for (int i = 0; i < h; i++) {
             if (i == 0) {
                tempRegion = regions[BOTTOM][RIGHT];
            } else if (i == h-1) {
                tempRegion = regions[TOP][RIGHT];
            } else {
                tempRegion = regions[MID][RIGHT];
            }
            right.addSprite(new NestedSprite(tempRegion), 0, i * size);

        }

        SpriteActor bottom = new SpriteActor();
        bottom.setPosition(leftPad + size, botPad);
        for (int i = 0; i < w - 2; i++) {
            bottom.addSprite(new NestedSprite(regions[BOTTOM][MID]), size * i, 0);

        }
        add(top);
        add(left);
        add(right);
        add(bottom);


    }


}
