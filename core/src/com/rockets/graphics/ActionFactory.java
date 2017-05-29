package com.rockets.graphics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.rockets.constants.AnimConst;

/**
 * name: ActionFactory
 * desc:
 * date: 2017-05-28
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ActionFactory {
    private static Vector2 tempOrigin = new Vector2();
    private static Vector2 temp1 = new Vector2();
    private static Vector2 temp12 = new Vector2();

    private static float tempDecay;
    private static float tempFloat1;
    public static Action shake(Actor actor,  float intensity){
        tempOrigin.set(actor.getX(),actor.getY());
        tempDecay = 1f;
        tempFloat1 = MathUtils.random(0,360);
        SequenceAction seq = Actions.sequence();
        while(!MathUtils.isEqual(intensity,tempDecay)){
            temp1.set(0,intensity);
            temp1.setAngle(tempFloat1+MathUtils.random(90,270));
            tempFloat1 = temp1.angle();
            seq.addAction(Actions.moveTo(actor.getX()+temp1.x,actor.getY()+temp1.y, AnimConst.FAST));
            intensity -= tempDecay;
        }
        seq.addAction(Actions.moveTo(actor.getX(),actor.getY(), AnimConst.FAST));
        return seq;
    }
}
