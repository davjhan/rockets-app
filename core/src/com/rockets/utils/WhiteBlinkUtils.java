package com.rockets.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rockets.assets.GameAssets;

/**
 * name: AnimUtils
 * desc:
 * date: 2017-01-10
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class WhiteBlinkUtils {
    public static Action getWhiteBlinkAction(GameAssets gameAssets,final Sprite sprite){
        final Texture regTexture = sprite.getTexture();
        final Texture whiteTexture = gameAssets.getWhiteTexture(sprite.getTexture());

        Runnable setToWhiteRunnable = new Runnable() {
            @Override
            public void run() {
                sprite.setTexture(whiteTexture);
            }
        };
        Runnable setToOriginal = new Runnable() {
            @Override
            public void run() {
                sprite.setTexture(regTexture);
            }
        };
        return Actions.sequence(
                Actions.run(setToWhiteRunnable),
                Actions.delay(0.05f,Actions.run(setToOriginal)),
                Actions.delay(0.05f,Actions.run(setToWhiteRunnable)),
                Actions.delay(0.05f,Actions.run(setToOriginal)),
                Actions.delay(0.05f,Actions.run(setToWhiteRunnable)),
                Actions.delay(0.05f,Actions.run(setToOriginal))
        );
    }
    public static Action getWhiteShortBlinkAction(GameAssets gameAssets,final Sprite sprite){
        final Texture regTexture = sprite.getTexture();
        final Texture whiteTexture = gameAssets.getWhiteTexture(sprite.getTexture());
        Runnable setToWhiteRunnable = new Runnable() {
            @Override
            public void run() {
                sprite.setTexture(whiteTexture);
            }
        };
        Runnable setToOriginal = new Runnable() {
            @Override
            public void run() {
                sprite.setTexture(regTexture);
            }
        };

        return Actions.sequence(
                Actions.run(setToWhiteRunnable),
                Actions.delay(0.05f,Actions.run(setToOriginal))
        );
    }
}
