package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * name: NestedWidget
 * desc:
 * date: 2016-12-31
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class NestedActor implements Nested, Pool.Poolable {
    private Actor actor;
    private Vector2 parentPos = new Vector2();
    private Vector2 temp = new Vector2();
    private Vector2 parentScale = new Vector2(1,1);
    public static NestedActor newInstance(Actor actor){
        return Pools.get(NestedActor.class).obtain().init(actor);
    }
    public static NestedActor newInstance(TextureRegion region){
        return Pools.get(NestedActor.class).obtain().init(new SingleSpriteActor(region));
    }
    private NestedActor init(Actor actor) {
        this.actor = actor;
        return this;
    }

    public NestedActor(){
    }

    @Override
    public void draw(Batch batch, float alphaModulation, float parentX, float parentY, float parentScaleX, float parentScaleY,float rotation) {
        if(actor != null){
            temp.set(parentX-parentPos.x,parentY-parentPos.y);
            actor.moveBy(temp.x,temp.y);
            temp.set(parentScaleX/parentScale.x,parentScaleY/parentScale.y);
            actor.setScale(actor.getScaleX()*temp.x,actor.getScaleY()*temp.y);
            parentPos.set(parentX,parentY);
            parentScale.set(parentScaleX,parentScaleY);
            actor.setRotation(rotation);
            actor.draw(batch, alphaModulation);
        }

    }
    @Override
    public void setPosition(float x, float y) {
        actor.setPosition(x,y);
    }

    @Override
    public void setOrigin(int align) {
        actor.setOrigin(align);
    }

    @Override
    public float getHeight() {
        return actor.getHeight();
    }

    @Override
    public float getWidth() {
        return actor.getWidth();
    }

    @Override
    public void dispose() {
        Pools.free(this);
    }

    @Override
    public void reset() {
        actor = null;
        temp.setZero();
        parentPos.setZero();
        parentScale.set(1,1);
    }


}
