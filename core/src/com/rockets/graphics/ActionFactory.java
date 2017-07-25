package com.rockets.graphics;

import com.badlogic.gdx.math.Interpolation;
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

    public static Action shake(Actor actor, float intensity) {
        return shake(actor, intensity, 1);
    }

    public static Action shake(Actor actor, float intensity, float decay) {
        tempOrigin.set(actor.getX(), actor.getY());
        tempFloat1 = MathUtils.random(0, 360);
        SequenceAction seq = Actions.sequence();
        while (!MathUtils.isEqual(intensity, decay)) {
            temp1.set(0, intensity);
            temp1.setAngle(tempFloat1 + MathUtils.random(90, 270));
            tempFloat1 = temp1.angle();
            seq.addAction(Actions.moveTo(actor.getX() + temp1.x, actor.getY() + temp1.y, AnimConst.FAST));
            intensity -= decay;
        }
        seq.addAction(Actions.moveTo(actor.getX(), actor.getY(), AnimConst.FAST));
        return seq;
    }

    public static Action fadeUp() {
        float y = 16;
        float dur = 0.3f;
        return Actions.sequence(
                Actions.alpha(0),
                Actions.moveBy(0, -y),
                Actions.parallel(
                        Actions.alpha(1f, dur, Interpolation.pow3Out),
                        Actions.moveBy(0, y, dur, Interpolation.pow3Out)
                )
        );
    }

    public static Action springPulsate() {
        float maxScale = 1.1f;
        float small = 0.95f;
        float big = 1.05f;
        float dur = 0.5f;
        return Actions.sequence(
                Actions.scaleTo(maxScale, maxScale, 0.2f, Interpolation.pow2In),
                Actions.scaleTo(small, small, 0.2f, Interpolation.pow2Out),
                Actions.forever(Actions.sequence(
                        Actions.scaleTo(big, big, dur, Interpolation.pow2In),
                        Actions.scaleTo(small, small, dur, Interpolation.pow2Out)
                ))
        );
    }

    public static Action pulsate() {
        float small = 0.95f;
        float big = 1.05f;
        float dur = 0.5f;
        return Actions.forever(Actions.sequence(
                Actions.scaleTo(big, big, dur, Interpolation.pow2In),
                Actions.scaleTo(small, small, dur, Interpolation.pow2Out)
                )
        );
    }
}
