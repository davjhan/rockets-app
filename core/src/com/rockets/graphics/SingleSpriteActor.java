package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * name: GameEntity
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SingleSpriteActor extends Actor implements Disposable{
    protected Nested sprite;
    private final Affine2 worldTransform = new Affine2();
    private final Matrix4 computedTransform = new Matrix4();
    private final Matrix4 oldTransform = new Matrix4();
    public SingleSpriteActor() {

    }

    public SingleSpriteActor(TextureRegion textureRegion) {
        this(textureRegion,0,0);
    }
    public SingleSpriteActor(TextureRegion textureRegion, float offsetX, float offsetY) {
        this();
        sprite = new NestedSprite(textureRegion);
        setBounds(offsetX,offsetY,textureRegion.getRegionWidth(),textureRegion.getRegionHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(sprite != null){
            applyTransform(batch, computeTransform());
            sprite.draw(batch,parentAlpha,getX(),getDrawingY(),getScaleX(),getScaleY(),getRotation());
            resetTransform(batch);
        }


        super.draw(batch, parentAlpha);
    }

    public Nested setSprite(Nested sprite, float x, float y){
        sprite.setPosition(x,y);
        this.sprite = sprite;
        return sprite;
    }

    protected Nested setSprite(Nested sprite) {
        this.sprite = sprite;
        setSize(sprite.getWidth(),sprite.getHeight());
        return sprite;
    }

    public void removeSprite(){
        this.sprite = null;
    }

    @Override
    public boolean remove() {
        dispose();
        return super.remove();
    }

    public float getDrawingY() {
        return getY();
    }

    @Override
    public void dispose() {
        clearActions();
        clearListeners();
    }
    private void applyTransform (Batch batch, Matrix4 transform) {
        oldTransform.set(batch.getTransformMatrix());
        batch.setTransformMatrix(transform);
    }

    /** Restores the batch transform to what it was before {@link #applyTransform(Batch, Matrix4)}. Note this causes the batch to
     * be flushed. */
    private void resetTransform (Batch batch) {
        batch.setTransformMatrix(oldTransform);
    }

    /** Returns the transform for this group's coordinate system. */
    private Matrix4 computeTransform () {
        Affine2 worldTransform = this.worldTransform;
        float originX = getOriginX(), originY = getOriginY();
        worldTransform.setToTrnRotScl( getX()+getOriginX(),getY()+getOriginY(), getRotation(), getScaleX(), getScaleY());
        if (originX != 0 || originY != 0) worldTransform.translate(-originX, -originY);
        computedTransform.set(worldTransform);
        return computedTransform;
    }
}
