package com.rockets.uiscreens.listeners;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * name: SquishyButtonListener
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SquishyButtonListener extends InputListener {
    private Action downAction;
    private Action upAction;
    private boolean enabled = true;

    public SquishyButtonListener(){

    }
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!enabled) return false;
        downAction = Actions.scaleTo(0.95f,0.95f,0.1f, Interpolation.pow2);
        event.getListenerActor().removeAction(upAction);
        event.getListenerActor().addAction(downAction);

        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if(!enabled) return;

        event.getListenerActor().removeAction(downAction);
        upAction = Actions.scaleTo(1f,1f,0.1f, Interpolation.pow2);
        event.getListenerActor().addAction(upAction);
        super.touchUp(event, x, y, pointer, button);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
